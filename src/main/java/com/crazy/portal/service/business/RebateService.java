package com.crazy.portal.service.business;

import com.crazy.portal.bean.business.rebate.RebateQueryBean;
import com.crazy.portal.bean.common.Constant;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.dao.business.rebate.BusinessAccountDetailMapper;
import com.crazy.portal.dao.business.rebate.BusinessRebateFileMapper;
import com.crazy.portal.dao.business.rebate.BusinessRebateMapper;
import com.crazy.portal.dao.business.rebate.BusinessStrategyMapper;
import com.crazy.portal.entity.basic.BasicFile;
import com.crazy.portal.entity.business.rebate.BusinessRebate;
import com.crazy.portal.entity.business.rebate.BusinessRebateFile;
import com.crazy.portal.util.DateUtil;
import com.crazy.portal.util.Enums;
import com.crazy.portal.util.FileUtil;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
public class RebateService {

    @Resource
    private BusinessRebateMapper businessRebateMapper;
    @Resource
    private BusinessRebateFileMapper businessRebateFileMapper;
    @Resource
    private BusinessAccountDetailMapper businessAccountDetailMapper;
    @Resource
    private BusinessStrategyMapper businessStrategyMapper;
    @Value("${file.path.root}")
    private String filePath;
    private static final String CUST_FILE_PATH = "rebate";

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
    public void confirm(BusinessRebate bean, Integer userId){
        bean.setUpdateId(userId);
        bean.setUpdateTime(DateUtil.getCurrentTS());
        bean.setStatus(Enums.BusinessRebateStatus.CS_CONFIRM.getCode());
        businessRebateMapper.updateByPrimaryKeySelective(bean);
    }

    /**
     * 文件上传
     * @param files
     * @return
     */
    public FileVO fileUpload(Integer id, Integer userId, MultipartFile files){
        FileVO fileInfo = FileUtil.upload(files, getCustFilePath());
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
     * @param response
     */
    public void fileDownload(Integer id, HttpServletResponse response){
        BusinessRebateFile file = businessRebateFileMapper.selectByRebateId(id);
        FileUtil.download(response, getCustFilePath(), file.getFileName());
    }

    public String getCustFilePath(){
        return filePath.concat(File.separator).concat(CUST_FILE_PATH).concat(File.separator);
    }
}
