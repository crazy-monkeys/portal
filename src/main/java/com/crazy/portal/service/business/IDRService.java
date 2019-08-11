package com.crazy.portal.service.business;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.crazy.portal.bean.business.BusinessFileUploadBean;
import com.crazy.portal.bean.business.BusinessIdrQueryBean;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.dao.business.*;
import com.crazy.portal.entity.business.BusinessFile;
import com.crazy.portal.entity.business.BusinessIdrInfo;
import com.crazy.portal.entity.business.IdrBaseEntity;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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

    private Multimap<Integer, String> fileCache = ArrayListMultimap.create();

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
        BusinessUtil.assertIsNull(info, ErrorCodes.BusinessEnum.BUSINESS_IDRINFO_IS_NULL);
        if(info.getType().equals(Enums.BusinessIdrType.INSURANCE.getCode())){
            info.setIList(businessInsuranceInfoMapper.selectByIdrInfoId(id));
        }else if(info.getType().equals(Enums.BusinessIdrType.DIFF_PRICE.getCode())){
            info.setDList(businessDiffPriceInfoMapper.selectByIdrInfoId(id));
        }else if(info.getType().equals(Enums.BusinessIdrType.RETURNS.getCode())){
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
        if(fileType.equals(Enums.BusinessFileType.FINANCIAL_CLOSURE.getCode())){
            BusinessUtil.assertIsNull(id, ErrorCodes.BusinessEnum.BUSINESS_IDR_ID_IS_NULL);
        }
        if(fileType.equals(Enums.BusinessFileType.IDR.getCode())){
            BusinessUtil.assertIsNull(type, ErrorCodes.BusinessEnum.BUSINESS_TYPE_IS_NULL);
        }
        BusinessFileUploadBean result = new BusinessFileUploadBean();
        if(fileType.equals(Enums.BusinessFileType.IDR.getCode())){
            Enums.BusinessIdrType idrType = Enums.BusinessIdrType.getDescByCode(type);
            List<BaseRowModel> records = ExcelUtils.readExcel(file, idrType.getType().getClass());
            result.setIdrList(records);
        }
        FileVO fileVo = FileUtil.upload(file, getIdrFilePath());
        if(fileType.equals(Enums.BusinessFileType.FINANCIAL_CLOSURE.getCode())){
            financialClosure(id, fileType, crAmount, userId, fileVo);
        }
        result.setFileName(fileVo.getFileName());
        result.setFilePath(fileVo.getFullPath());
        fileCache.put(userId, fileVo.getFullPath());
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
        return filePath.concat(File.separator).concat(IDR_FILE_PATH).concat(File.separator);
    }

    public void save(BusinessIdrInfo bean, Integer userId){
        bean.setStatus(Enums.BusinessIdrStatus.SUBMIT.getCode());
        bean.setCreateId(userId);
        bean.setCreateTime(DateUtil.getCurrentTS());
        businessIdrInfoMapper.insertSelective(bean);

        if(bean.getType().equals(Enums.BusinessIdrType.INSURANCE.getCode())) {
            saveExtendsInfo(bean.getIList(), businessInsuranceInfoMapper, bean);
        }
        if(bean.getType().equals(Enums.BusinessIdrType.DIFF_PRICE.getCode())) {
            saveExtendsInfo(bean.getDList(), businessDiffPriceInfoMapper, bean);
        }
        if(bean.getType().equals(Enums.BusinessIdrType.RETURNS.getCode())) {
            saveExtendsInfo(bean.getRList(), businessReturnsInfoMapper, bean);
        }
        saveExtendsInfo(bean.getFiles(), businessFileMapper, bean);

        clearDiscardFile(bean.getFiles(), userId);
    }

    public void saveExtendsInfo(List<? extends IdrBaseEntity> list, IdrBaseMapper mapper, BusinessIdrInfo bean){
        if(list == null){
            return;
        }
        list.forEach(e->{
            e.setIdrInfoId(bean.getId());
            e.setCreateId(bean.getCreateId());
            e.setCreateTime(bean.getCreateTime());
            mapper.insertSelective(e);
        });
    }

    @Async
    public void clearDiscardFile(List<BusinessFile> files, Integer userId){
        if(files == null){
            return;
        }
        List<String> filePaths = files.stream().map(BusinessFile::getFilePath).collect(Collectors.toList());
        Collection<String> caches = fileCache.get(userId);
        caches.stream().filter(e->!filePaths.contains(e)).forEach(e->new File(e).delete());
    }

}
