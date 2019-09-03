package com.crazy.portal.dao.forecast;

import com.crazy.portal.entity.forecast.ForecastLine;

public interface ForecastLineMapper {
    int deleteByPrimaryKey(Integer lineId);

    int insert(ForecastLine record);

    int insertSelective(ForecastLine record);

    ForecastLine selectByPrimaryKey(Integer lineId);

    int updateByPrimaryKeySelective(ForecastLine record);

    int updateByPrimaryKey(ForecastLine record);
}