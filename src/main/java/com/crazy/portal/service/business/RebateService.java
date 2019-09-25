package com.crazy.portal.service.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crazy.portal.bean.business.rebate.RebateConfirmBean;
import com.crazy.portal.bean.business.rebate.RebateGroupParam;
import com.crazy.portal.bean.business.rebate.RebateQueryBean;
import com.crazy.portal.bean.business.rebate.SendMailBean;
import com.crazy.portal.bean.common.Constant;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.bean.system.MailBean;
import com.crazy.portal.config.email.EmailHelper;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.business.rebate.*;
import com.crazy.portal.dao.cusotmer.CustomerInfoMapper;
import com.crazy.portal.entity.business.rebate.*;
import com.crazy.portal.entity.cusotmer.CustomerInfo;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 客户Rebate
 * @Author Shawn
 * @Date 2019-08-16
 */
@Slf4j
@Service
public class RebateService {

    @Resource
    private BusinessRebateMapper businessRebateMapper;
    @Resource
    private BusinessRebateItemMapper businessRebateItemMapper;
    @Resource
    private BusinessRebateFileMapper businessRebateFileMapper;
    @Resource
    private BusinessSalesDetailMapper businessSalesDetailMapper;
    @Resource
    private BusinessPriceRoleMapper businessPriceRoleMapper;
    @Resource
    private EmailHelper emailHelper;
    @Resource
    private CustomerInfoMapper customerInfoMapper;
    @Resource
    private RebateApiService rebateApiService;
    @Value("${file.path.root}")
    private String filePath;
    private static final String REBATE_FILE_PATH = "rebate";

    /**
     * 客户rebate列表
     * @param bean
     * @return
     */
    public PageInfo<BusinessRebate> list(RebateQueryBean bean){
        PortalUtil.defaultStartPage(bean.getPageIndex(), bean.getPageSize());
        List<BusinessRebate> list = businessRebateMapper.selectByPage(bean);
        return new PageInfo<>(list);
    }

    /**
     * 客户rebate-item列表
     * @param bean
     * @return
     */
    public PageInfo<BusinessRebateItem> items(RebateQueryBean bean){
        PortalUtil.defaultStartPage(bean.getPageIndex(), bean.getPageSize());
        List<BusinessRebateItem> list = businessRebateItemMapper.selectByPage(bean);
        return new PageInfo<>(list);
    }

    /**
     * 客户rebate明细查询
     * @param id
     * @return
     */
    public BusinessRebate find(Integer id){
        BusinessRebate info = businessRebateMapper.selectByPrimaryKey(id);
        BusinessUtil.notNull(info, ErrorCodes.BusinessEnum.REBATE_RECORD_NOT_FOUND);
        info.setSalesDetails(businessSalesDetailMapper.selectByRebateId(id));
        info.setPriceRoles(businessPriceRoleMapper.selectByRebateId(id));
        return info;
    }

    /**
     * 发送确认函
     * @param bean
     * @param userId
     */
    @Transactional(rollbackFor=Exception.class)
    public void confirm(RebateConfirmBean bean, Integer userId) {
        BusinessUtil.notNull(bean.getRebates(), ErrorCodes.BusinessEnum.REBATE_RECORD_NOT_FOUND);
        List<BusinessRebateItem> items = Lists.newArrayList();
        for (RebateConfirmBean.RebateRecord e : bean.getRebates()) {
            BusinessRebate info = businessRebateMapper.selectByPrimaryKey(e.getId());
            BusinessUtil.notNull(info, ErrorCodes.BusinessEnum.REBATE_RECORD_NOT_FOUND);
            BusinessUtil.assertFlase(e.getReleaseAmount().compareTo(info.getSurplusRebateAmount()) > 0, ErrorCodes.BusinessEnum.REBATE_SURPLUS_AMOUNT_BIG);
            BusinessRebateItem item = saveRebateItem(bean, e, userId, info);
            updateRebateMasterInfo(e, userId, info);
            items.add(item);
        }
//        sendConfirmEmail(items, bean.getExecutor());
    }

    private BusinessRebateItem saveRebateItem(RebateConfirmBean bean, RebateConfirmBean.RebateRecord releaseItem, Integer userId, BusinessRebate info) {
        BusinessRebateItem item = new BusinessRebateItem();
        item.setRebateId(releaseItem.getId());
        try {
            BeanUtils.copyNotNullFields(info, item);
        }catch (Exception e){
            log.error(ErrorCodes.BusinessEnum.REBATE_SEND_EMAIL_EXCEPTION.getZhMsg(), e);
            throw new BusinessException(ErrorCodes.BusinessEnum.REBATE_SEND_EMAIL_EXCEPTION);
        }
        item.setId(null);
        item.setExecutor(bean.getExecutor());
        item.setExecuteStyle(bean.getExecuteStyle());
        item.setRebateAmount(releaseItem.getReleaseAmount());
        item.setNoticeDate(DateUtil.getCurrentTS());
        item.setRemark(bean.getRemark());
        item.setStatus(Enums.BusinessRebateItemStatus.WAIT_CONFIRM.getCode());
        item.setActive(Constant.ACTIVE);
        item.setCreateId(userId);
        item.setCreateTime(DateUtil.getCurrentTS());
        businessRebateItemMapper.insertSelective(item);
        item.setSurplusRebateAmount(info.getSurplusRebateAmount());
        item.setMasterRebateAmount(info.getRebateAmount());
        return item;
    }

    private void updateRebateMasterInfo(RebateConfirmBean.RebateRecord releaseItem, Integer userId, BusinessRebate info) {
        BigDecimal releaseAmount = info.getReleaseAmount().add(releaseItem.getReleaseAmount());
        BigDecimal surplusRebateAmount = info.getSurplusRebateAmount().subtract(releaseItem.getReleaseAmount());
        info.setReleaseAmount(releaseAmount);
        info.setSurplusRebateAmount(surplusRebateAmount);
        info.setUpdateId(userId);
        info.setUpdateTime(DateUtil.getCurrentTS());
        businessRebateMapper.updateByPrimaryKeySelective(info);
    }

    /**
     * 发送邮件
     * @param items
     * @param executor
     * @describe 代理商、CS:发送汇总的数据   客户、队长：发送item的数据
     *
     */
    private void sendConfirmEmail(List<BusinessRebateItem> items, String executor) {
        Map<String, Object> data = ImmutableMap.of("list", items);
        List<SendMailBean> toMails = Lists.newArrayList();
        //TODO 获取阿米巴队长邮箱
        String amebaHeaderEmail = "";
        //TODO 获取CS邮箱
        String csEmail = "";
        toMails.add(SendMailBean.builder().email(amebaHeaderEmail).title("Rebate确认函[阿米巴队长]").data(data).templateName(EmailHelper.MAIL_TEMPLATE.REBATE_ITEM_CONFIRM.getTemplateName()).build());
        toMails.add(SendMailBean.builder().email(csEmail).title("Rebate确认函[CS]").data(data).templateName(EmailHelper.MAIL_TEMPLATE.REBATE_MASTER_CONFIRM.getTemplateName()).build());
        CustomerInfo custInfo = customerInfoMapper.selectEmailByCustName(executor);
        BusinessUtil.notNull(custInfo, ErrorCodes.BusinessEnum.REBATE_EXECUTOR_EMAIL_NOT_FOUND);
        String templateName = EmailHelper.MAIL_TEMPLATE.REBATE_ITEM_CONFIRM.getTemplateName();
        if(custInfo.getBusinessType().equals(Enums.CUSTOMER_BUSINESS_TYPE.dealer.getCode())){
            //代理商
            templateName = EmailHelper.MAIL_TEMPLATE.REBATE_MASTER_CONFIRM.getTemplateName();
        }
        toMails.add(SendMailBean.builder().email(custInfo.getCustEmail()).title("Rebate确认函").data(data).templateName(templateName).build());
        toMails.forEach(e-> emailHelper.sendHtmlMail(MailBean.builder().tos(e.getEmail()).subject(e.getTitle()).params(e.getData()).templateName(e.getTemplateName()).build()));
    }

    /**
     * 文件上传
     * @param rebateItemId
     * @param userId
     * @param file
     * @return
     */
    @Transactional
    public FileVO fileUpload(Integer rebateItemId, Integer userId, MultipartFile file){
        BusinessUtil.notNull(rebateItemId, ErrorCodes.BusinessEnum.REBATE_ITEM_ID_IS_NULL);
        BusinessUtil.notNull(file, ErrorCodes.BusinessEnum.REBATE_FILE_NOT_FOUND);

        FileVO fileInfo = FileUtil.upload(file, getCustFilePath());
        //保存文件信息
        saveRebateFile(rebateItemId, userId, fileInfo);
        //更新item状态
        Integer rebateId = updateRebateItemStatus(rebateItemId, userId);
        //更新主rebate状态
        updateRebateMasterStatus(userId, rebateId);
        return fileInfo;
    }

    public void updateRebateMasterStatus(Integer userId, Integer rebateId) {
        List<BusinessRebateItem> items = businessRebateItemMapper.selectByRebateId(rebateId);
        BigDecimal total = items.stream()
                                .filter(e->e.getStatus().equals(Enums.BusinessRebateItemStatus.FINISHED.getCode()))
                                .map(BusinessRebateItem::getRebateAmount)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BusinessRebate record = businessRebateMapper.selectByPrimaryKey(rebateId);
        BusinessUtil.notNull(record, ErrorCodes.BusinessEnum.REBATE_RECORD_NOT_FOUND);
        if(total != null && total.compareTo(record.getRebateAmount()) == 0) {
            record.setStatus(Enums.BusinessRebateStatus.FINISHED.getCode());
            record.setUpdateId(userId);
            record.setUpdateTime(DateUtil.getCurrentTS());
            businessRebateMapper.updateByPrimaryKeySelective(record);
        }
    }

    private Integer updateRebateItemStatus(Integer rebateItemId, Integer userId) {
        BusinessRebateItem item = businessRebateItemMapper.selectByPrimaryKey(rebateItemId);
        if(item.getStatus().equals(Enums.BusinessRebateItemStatus.WAIT_CONFIRM.getCode())){
            item.setDlExecuteDate(DateUtil.getCurrentTS());
            item.setStatus(Enums.BusinessRebateItemStatus.USED_CONFIRM.getCode());
        }
        if(item.getStatus().equals(Enums.BusinessRebateItemStatus.USED_CONFIRM.getCode())){
            item.setZrExecuteDate(DateUtil.getCurrentTS());
            item.setStatus(Enums.BusinessRebateItemStatus.FINISHED.getCode());
        }
        item.setUpdateId(userId);
        item.setUpdateTime(DateUtil.getCurrentTS());
        businessRebateItemMapper.updateByPrimaryKeySelective(item);
        return item.getRebateId();
    }

    private BusinessRebateFile saveRebateFile(Integer rebateItemId, Integer userId, FileVO fileInfo) {
        BusinessRebateFile rebateFile = new BusinessRebateFile();
        rebateFile.setRebateItemId(rebateItemId);
        rebateFile.setFileName(fileInfo.getFileName());
        rebateFile.setFilePath(fileInfo.getFullPath());
        rebateFile.setActive(Constant.ACTIVE);
        rebateFile.setCreateId(userId);
        rebateFile.setCreateTime(DateUtil.getCurrentTS());
        businessRebateFileMapper.insertSelective(rebateFile);
        return rebateFile;
    }

    /**
     * 文件下载
     * @param id
     * @param response
     */
    public void fileDownload(Integer id, HttpServletResponse response){
        BusinessRebateFile file = businessRebateFileMapper.selectByRebateItemId(id);
        BusinessUtil.notNull(file, ErrorCodes.BusinessEnum.REBATE_FILE_NOT_FOUND);
        FileUtil.download(response, getCustFilePath(), file.getFileName());
    }

    public String getCustFilePath(){
        return filePath.concat(File.separator).concat(REBATE_FILE_PATH).concat(File.separator);
    }

    /**
     * rebate数据同步
     * BI -> portal
     * 每天0点
     */
    @Transactional(rollbackFor=Exception.class)
    public void rebateDataSync(String param) throws Exception {
        String currMonth = StringUtil.isBlank(param) ? DateUtil.format(new Date(), DateUtil.MONTH_FORMAT) : param;
        String preMonth = DateUtil.getPerMonth();

        Date currDate = DateUtil.getCurrentTS();

        batchUpdateSalesDetail(currMonth, preMonth, currDate);

        batchUpdateRebate(currMonth, preMonth, currDate);

        batchUpdatePriceRole(currMonth, preMonth, currDate);

    }

    private void batchUpdateRebate(String currMonth, String preMonth, Date currDate) throws Exception{
        List<RebateGroupParam> groupParams = businessSalesDetailMapper.selectGroupParamList(currMonth, preMonth);
        List<Integer> rebateIds = new ArrayList<>();
        for (RebateGroupParam e : groupParams) {
            Integer rebateId = businessRebateMapper.selectRebateIdByGroupParam(e);
            if(rebateId == null){
                BusinessRebate rebateRecord = new BusinessRebate();
                BeanUtils.copyNotNullFields(e, rebateRecord);
                rebateRecord.setRebateAmount(BigDecimal.ZERO);
                rebateRecord.setReleaseAmount(BigDecimal.ZERO);
                rebateRecord.setSurplusRebateAmount(BigDecimal.ZERO);
                rebateRecord.setStatus(Enums.BusinessRebateStatus.EXECUTE_PROCESS.getCode());
                rebateRecord.setActive(Constant.ACTIVE);
                rebateRecord.setCreateId(Constant.TASK_DEFAULT_USER_ID);
                rebateRecord.setCreateTime(currDate);
                businessRebateMapper.insertSelective(rebateRecord);
                rebateId = rebateRecord.getId();
            }
            rebateIds.add(rebateId);
        }
        businessSalesDetailMapper.updateRebateId();
        rebateIds.forEach(e-> businessSalesDetailMapper.updateRebateAmountByRebateId(e));
    }

    private void batchUpdateSalesDetail(String currMonth, String preMonth, Date currDate) throws IOException {
        String salesDetails = rebateApiService.syncRebatePriceSalesDetails(currMonth, preMonth);
        JSONArray salesDetailResult = JSON.parseArray(salesDetails);
        Iterator item = salesDetailResult.iterator();
        while (item.hasNext()){
            JSONObject e = (JSONObject) item.next();
            BusinessSalesDetail sd = new BusinessSalesDetail();
            if(e.getInteger("sales_report_id") == null){
                continue;
            }
            sd.setId(e.getInteger("sales_report_id"));
            sd.setAgencyShortName(e.getString("agency_short_name"));
            sd.setAgencyName(e.getString("agency_name"));
            sd.setCustomerShortName(e.getString("customer_short_name"));
            sd.setCustomerCode(e.getString("customer_code"));
            sd.setCustomerType(e.getString("customer_type"));
            sd.setAmebaHeader(e.getString("ameba_header"));
            sd.setAmebaDepartment(e.getString("ameba_department"));
            sd.setBu(e.getString("bu"));
            sd.setProduct(e.getString("product"));
            sd.setShipmentType(e.getString("shipment_type"));
            sd.setQty(e.getInteger("qty"));
            sd.setSalesPrice(e.getBigDecimal("sales_price"));
            sd.setPoPrice(e.getBigDecimal("po_price"));
            sd.setActualPrice(e.getBigDecimal("actual_price"));
            sd.setRebateAmount(e.getBigDecimal("rebate_amount"));
            sd.setAccountYearMonth(e.getString("year_month"));
            sd.setShipmentYearMonth(e.getString("shipment_year_month"));
            sd.setShipmentDate(e.getString("shipment_date"));
            sd.setOrderMonth(e.getString("order_month"));
            sd.setPriceRoleId(e.getString("price_role_id"));
            sd.setShipmentCompany(e.getString("shipment_company"));
            sd.setRebateType(e.getString("rebate_type"));
            sd.setClass3(e.getString("class3"));
            sd.setActive(Constant.ACTIVE);

            int recordCount = businessSalesDetailMapper.selectCountByPrimaryKey(sd.getId());
            if(recordCount == 0){
                sd.setCreateId(Constant.TASK_DEFAULT_USER_ID);
                sd.setCreateTime(currDate);
                businessSalesDetailMapper.insertSelective(sd);
            }else{
                sd.setUpdateId(Constant.TASK_DEFAULT_USER_ID);
                sd.setUpdateTime(currDate);
                businessSalesDetailMapper.updateByPrimaryKeySelective(sd);
            }

        }
    }

    private void batchUpdatePriceRole(String currMonth, String preMonth, Date currDate) throws IOException {
        String priceRoles = rebateApiService.syncRebatePriceRoleData(currMonth, preMonth);
        JSONArray priceRoleResult = JSON.parseArray(priceRoles);
        Iterator prs = priceRoleResult.iterator();
        while (prs.hasNext()){
            JSONObject e = (JSONObject) prs.next();
            BusinessPriceRole pr = new BusinessPriceRole();
            if(e.getInteger("id") == null){
                continue;
            }
            pr.setId(e.getInteger("id"));
            pr.setCustomerCode(e.getString("customer_code"));
            pr.setCustomerName(e.getString("customer_name"));
            pr.setCustomerIncode(e.getString("customer_incode"));
            pr.setCustomerShortName(e.getString("customer_short_name"));
            pr.setProduct(e.getString("product"));
            pr.setStartDate(e.getDate("start_date"));
            pr.setEndDate(e.getDate("end_date"));
            pr.setPrice(e.getBigDecimal("price"));
            pr.setSalesLimitLower(e.getBigDecimal("sales_limit_lower"));
            pr.setSalesLimitUpper(e.getBigDecimal("sales_limit_upper"));
            pr.setCalculateType(e.getString("calculate_type"));
            pr.setRebateExcuteMode(e.getString("rebate_excute_mode"));
            pr.setPriceProposer(e.getString("price_proposer"));
            pr.setShipmentType(e.getString("shipment_type"));
            pr.setRelatedCustomerCode(e.getString("related_customer_code"));
            pr.setRelatedCustomerName(e.getString("related_customer_name"));
            pr.setRelatedProduct(e.getString("related_product"));
            pr.setCreateTime(e.getString("create_time"));
            pr.setActive(Constant.ACTIVE);

            int recordCount = businessPriceRoleMapper.selectCountByPrimaryKey(pr.getId());
            if(recordCount == 0){
                pr.setCreateId(Constant.TASK_DEFAULT_USER_ID);
                pr.setInsertTime(currDate);
                businessPriceRoleMapper.insertSelective(pr);
            }else{
                pr.setUpdateId(Constant.TASK_DEFAULT_USER_ID);
                pr.setUpdateTime(currDate);
                businessPriceRoleMapper.updateByPrimaryKeySelective(pr);
            }
        }
    }


}
