package com.crazy.portal.dao.rate;

import com.crazy.portal.bean.rate.AgencyRateQueryBean;
import com.crazy.portal.entity.rate.AgencyRate;

import java.util.List;

public interface AgencyRateMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(AgencyRate record);

    AgencyRate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgencyRate record);

    List<AgencyRate> selectByPage(AgencyRateQueryBean bean);

    int inActive();

    int approve(String ids);
}