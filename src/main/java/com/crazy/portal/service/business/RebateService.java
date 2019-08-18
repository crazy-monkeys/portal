package com.crazy.portal.service.business;

import com.crazy.portal.bean.business.rebate.RebateConfirmBean;
import com.crazy.portal.bean.business.rebate.RebateQueryBean;
import com.crazy.portal.bean.common.Constant;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.bean.system.MailBean;
import com.crazy.portal.config.email.EmailHelper;
import com.crazy.portal.dao.business.rebate.*;
import com.crazy.portal.dao.customer.CustomerInfoMapper;
import com.crazy.portal.entity.business.rebate.BusinessRebate;
import com.crazy.portal.entity.business.rebate.BusinessRebateFile;
import com.crazy.portal.entity.business.rebate.BusinessRebateItem;
import com.crazy.portal.entity.customer.CustomerInfo;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
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
    private BusinessAccountDetailMapper businessAccountDetailMapper;
    @Resource
    private BusinessStrategyMapper businessStrategyMapper;
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
     * 客户rebate明细查询
     * @param id
     * @return
     */
    public BusinessRebate find(Integer id){
        BusinessRebate info = businessRebateMapper.selectByPrimaryKey(id);
        BusinessUtil.isNull(info, ErrorCodes.BusinessEnum.REBATE_RECORD_NOT_FOUND);
        info.setFile(businessRebateFileMapper.selectByRebateId(id));
        info.setAccountDetailList(businessAccountDetailMapper.selectByRebateId(id));
        info.setStrategyList(businessStrategyMapper.selectByRebateId(id));
        return info;
    }

    /**
     * 发送确认函
     * @param bean
     * @param userId
     */
    public void confirm(RebateConfirmBean bean, Integer userId){
        BusinessRebate info = businessRebateMapper.selectByPrimaryKey(bean.getId());
        BusinessUtil.isNull(info, ErrorCodes.BusinessEnum.REBATE_RECORD_NOT_FOUND);
        saveRebateItem(bean, userId, info);
        sendConfirmEmail(bean);
    }

    private void saveRebateItem(RebateConfirmBean bean, Integer userId, BusinessRebate info) {
        BusinessRebateItem item = new BusinessRebateItem();
        item.setRebateId(bean.getId());
        item.setCustomerName(info.getCustomerName());
        item.setDealerName(info.getDealerName());
        item.setExecutor(bean.getExecutor());
        item.setExecuteStyle(bean.getExecuteStyle());
        item.setRebateAmount(bean.getSurplusRebateAmount());
        item.setRemark(bean.getRemark());
        item.setStatus(Enums.BusinessRebateItemStatus.IN_APPROVAL.getCode());
        item.setActive(Constant.ACTIVE);
        item.setCreateId(userId);
        item.setCreateTime(DateUtil.getCurrentTS());
        businessRebateItemMapper.insertSelective(item);
    }

    private void sendConfirmEmail(RebateConfirmBean bean) {
        CustomerInfo custInfo = customerInfoMapper.selectByCustName(bean.getExecutor());
        MailBean mailBean = new MailBean();
        mailBean.setTos(custInfo.getCustEmail());
        mailBean.setSubject("Rebate确认函");
        mailBean.setParams(ImmutableMap.of("customerName", bean.getExecutor(), "amount", bean.getSurplusRebateAmount().toString()));
        mailBean.setTemplateName(Enums.MAIL_TEMPLATE.REBATE_CONFIRM.getTemplateName());
        emailHelper.sendHtmlMail(mailBean);
    }

    /**
     * 文件上传
     * @param id
     * @param userId
     * @param file
     * @return
     */
    public FileVO fileUpload(Integer id, Integer userId, MultipartFile file){
        FileVO fileInfo = FileUtil.upload(file, getCustFilePath());
        BusinessRebate record = businessRebateMapper.selectByPrimaryKey(id);
        record.setStatus(Enums.BusinessRebateStatus.FINISHED.getCode());
        record.setUpdateId(userId);
        record.setUpdateTime(DateUtil.getCurrentTS());
        businessRebateMapper.updateByPrimaryKeySelective(record);

        BusinessRebateFile rebateFile = new BusinessRebateFile();
        rebateFile.setRebateId(id);
        rebateFile.setFileName(fileInfo.getFileName());
        rebateFile.setFilePath(fileInfo.getFullPath());
        rebateFile.setActive(Constant.ACTIVE);
        rebateFile.setCreateId(userId);
        rebateFile.setCreateTime(DateUtil.getCurrentTS());
        businessRebateFileMapper.insertSelective(rebateFile);

        return fileInfo;
    }

    /**
     * 文件下载
     * @param id
     * @param response
     */
    public void fileDownload(Integer id, HttpServletResponse response){
        BusinessRebateFile file = businessRebateFileMapper.selectByRebateId(id);
        BusinessUtil.isNull(file, ErrorCodes.BusinessEnum.REBATE_FILE_NOT_FOUND);
        FileUtil.download(response, getCustFilePath(), file.getFileName());
    }

    public String getCustFilePath(){
        return filePath.concat(File.separator).concat(REBATE_FILE_PATH).concat(File.separator);
    }
}
