package com.crazy.portal.service.rate;

import com.alibaba.excel.support.ExcelTypeEnum;
import com.crazy.portal.bean.common.Constant;
import com.crazy.portal.bean.rate.AgencyRateQueryBean;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.rate.AgencyRateMapper;
import com.crazy.portal.entity.rate.AgencyRate;
import com.crazy.portal.util.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Describe 代理商费率
 * @Author Shawn
 * @Date 2019-08-10
 */
@Slf4j
@Service
public class AgencyRateService {

    public static final String FILE_NAME = "代理费率";
    @Resource
    private AgencyRateMapper agencyRateMapper;

    /**
     * 列表查询
     * @param bean
     * @return
     */
    public PageInfo<AgencyRate> selectByPage(AgencyRateQueryBean bean){
        PortalUtil.defaultStartPage(bean.getPageIndex(), bean.getPageSize());
        List<AgencyRate> list = agencyRateMapper.selectByPage(bean);
        return new PageInfo<>(list);
    }

    /**
     * 上传代理费率文件
     * @param files
     * @param userId
     * @return
     */
    @Transactional
    public List<AgencyRate> upload(MultipartFile[] files, Integer userId) {
        BusinessUtil.assertFlase(files == null || files.length == 0, ErrorCodes.BusinessEnum.AGENCY_RATE_UPLOAD_FILE_IS_NULL_ERROR);
        List<AgencyRate> results = new ArrayList<>();
        for (MultipartFile file : files) {
            List<AgencyRateQueryBean> beans = ExcelUtils.readExcel(file, AgencyRateQueryBean.class);
            BusinessUtil.assertFlase(ObjectUtils.isEmpty(beans), ErrorCodes.BusinessEnum.AGENCY_RATE_UPLOAD_FILE_CONTENT_IS_NULL_ERROR);
            results.addAll(saveRecord(beans, userId));
        }
        return results;
    }

    public List<AgencyRate> saveRecord(List<AgencyRateQueryBean> beans, Integer userId){
        List<AgencyRate> results = new ArrayList<>();
        try {
            for (AgencyRateQueryBean bean : beans) {
                AgencyRate record = new AgencyRate();
                BeanUtils.copyNotNullFields(bean, record);
                record.setCustomerType(Enums.CustomerType.getDescByCode(record.getCustomerType()));
                BusinessUtil.notNull(record.getCustomerType(), ErrorCodes.BusinessEnum.AGENCY_RATE_CUST_TYPE_ERROR);
                record.setActive(Enums.RateType.INITIALIZE.getCode());
                record.setCreateUserId(userId);
                record.setCreateTime(DateUtil.getCurrentTS());
                agencyRateMapper.insertSelective(record);
                results.add(record);
            }
        } catch (BusinessException ex){
            throw ex;
        } catch (Exception ex) {
            log.error(ErrorCodes.BusinessEnum.AGENCY_RATE_UPLOAD_EXCEPTION.getZhMsg(), ex);
            throw new BusinessException(ErrorCodes.BusinessEnum.AGENCY_RATE_UPLOAD_EXCEPTION);
        }
        return results;
    }

    /**
     * 审批费率
     * @param rateIds
     */
    @Transactional(rollbackFor = Exception.class)
    public void approveRate(String rateIds, Integer userId){
        BusinessUtil.assertFlase(StringUtil.isBlank(rateIds), ErrorCodes.BusinessEnum.AGENCY_RATE_APPROVE_RECORD_IS_REQUIRED);
        agencyRateMapper.invalidationAll();
        agencyRateMapper.validationByIds(Stream.of(rateIds.split(Constant.DEFAULT_SEPARATE_CHAR)).map(id->Integer.valueOf(id)).collect(Collectors.toList()), userId);
    }

    /**
     * 模板下载
     * @param response
     */
    public void templateDownload(HttpServletResponse response) {
        try {
            Map<String, List> resultMap = new HashMap<>();
            resultMap.put(ExcelUtils.DEFAULT_SHEET_NAME, Collections.singletonList(new AgencyRateQueryBean()));
            ExcelUtils.createExcelStreamMutilByEaysExcel(response, resultMap, FILE_NAME, ExcelTypeEnum.XLSX);
        }catch (Exception ex){
            log.error(ErrorCodes.BusinessEnum.EXCEL_TEMPLATE_DOWNLOAD_FAIL.getZhMsg(), ex);
            throw new BusinessException(ErrorCodes.BusinessEnum.EXCEL_TEMPLATE_DOWNLOAD_FAIL);
        }
    }
}
