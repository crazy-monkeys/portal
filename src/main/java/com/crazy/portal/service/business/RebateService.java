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
        String executorInName = customerInfoMapper.selectCustAbbreviationByCustName(bean.getExecutor());
        for (RebateConfirmBean.RebateRecord record : bean.getRebates()) {
            BusinessRebate info = businessRebateMapper.selectByPrimaryKey(record.getId());
            BusinessUtil.notNull(info, ErrorCodes.BusinessEnum.REBATE_RECORD_NOT_FOUND);
            BusinessUtil.assertFlase(record.getReleaseAmount().compareTo(info.getSurplusRebateAmount()) > 0, ErrorCodes.BusinessEnum.REBATE_SURPLUS_AMOUNT_TOO_LARGE);
            BusinessRebateItem item = saveRebateItem(bean, record, userId, info, noticeDate, executorInName);
            updateRebateMasterInfo(record, userId, info);
            items.add(item);
        }
        sendConfirmEmail(items, bean.getExecutor());
    }

    /**
     * 保存Rebate释放项
     * @param bean
     * @param releaseItem
     * @param userId
     * @param info
     * @param noticeDate
     * @return
     */
    private BusinessRebateItem saveRebateItem(RebateConfirmBean bean, RebateConfirmBean.RebateRecord releaseItem, Integer userId, BusinessRebate info, Date noticeDate, String executorInName) {
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
        item.setExecutorInName(StringUtil.isBlank(executorInName) ? bean.getExecutor() : executorInName);
        return item;
    }

    /**
     * 更新Rebate主项金额
     * @param releaseItem
     * @param userId
     * @param info
     */
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
        BusinessUtil.notNull(custInfo, ErrorCodes.BusinessEnum.REBATE_EXECUTOR_EMAIL_IS_NOT_FOUND);
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
        BusinessUtil.notNull(bean.getFile(), ErrorCodes.BusinessEnum.REBATE_FILE_IS_NOT_FOUND);
        List<BusinessRebateItem> items = businessRebateItemMapper.selectByParam(bean);
        BusinessUtil.assertFlase(ObjectUtils.isEmpty(items), ErrorCodes.BusinessEnum.REBATE_RECORD_NOT_FOUND);
        FileVO fileInfo = FileUtil.upload(bean.getFile(), getFilePath());
        BusinessUtil.notNull(fileInfo, ErrorCodes.BusinessEnum.REBATE_UPLOAD_FILE_IS_NULL);
        //保存文件信息
        BusinessRebateFile file = saveRebateFile(userId, fileInfo);
        for(BusinessRebateItem item : items) {
            //更新item状态
            Integer rebateId = updateRebateItemStatus(item.getId(), userId, dealerId, file.getId(), bean.getZrExecuteDate());
            //更新主rebate状态
            updateRebateMasterStatus(userId, rebateId);
        }
        return fileInfo;
    }

    /**
     * 更新Rebate主项状态
     * @param userId
     * @param rebateId
     */
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
        Integer status = null;
        if (total != null && isAllFinished && total.compareTo(record.getRebateAmount()) == 0) {
            status = Enums.BusinessRebateStatus.FINISHED.getCode();
        } else if (isAllFinished){
            status = Enums.BusinessRebateStatus.IN_APPROVAL.getCode();
        } else if (isAnyWaitConfirm){
            status = Enums.BusinessRebateStatus.WAIT_CONFIRM.getCode();
        } else if (isAllUsedConfirm){
            status = Enums.BusinessRebateStatus.USED_CONFIRM.getCode();
        }
        if(status != null) {
            record.setStatus(status);
            record.setUpdateId(userId);
            record.setUpdateTime(DateUtil.getCurrentTS());
            businessRebateMapper.updateByPrimaryKeySelective(record);
        }
    }

    /**
     * 更新Rebate释放项状态
     * @param rebateItemId
     * @param userId
     * @param dealerId
     * @param fileId
     * @return
     */
    private Integer updateRebateItemStatus(Integer rebateItemId, Integer userId, Integer dealerId, Integer fileId, Date zrExecuteDate) {
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
            BusinessUtil.notNull(zrExecuteDate, ErrorCodes.BusinessEnum.REBATE_ZREXECUTEDATE_IS_REQUIRED);
            item.setZrExecuteDate(zrExecuteDate);
            item.setStatus(Enums.BusinessRebateStatus.FINISHED.getCode());
        }
        item.setUpdateId(userId);
        item.setUpdateTime(DateUtil.getCurrentTS());
        businessRebateItemMapper.updateByPrimaryKeySelective(item);
        return item.getRebateId();
    }

    /**
     * 保存文件
     * @param userId
     * @param fileInfo
     * @return
     */
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
        BusinessUtil.notNull(file, ErrorCodes.BusinessEnum.REBATE_FILE_IS_NOT_FOUND);
        return readerFilePath.concat(File.separator).concat(REBATE_FILE_PATH).concat(File.separator).concat(file.getFileName());
    }

    public String getFilePath(){
        return filePath.concat(File.separator).concat(REBATE_FILE_PATH).concat(File.separator);
    }

    /**
     * 修改备注
     * @param bean
     * @param userId
     */
    public void modifyRemark(RebateModifyRemarkBean bean, Integer userId){
        BusinessUtil.assertFlase(StringUtil.isBlank(bean.getRemark()), ErrorCodes.BusinessEnum.REBATE_MODIFY_REMARK_CONTENT_IS_NULL);
        BusinessRebate record = businessRebateMapper.selectByPrimaryKey(bean.getId());
        BusinessUtil.notNull(record, ErrorCodes.BusinessEnum.REBATE_RECORD_NOT_FOUND);
        record.setId(bean.getId());
        record.setRemark(bean.getRemark());
        record.setUpdateId(userId);
        record.setUpdateTime(DateUtil.getCurrentTS());
        businessRebateMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * Rebate数据同步
     */
    @Transactional(rollbackFor = Exception.class)
    public void rebateDataSync(String param) throws Exception {
        String currMonth = StringUtil.isBlank(param) ? DateUtil.format(new Date(), DateUtil.MONTH_FORMAT) : param;
        String preMonth = DateUtil.getPerMonth(currMonth);

        Date currDate = DateUtil.getCurrentTS();
        //批量更新销售明细数据
        batchUpdateSalesDetail(currMonth, preMonth, currDate);
        //批量更新rebate信息
        batchUpdateRebate(currMonth, preMonth, currDate);
        //批量更新价格数据
        batchUpdatePriceRole(currMonth, preMonth, currDate);

    }

    /**
     * 批量更新rebate信息
     * @param currMonth
     * @param preMonth
     * @param currDate
     * @throws Exception
     */
    private void batchUpdateRebate(String currMonth, String preMonth, Date currDate) throws Exception{
        //按照分组条件，查询出所有Rebate分组结果
        List<RebateGroupParam> groupParams = businessSalesDetailMapper.selectGroupParamList(currMonth, preMonth);
        List<Integer> rebateIds = new ArrayList<>();
        for (RebateGroupParam e : groupParams) {
            //查询该Rebate分组是否存在，不存在则新增
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
        //批量填充销售数据的RebateId字段
        businessSalesDetailMapper.updateRebateId();
        //批量更新Rebate总金额
        rebateIds.forEach(e-> businessSalesDetailMapper.updateRebateAmountByRebateId(e));
    }

    /**
     * 批量更新销售明细数据
     * @param currMonth
     * @param preMonth
     * @param currDate
     * @throws Exception
     */
    private void batchUpdateSalesDetail(String currMonth, String preMonth, Date currDate) throws Exception {
        ImmutableMap<String, String> replaceCompany = ImmutableMap.of("7100", "SPRD", "3000", "SPRD", "3001", "SPRD", "4800", "RDA");
        List<BusinessSalesDetailAO> salesDetailResult = rebateApiService.syncRebatePriceSalesDetails(currMonth, preMonth);
        for (BusinessSalesDetailAO salesDetailAO : salesDetailResult) {
            BusinessSalesDetail salesDetail = new BusinessSalesDetail();
            if(salesDetailAO.getId() == null){
                continue;
            }
            BeanUtils.copyNotNullFields(salesDetailAO, salesDetail);
            salesDetail.setShipmentCompany(replaceCompany.getOrDefault(salesDetail.getShipmentCompany(), salesDetail.getShipmentCompany()));
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

    /**
     * 批量更新价格数据
     * @param currMonth
     * @param preMonth
     * @param currDate
     * @throws Exception
     */
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
