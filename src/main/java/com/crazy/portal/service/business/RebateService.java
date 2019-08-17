package com.crazy.portal.service.business;

import com.crazy.portal.bean.business.rebate.RebateQueryBean;
import com.crazy.portal.dao.business.rebate.BusinessAccountDetailMapper;
import com.crazy.portal.dao.business.rebate.BusinessRebateFileMapper;
import com.crazy.portal.dao.business.rebate.BusinessRebateMapper;
import com.crazy.portal.dao.business.rebate.BusinessStrategyMapper;
import com.crazy.portal.entity.business.rebate.BusinessRebate;
import com.crazy.portal.util.PortalUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public PageInfo<BusinessRebate> list(RebateQueryBean bean){
        PortalUtil.defaultStartPage(bean.getPageIndex(), bean.getPageSize());
        List<BusinessRebate> list = businessRebateMapper.selectByPage(bean);
        return new PageInfo<>(list);
    }

    public BusinessRebate find(Integer id){
        BusinessRebate info = businessRebateMapper.selectByPrimaryKey(id);
        info.setFile(businessRebateFileMapper.selectByRebateId(id));
        info.setAccountDetailList(businessAccountDetailMapper.selectByRebateId(id));
        info.setStrategyList(businessStrategyMapper.selectByRebateId(id));
        return info;
    }
}
