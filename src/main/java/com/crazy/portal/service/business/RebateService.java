package com.crazy.portal.service.business;

import com.crazy.portal.bean.business.rebate.*;
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
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Value("${portal.file-url}")
    private String readerFilePath;
    /**
     * 客户rebate列表
     * @param bean
     * @return
     */
    public PageInfo<BusinessRebate> list(RebateQueryBean bean){
        PortalUtil.defaultStartPage(bean.getPageIndex(), bean.getPageSize());
        List<BusinessRebate> list = businessRebateMapper.selectByPage(bean);
        for(BusinessRebate record : list){
            record.setItemList(businessRebateItemMapper.selectByRebateId(record.getId()));
        }
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
    @Transactional(rollbackFor = Exception.class)
    public void confirm(RebateConfirmBean bean, Integer userId) {
        BusinessUtil.notNull(bean.getRebates(), ErrorCodes.BusinessEnum.REBATE_RECORD_NOT_FOUND);
        List<BusinessRebateItem> items = Lists.newArrayList();
        Date noticeDate = DateUtil.getCurrentTS();
        for (RebateConfirmBean.RebateRecord e : bean.getRebates()) {
            BusinessRebate info = businessRebateMapper.selectByPrimaryKey(e.getId());
            BusinessUtil.notNull(info, ErrorCodes.BusinessEnum.REBATE_RECORD_NOT_FOUND);
            BusinessUtil.assertFlase(e.getReleaseAmount().compareTo(info.getSurplusRebateAmount()) > 0, ErrorCodes.BusinessEnum.REBATE_SURPLUS_AMOUNT_BIG);
            BusinessRebateItem item = saveRebateItem(bean, e, userId, info, noticeDate);
            updateRebateMasterInfo(e, userId, info);
            items.add(item);
        }
        sendConfirmEmail(items, bean.getExecutor());
    }

    private BusinessRebateItem saveRebateItem(RebateConfirmBean bean, RebateConfirmBean.RebateRecord releaseItem, Integer userId, BusinessRebate info, Date noticeDate) {
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
        item.setNoticeDate(noticeDate);
        item.setRemark(bean.getRemark());
        item.setStatus(Enums.BusinessRebateStatus.WAIT_CONFIRM.getCode());
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
        String amebaHeaderEmail = "947516986@qq.com";
        //TODO 获取CS邮箱
        String csEmail = "947516986@qq.com";
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
     * @param bean
     * @param userId
     * @return
     */
    @Transactional
    public FileVO fileUpload(RebateUploadBean bean, Integer userId, Integer dealerId){
        BusinessUtil.notNull(bean.getFile(), ErrorCodes.BusinessEnum.REBATE_FILE_NOT_FOUND);
        List<BusinessRebateItem> items = businessRebateItemMapper.selectByParam(bean);
        BusinessUtil.assertFlase(ObjectUtils.isEmpty(items), ErrorCodes.BusinessEnum.REBATE_RECORD_NOT_FOUND);
        FileVO fileInfo = FileUtil.upload(bean.getFile(), getFilePath());
        //保存文件信息
        BusinessRebateFile file = saveRebateFile(userId, fileInfo);
        for(BusinessRebateItem item : items) {
            //更新item状态
            Integer rebateId = updateRebateItemStatus(item.getId(), userId, dealerId, file.getId());
            //更新主rebate状态
            updateRebateMasterStatus(userId, rebateId);
        }
        return fileInfo;
    }

    public void updateRebateMasterStatus(Integer userId, Integer rebateId) {
        List<BusinessRebateItem> items = businessRebateItemMapper.selectByRebateId(rebateId);
        BigDecimal total = items.stream()
                                .filter(e->e.getStatus().equals(Enums.BusinessRebateStatus.FINISHED.getCode()))
                                .map(BusinessRebateItem::getRebateAmount)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);
        boolean isAllFinished = items.stream().allMatch(e->Enums.BusinessRebateStatus.FINISHED.getCode().equals(e.getStatus()));
        boolean isAnyWaitConfirm = items.stream().anyMatch(e->Enums.BusinessRebateStatus.WAIT_CONFIRM.getCode().equals(e.getStatus()));
        boolean isAllUsedConfirm = items.stream().allMatch(e->Enums.BusinessRebateStatus.USED_CONFIRM.getCode().equals(e.getStatus()));
        BusinessRebate record = businessRebateMapper.selectByPrimaryKey(rebateId);
        BusinessUtil.notNull(record, ErrorCodes.BusinessEnum.REBATE_RECORD_NOT_FOUND);
        if (total != null && isAllFinished && total.compareTo(record.getRebateAmount()) == 0) {
            record.setStatus(Enums.BusinessRebateStatus.FINISHED.getCode());
            record.setUpdateId(userId);
            record.setUpdateTime(DateUtil.getCurrentTS());
            businessRebateMapper.updateByPrimaryKeySelective(record);
        } else if (isAllFinished){
            record.setStatus(Enums.BusinessRebateStatus.IN_APPROVAL.getCode());
            record.setUpdateId(userId);
            record.setUpdateTime(DateUtil.getCurrentTS());
            businessRebateMapper.updateByPrimaryKeySelective(record);
        } else if (isAnyWaitConfirm){
            record.setStatus(Enums.BusinessRebateStatus.WAIT_CONFIRM.getCode());
            record.setUpdateId(userId);
            record.setUpdateTime(DateUtil.getCurrentTS());
            businessRebateMapper.updateByPrimaryKeySelective(record);
        } else if (isAllUsedConfirm){
            record.setStatus(Enums.BusinessRebateStatus.USED_CONFIRM.getCode());
            record.setUpdateId(userId);
            record.setUpdateTime(DateUtil.getCurrentTS());
            businessRebateMapper.updateByPrimaryKeySelective(record);
        }
    }

    private Integer updateRebateItemStatus(Integer rebateItemId, Integer userId, Integer dealerId, Integer fileId) {
        BusinessRebateItem item = businessRebateItemMapper.selectByPrimaryKey(rebateItemId);
        BusinessUtil.notNull(item, ErrorCodes.BusinessEnum.REBATE_RECORD_NOT_FOUND);
        BusinessUtil.assertFlase(Enums.BusinessRebateStatus.FINISHED.getCode().equals(item.getStatus()), ErrorCodes.BusinessEnum.REBATE_ITEM_FINISHED_EXCEPTION);
        if(item.getStatus().equals(Enums.BusinessRebateStatus.WAIT_CONFIRM.getCode())){
            BusinessUtil.notNull(dealerId, ErrorCodes.BusinessEnum.REBATE_ITEM_CONFIRM_FILE_UPLOAD_EXCEPTION);
            item.setDlFileId(fileId);
            item.setDlExecuteDate(DateUtil.getCurrentTS());
            item.setStatus(Enums.BusinessRebateStatus.USED_CONFIRM.getCode());
        }else if(item.getStatus().equals(Enums.BusinessRebateStatus.USED_CONFIRM.getCode())){
            BusinessUtil.isNull(dealerId, ErrorCodes.BusinessEnum.REBATE_ITEM_DL_PROHIBIT_UPLOAD_EXCEPTION);
            item.setZrFileId(fileId);
            item.setZrExecuteDate(DateUtil.getCurrentTS());
            item.setStatus(Enums.BusinessRebateStatus.FINISHED.getCode());
        }
        item.setUpdateId(userId);
        item.setUpdateTime(DateUtil.getCurrentTS());
        businessRebateItemMapper.updateByPrimaryKeySelective(item);
        return item.getRebateId();
    }

    private BusinessRebateFile saveRebateFile(Integer userId, FileVO fileInfo) {
        BusinessRebateFile rebateFile = new BusinessRebateFile();
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
     */
    public String fileDownload(Integer id){
        BusinessRebateFile file = businessRebateFileMapper.selectByPrimaryKey(id);
        BusinessUtil.notNull(file, ErrorCodes.BusinessEnum.REBATE_FILE_NOT_FOUND);
        return readerFilePath.concat(File.separator).concat(REBATE_FILE_PATH).concat(File.separator).concat(file.getFileName());
    }

    public String getFilePath(){
        return filePath.concat(File.separator).concat(REBATE_FILE_PATH).concat(File.separator);
    }

    /**
     * 修改备注
     * @param id
     * @param remark
     * @param userId
     */
    public void modifyRemark(Integer id, String remark, Integer userId){
        BusinessRebateItem record = new BusinessRebateItem();
        record.setId(id);
        record.setRemark(remark);
        record.setUpdateId(userId);
        record.setUpdateTime(DateUtil.getCurrentTS());
        businessRebateItemMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * rebate数据同步
     */
    @Transactional(rollbackFor = Exception.class)
    public void rebateDataSync(String param) throws Exception {
        String currMonth = StringUtil.isBlank(param) ? DateUtil.format(new Date(), DateUtil.MONTH_FORMAT) : param;
        String preMonth = DateUtil.getPerMonth(currMonth);

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
                rebateRecord.setStatus(Enums.BusinessRebateStatus.IN_APPROVAL.getCode());
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

    private void batchUpdateSalesDetail(String currMonth, String preMonth, Date currDate) throws Exception {
        List<BusinessSalesDetailAO> salesDetailResult = rebateApiService.syncRebatePriceSalesDetails(currMonth, preMonth);
        for (BusinessSalesDetailAO salesDetailAO : salesDetailResult) {
            BusinessSalesDetail salesDetail = new BusinessSalesDetail();
            if(salesDetailAO.getId() == null){
                continue;
            }
            BeanUtils.copyNotNullFields(salesDetailAO, salesDetail);
            if("7100".equals(salesDetail.getShipmentCompany()) || "3000".equals(salesDetail.getShipmentCompany()) || "3001".equals(salesDetail.getShipmentCompany())){
                salesDetail.setShipmentCompany("SPRD");
            }
            if("4800".equals(salesDetail.getShipmentCompany())){
                salesDetail.setShipmentCompany("RDA");
            }
            salesDetail.setActive(Constant.ACTIVE);
            int recordCount = businessSalesDetailMapper.selectCountByPrimaryKey(salesDetail.getId());
            if(recordCount == 0){
                salesDetail.setCreateId(Constant.TASK_DEFAULT_USER_ID);
                salesDetail.setCreateTime(currDate);
                businessSalesDetailMapper.insertSelective(salesDetail);
            } else {
                salesDetail.setUpdateId(Constant.TASK_DEFAULT_USER_ID);
                salesDetail.setUpdateTime(currDate);
                businessSalesDetailMapper.updateByPrimaryKeySelective(salesDetail);
            }
        }
    }

    private void batchUpdatePriceRole(String currMonth, String preMonth, Date currDate) throws Exception {
        List<BusinessPriceRoleAO> priceRoleResult  = rebateApiService.syncRebatePriceRoleData(currMonth, preMonth);
        for (BusinessPriceRoleAO priceRoleAO : priceRoleResult) {
            BusinessPriceRole priceRole = new BusinessPriceRole();
            if(priceRoleAO.getId() == null){
                continue;
            }
            BeanUtils.copyNotNullFields(priceRoleAO, priceRole);
            priceRole.setActive(Constant.ACTIVE);
            int recordCount = businessPriceRoleMapper.selectCountByPrimaryKey(priceRole.getId());
            if(recordCount == 0){
                priceRole.setCreateId(Constant.TASK_DEFAULT_USER_ID);
                priceRole.setInsertTime(currDate);
                businessPriceRoleMapper.insertSelective(priceRole);
            } else {
                priceRole.setUpdateId(Constant.TASK_DEFAULT_USER_ID);
                priceRole.setUpdateTime(currDate);
                businessPriceRoleMapper.updateByPrimaryKeySelective(priceRole);
            }
        }
    }


}
