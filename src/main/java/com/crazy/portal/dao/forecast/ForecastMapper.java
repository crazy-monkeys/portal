package com.crazy.portal.dao.forecast;

import com.crazy.portal.bean.forecast.AgencyErrorTemplate;
import com.crazy.portal.entity.forecast.Forecast;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ForecastMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Forecast record);

    int insertSelective(Forecast record);

    Forecast selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Forecast record);

    int updateByPrimaryKey(Forecast record);

    List<Forecast> selectByYearMonth(@Param(value = "yearMonth") String yearMonth,
                                     @Param(value = "userId") Integer userId);

    List<Forecast> selectErrorDataByBatch(@Param(value = "batchNo") String batchNo,
                                          @Param(value = "userId") Integer userId);

    List<Forecast> selectNotErrorDataByBatch(@Param(value = "batchNo") String batchNo,
                                             @Param(value = "userId") Integer userId);

    int countErrorDataByBatch(@Param(value = "batchNo") String batchNo,
                              @Param(value = "userId") Integer userId);

    int updateStatus(@Param(value = "status") Integer status,
                     @Param(value = "batchNo") String batchNo,
                     @Param(value = "userId") Integer userId);

    List<Forecast> selectByUser(Integer userId);

    int selectCntByBatchAndId(@Param(value = "batchNo") String batchNo,
                              @Param(value = "list") List<AgencyErrorTemplate> list,
                              @Param(value = "userId") Integer userId);

    int deleteByBatchNo(@Param(value = "batchNo") String batchNo,
                        @Param(value = "userId") Integer userId);
}