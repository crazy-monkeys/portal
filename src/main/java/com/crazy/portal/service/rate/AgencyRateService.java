package com.crazy.portal.service.rate;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Describe 代理商费率
 * @Author Shawn
 * @Date 2019-08-10
 */
@Slf4j
@Transactional
@Service
public class AgencyRateService {

    @Resource
    private AgencyRateMapper agencyRateMapper;

    public PageInfo<AgencyRate> selectByPage(AgencyRateQueryBean bean){
        PortalUtil.defaultStartPage(bean.getPageIndex(), bean.getPageSize());
        List<AgencyRate> list = agencyRateMapper.selectByPage(bean);
        return new PageInfo<>(list);
    }

    public List<AgencyRate> uploadAgencyRateFile(MultipartFile[] files, Integer userId) throws Exception {
        agencyRateMapper.inActive();
        List<AgencyRate> results = new ArrayList<>();
        for (MultipartFile file : files) {
            List<Object> records = EasyExcelFactory.read(file.getInputStream(), new Sheet(1, 1, AgencyRateQueryBean.class));
            records.forEach(e->{
                try {
                    AgencyRate record = new AgencyRate();
                    BeanUtils.copyNotNullFields(e , record);
                    if(record.getCustomerType().equals("B1")){
                        record.setCustomerType("Account Market");
                    }else{
                        record.setCustomerType("Mass Market");
                    }
                    results.add(record);
                    record.setActive(Enums.RATE_TYPE.INI.getCode());
                    record.setCreateUserId(userId);
                    record.setCreateTime(DateUtil.getCurrentTS());
                    agencyRateMapper.insertSelective(record);
                } catch (Exception ex) {
                    log.error("保存代理费率异常", ex);
                    throw new BusinessException(ErrorCodes.BusinessEnum.AGENCY_RATE_UPLOAD_EXCEPTION);
                }
            });
        }
        return results;
    }

    public void approveRate(){
        agencyRateMapper.approve();
    }
}