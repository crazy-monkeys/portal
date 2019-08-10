package com.crazy.portal.service.business;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.business.*;
import com.crazy.portal.bean.common.Constant;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.bean.customer.visitRecord.VisitRecordEO;
import com.crazy.portal.config.exception.ErrorInfo;
import com.crazy.portal.dao.business.*;
import com.crazy.portal.entity.base.BaseEntity;
import com.crazy.portal.entity.business.BusinessFile;
import com.crazy.portal.entity.business.BusinessIdrInfo;
import com.crazy.portal.entity.business.BusinessInsuranceInfo;
import com.crazy.portal.entity.customer.VisitRecord;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bill on 2019/7/30.
 *  * I: insurance 保价
 *  * D: diff 差价
 *  * R: returns 退货
 */
@Slf4j
@Service
public class IDRService {

    @Resource
    private BusinessIdrInfoMapper businessIdrInfoMapper;
    @Resource
    private BusinessDiffPriceInfoMapper businessDiffPriceInfoMapper;
    @Resource
    private BusinessInsuranceInfoMapper businessInsuranceInfoMapper;
    @Resource
    private BusinessReturnsInfoMapper businessReturnsInfoMapper;
    @Resource
    private BusinessFileMapper businessFileMapper;

    @Value("${file.path.root}")
    private String filePath;

    private static final String IDR_FILE_PATH = "business";
    /**
     * 保差退列表查询
     * @param bean
     * @return
     */
    public PageInfo<BusinessIdrInfo> selectByPage(BusinessIdrQueryBean bean){
        PortalUtil.defaultStartPage(bean.getPageIndex(), bean.getPageSize());
        List<BusinessIdrInfo> list = businessIdrInfoMapper.selectByPage(bean);
        return new PageInfo<>(list);
    }

    /**
     * 查询差保退信息详情
     * @param id
     * @return
     */
    public BusinessIdrInfo selectIdrDetail(Integer id){
        BusinessIdrInfo info = businessIdrInfoMapper.selectByPrimaryKey(id);
        if(info.getType() == Enums.BusinessIdrType.INSURANCE.getCode()){
            info.setIList(businessInsuranceInfoMapper.selectByIdrInfoId(id));
        }else if(info.getType() == Enums.BusinessIdrType.DIFF_PRICE.getCode()){
            info.setDList(businessDiffPriceInfoMapper.selectByIdrInfoId(id));
        }else if(info.getType() == Enums.BusinessIdrType.RETURNS.getCode()){
            info.setRList(businessReturnsInfoMapper.selectByIdrInfoId(id));
        }
        info.setFiles(businessFileMapper.selectByIdrInfoId(id));
        return info;
    }

    /**
     * 模板下载
     * @param type
     * @param response
     */
    public void templateDownload(Integer type, HttpServletResponse response) throws Exception{
        Map<String, List<? extends BaseRowModel>> resultMap = new HashMap<>();
        String sheetName = "";
        List<BaseRowModel> list = new ArrayList<>();
        if(type == Enums.BusinessIdrType.INSURANCE.getCode()){
            sheetName = Enums.BusinessIdrType.INSURANCE.getDesc();
            list.add(new InsuranceEO());
        }else if(type == Enums.BusinessIdrType.DIFF_PRICE.getCode()){
            sheetName = Enums.BusinessIdrType.DIFF_PRICE.getDesc();
            list.add(new DiffPriceEO());
        }else if(type == Enums.BusinessIdrType.RETURNS.getCode()){
            sheetName = Enums.BusinessIdrType.RETURNS.getDesc();
            list.add(new ReturnsEO());
        }
        resultMap.put("模板", list);
        ExcelUtils.createExcelStreamMutilByEaysExcel(response, resultMap, sheetName, ExcelTypeEnum.XLSX);
    }
    /**
     * 上传附件
     * @param type 1.保价 2.差价补偿 3.退换货
     * @param fileType 1：普通附件 2：保差退附件 3：财务完结附件
     * @param crAmount CR金额
     * @param file 文件
     * @return
     */
    public BusinessFileUploadBean upload(Integer id, Integer type, Integer fileType, BigDecimal crAmount, MultipartFile file, Integer userId) throws Exception{
        FileVO fileVo = FileUtil.upload(file, getIdrFilePath());
        if(fileType == Enums.BusinessFileType.FINANCIAL_CLOSURE.getCode()){
            BusinessFile businessFile = new BusinessFile();
            businessFile.setIdrInfoId(id);
            businessFile.setFileName(fileVo.getFileName());
            businessFile.setFilePath(fileVo.getFullPath());
            businessFile.setFileType(fileType);
            businessFile.setActive(Constant.ACTIVE);
            businessFile.setCreateUserId(userId);
            businessFile.setCreateTime(DateUtil.getCurrentTS());
        }
        List<Object> records = null;
        if(fileType == Enums.BusinessFileType.IDR.getCode()){
            Class typeClass = null;
            if(type == Enums.BusinessIdrType.INSURANCE.getCode()){
                typeClass = InsuranceEO.class;
            }else if(type == Enums.BusinessIdrType.DIFF_PRICE.getCode()){
                typeClass = DiffPriceEO.class;
            }else if(type == Enums.BusinessIdrType.RETURNS.getCode()){
                typeClass = ReturnsEO.class;
            }
            records = EasyExcelFactory.read(file.getInputStream(), new Sheet(1, 1, typeClass));
            records.forEach(e->{
                try {
                    BusinessInsuranceInfo record = new BusinessInsuranceInfo();
                    BeanUtils.copyNotNullFields(e , record);
                    record.setIdrInfoId(id);
                    record.setCreateId(userId);
                    record.setCreateTime(DateUtil.getCurrentTS());
                } catch (Exception ex) {
                    log.error("保存拜访记录异常", ex);
                }
            });
        }

        BusinessFileUploadBean result = new BusinessFileUploadBean();
        result.setFileName(fileVo.getFileName());
        result.setFilePath(fileVo.getFullPath());
        result.setIdrList(records);
        return result;
    }

    public String getIdrFilePath(){
        return filePath.concat(File.separator).concat(IDR_FILE_PATH);
    }

}
