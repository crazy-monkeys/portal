package com.crazy.portal.dao.forecast;

import com.crazy.portal.bean.forecast.ForecastMKBean;
import com.crazy.portal.entity.forecast.ForecastMk;

import java.util.List;

public interface ForecastMkMapper {
    int deleteByPrimaryKey(Integer mkId);

    int insert(ForecastMk record);

    int insertSelective(ForecastMk record);

    ForecastMk selectByPrimaryKey(Integer mkId);

    int updateByPrimaryKeySelective(ForecastMk record);

    int updateByPrimaryKey(ForecastMk record);

    List<ForecastMk> selectByPage(ForecastMKBean bean);

    ForecastMk selectByDealerId(Integer dealerId);
}