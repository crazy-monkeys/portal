package com.crazy.portal.service.business;

import com.crazy.portal.bean.business.rebate.RebateConfirmBean;
import com.crazy.portal.bean.business.rebate.RebateQueryBean;
import com.crazy.portal.bean.common.Constant;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.bean.system.MailBean;
import com.crazy.portal.config.email.EmailHelper;
import com.crazy.portal.dao.business.rebate.*;
import com.crazy.portal.dao.cusotmer.CustomerInfoMapper;
import com.crazy.portal.entity.business.rebate.BusinessRebate;
import com.crazy.portal.entity.business.rebate.BusinessRebateFile;
import com.crazy.portal.entity.business.rebate.BusinessRebateItem;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;

/**
 * 客户Rebate
 * @Author Shawn
 * @Date 2019-08-16
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
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
    @Transactional
    public void confirm(RebateConfirmBean bean, Integer userId){
        BusinessRebate info = businessRebateMapper.selectByPrimaryKey(bean.getId());
        BusinessUtil.notNull(info, ErrorCodes.BusinessEnum.REBATE_RECORD_NOT_FOUND);
        BusinessUtil.assertFlase(bean.getSurplusRebateAmount().compareTo(info.getSurplusRebateAmount()) > 0, ErrorCodes.BusinessEnum.REBATE_SURPLUS_AMOUNT_BIG);
        saveRebateItem(bean, userId, info);
        updateRebateMasterInfo(bean, userId, info);
//        sendConfirmEmail(bean);
    }

    private void saveRebateItem(RebateConfirmBean bean, Integer userId, BusinessRebate info) {
        BusinessRebateItem item = new BusinessRebateItem();
        item.setRebateId(bean.getId());
        item.setCustomerName(info.getCustomerName());
        item.setDealerName(info.getDealerName());
        item.setExecutor(bean.getExecutor());
        item.setExecuteStyle(bean.getExecuteStyle());
        item.setRebateAmount(bean.getSurplusRebateAmount());
        item.setNoticeDate(DateUtil.getCurrentTS());
        item.setRemark(bean.getRemark());
        item.setStatus(Enums.BusinessRebateItemStatus.WAIT_CONFIRM.getCode());
        item.setActive(Constant.ACTIVE);
        item.setCreateId(userId);
        item.setCreateTime(DateUtil.getCurrentTS());
        businessRebateItemMapper.insertSelective(item);
    }

    private void updateRebateMasterInfo(RebateConfirmBean bean, Integer userId, BusinessRebate info) {
        BigDecimal releaseAmount = info.getReleaseAmount().add(bean.getSurplusRebateAmount());
        BigDecimal surplusRebateAmount = info.getSurplusRebateAmount().subtract(bean.getSurplusRebateAmount());
        info.setReleaseAmount(releaseAmount);
        info.setSurplusRebateAmount(surplusRebateAmount);
        info.setUpdateId(userId);
        info.setUpdateTime(DateUtil.getCurrentTS());
        businessRebateMapper.updateByPrimaryKeySelective(info);
    }

    private void sendConfirmEmail(RebateConfirmBean bean) {
        String email = customerInfoMapper.selectEmailByCustName(bean.getExecutor());
        MailBean mailBean = new MailBean();
        mailBean.setTos(email);
        mailBean.setSubject("Rebate确认函");
        mailBean.setParams(ImmutableMap.of("customerName", bean.getExecutor(), "amount", bean.getSurplusRebateAmount().toString()));
        mailBean.setTemplateName(EmailHelper.MAIL_TEMPLATE.REBATE_CONFIRM.getTemplateName());
        emailHelper.sendHtmlMail(mailBean);
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
        if(total.compareTo(record.getRebateAmount()) == 0) {
            record.setStatus(Enums.BusinessRebateStatus.FINISHED.getCode());
            record.setUpdateId(userId);
            record.setUpdateTime(DateUtil.getCurrentTS());
            businessRebateMapper.updateByPrimaryKeySelective(record);
        }
    }

    private Integer updateRebateItemStatus(Integer rebateItemId, Integer userId) {
        BusinessRebateItem item = businessRebateItemMapper.selectByPrimaryKey(rebateItemId);
        if(item.getStatus().equals(Enums.BusinessRebateItemStatus.WAIT_CONFIRM.getCode())){
            item.setStatus(Enums.BusinessRebateItemStatus.USED_CONFIRM.getCode());
        }
        if(item.getStatus().equals(Enums.BusinessRebateItemStatus.USED_CONFIRM.getCode())){
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
     *
     */
    @Transactional
    public void rebateDataSync(){

    }

}
