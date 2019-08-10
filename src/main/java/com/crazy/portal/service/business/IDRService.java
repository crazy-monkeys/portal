package com.crazy.portal.service.business;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.crazy.portal.bean.business.*;
import com.crazy.portal.bean.common.Constant;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.dao.business.*;
import com.crazy.portal.entity.business.BusinessFile;
import com.crazy.portal.entity.business.BusinessIdrInfo;
import com.crazy.portal.entity.business.BusinessInsuranceInfo;
import com.crazy.portal.entity.business.IdrBaseEntity;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;

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
     * @param type 1.保价 2.差价补偿 3.退换货
     * @param response
     */
    public void templateDownload(Integer type, HttpServletResponse response) throws Exception{
        BusinessUtil.assertIsNull(type, ErrorCodes.BusinessEnum.BUSINESS_TYPE_IS_NULL);
        Map<String, List<? extends BaseRowModel>> resultMap = new HashMap<>();
        Enums.BusinessIdrType enumType = Enums.BusinessIdrType.getDescByCode(type);
        String sheetName = enumType.getDesc();
        resultMap.put("sheet1", Collections.singletonList(enumType.getType()));
        ExcelUtils.createExcelStreamMutilByEaysExcel(response, resultMap, sheetName, ExcelTypeEnum.XLSX);
    }
    /**
     * 上传附件
     * @param id 保差退ID
     * @param type 1.保价 2.差价补偿 3.退换货
     * @param fileType 1：普通附件 2：保差退附件 3：财务完结附件
     * @param crAmount CR金额
     * @param file 文件
     * @return
     */
    public BusinessFileUploadBean upload(Integer id, Integer type, Integer fileType, BigDecimal crAmount, MultipartFile file, Integer userId) throws Exception{
        BusinessUtil.assertIsNull(fileType, ErrorCodes.BusinessEnum.BUSINESS_FILE_TYPE_IS_NULL);
        BusinessUtil.assertIsNull(file, ErrorCodes.BusinessEnum.BUSINESS_FILE_IS_NULL);
        if(fileType == Enums.BusinessFileType.FINANCIAL_CLOSURE.getCode()){
            BusinessUtil.assertIsNull(id, ErrorCodes.BusinessEnum.BUSINESS_IDR_ID_IS_NULL);
        }
        if(fileType == Enums.BusinessFileType.IDR.getCode()){
            BusinessUtil.assertIsNull(type, ErrorCodes.BusinessEnum.BUSINESS_TYPE_IS_NULL);

        }
        BusinessFileUploadBean result = new BusinessFileUploadBean();
        FileVO fileVo = FileUtil.upload(file, getIdrFilePath());
        if(fileType == Enums.BusinessFileType.FINANCIAL_CLOSURE.getCode()){
            financialClosure(id, fileType, crAmount, userId, fileVo);
        }
        if(fileType == Enums.BusinessFileType.IDR.getCode()){
            Enums.BusinessIdrType idrType = Enums.BusinessIdrType.getDescByCode(type);
            List<BaseRowModel>  records = ExcelUtils.readExcel(file, idrType.getType().getClass());
            result.setIdrList(records);
        }
        result.setFileName(fileVo.getFileName());
        result.setFilePath(fileVo.getFullPath());
        return result;
    }

    private void financialClosure(Integer id, Integer fileType, BigDecimal crAmount, Integer userId, FileVO fileVo) {
        BusinessFile businessFile = new BusinessFile();
        businessFile.setIdrInfoId(id);
        businessFile.setFileName(fileVo.getFileName());
        businessFile.setFilePath(fileVo.getFullPath());
        businessFile.setFileType(fileType);
        businessFile.setCreateId(userId);
        businessFile.setCreateTime(DateUtil.getCurrentTS());
        businessFileMapper.insertSelective(businessFile);

        BusinessIdrInfo info = new BusinessIdrInfo();
        info.setId(id);
        info.setCrAmount(crAmount);
        info.setStatus(Enums.BusinessIdrStatus.FINISHED.getCode());
        info.setUpdateId(userId);
        info.setUpdateTime(DateUtil.getCurrentTS());
        businessIdrInfoMapper.updateByPrimaryKeySelective(info);
    }

    public String getIdrFilePath(){
        return filePath.concat(File.separator).concat(IDR_FILE_PATH);
    }

    public void save(BusinessIdrInfo bean, Integer userId){
        bean.setStatus(Enums.BusinessIdrStatus.SUBMIT.getCode());
        bean.setCreateId(userId);
        bean.setCreateTime(DateUtil.getCurrentTS());
        businessIdrInfoMapper.insertSelective(bean);

        saveExtendsInfo(bean.getIList(), businessInsuranceInfoMapper, bean);

        saveExtendsInfo(bean.getDList(), businessDiffPriceInfoMapper, bean);

        saveExtendsInfo(bean.getRList(), businessReturnsInfoMapper, bean);

        saveExtendsInfo(bean.getFiles(), businessFileMapper, bean);
    }

    public void saveExtendsInfo(List<? extends IdrBaseEntity> list, IdrBaseMapper mapper, BusinessIdrInfo bean){
        list.forEach(e->{
            e.setIdrInfoId(bean.getId());
            e.setCreateId(bean.getCreateId());
            e.setCreateTime(bean.getCreateTime());
            mapper.insertSelective(e);
        });
    }

}
