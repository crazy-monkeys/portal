package com.crazy.portal.dao.forecast;

import com.crazy.portal.bean.forecast.SdUpdateTemplate;
import com.crazy.portal.entity.forecast.ForecastSd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ForecastSdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ForecastSd record);

    int insertSelective(ForecastSd record);

    ForecastSd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ForecastSd record);

    int updateByPrimaryKey(ForecastSd record);

    List<SdUpdateTemplate> selectTotalByForecastIds(@Param(value = "forecastIds") Integer[] forecastIds);

    List<ForecastSd> selectPage();

    ForecastSd selectByMonthAndProduct(@Param(value = "operationYearMonth") String operationYearMonth,
                                       @Param(value = "company") String company,
                                       @Param(value = "bu") String bu,
                                       @Param(value = "pdt") String pdt,
                                       @Param(value = "productType") String productType,
                                       @Param(value = "platform") String platform,
                                       @Param(value = "productModel") String productModel);

    ForecastSd selectByBiId(@Param(value = "biId") String biId);
}