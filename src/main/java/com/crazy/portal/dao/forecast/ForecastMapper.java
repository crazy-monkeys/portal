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

    int updateStatus(@Param(value = "agencyStatusType") Integer agencyStatusType,
                     @Param(value = "status") Integer status,
                     @Param(value = "batchNo") String batchNo,
                     @Param(value = "userId") Integer userId);

    List<Forecast> selectPageByUser(@Param(value = "userId") Integer userId,
                                @Param(value = "customerAbbreviation") String customerAbbreviation,
                                @Param(value = "status") Integer status,
                                @Param(value = "salePeople") String salePeople,
                                @Param(value = "uploadStartTime") String uploadStartTime,
                                @Param(value = "uploadEndTime") String uploadEndTime);

    int selectCntByBatchAndId(@Param(value = "batchNo") String batchNo,
                              @Param(value = "list") List<AgencyErrorTemplate> list,
                              @Param(value = "userId") Integer userId);

    int deleteByBatchNo(@Param(value = "batchNo") String batchNo,
                        @Param(value = "userId") Integer userId);

    List<Forecast> selectByBatchNo(@Param(value = "batchNo") String batchNo);

    List<Forecast> selectRejectDataByIds(@Param(value = "ids") Integer[] ids,
                                         @Param(value = "userId") Integer userId);

    List<Forecast> selectByLeader(@Param(value = "userIds") Integer[] userIds,
                                  @Param(value = "customerAbbreviation") String customerAbbreviation,
                                  @Param(value = "salePeople") String salePeople,
                                  @Param(value = "uploadStartTime") String uploadStartTime,
                                  @Param(value = "uploadEndTime") String uploadEndTime,
                                  @Param(value = "ambPeople") String ambPeople,
                                  @Param(value = "sdPeople") String sdPeople,
                                  @Param(value = "agencyAbbreviation") String agencyAbbreviation,
                                  @Param(value = "channel") String channel);

    List<Forecast> selectBiDataByLeader(@Param(value = "userIds") Integer[] userIds,
                                  @Param(value = "customerAbbreviation") String customerAbbreviation,
                                  @Param(value = "salePeople") String salePeople,
                                  @Param(value = "uploadStartTime") String uploadStartTime,
                                  @Param(value = "uploadEndTime") String uploadEndTime,
                                  @Param(value = "agencyAbbreviation") String agencyAbbreviation,
                                  @Param(value = "channel") String channel);

    int updateStatusByIds(@Param(value = "ids") Integer[] ids,
                          @Param(value = "status") Integer status,
                          @Param(value = "operationRemark") String operationRemark);

    int deleteByBiIds(@Param(value = "ids") List<String> biIds);

    int updateErrorMsgById(@Param(value = "id") Integer id,
                           @Param(value = "errorMsg") String errorMsg);

    List<Forecast> selectByIds(@Param(value = "ids") Integer[] ids);

    int clearErrorMsgByBatch(@Param(value = "batchNo") String batchNo);

    Forecast selectRelationByKey(Integer id);

    int updateBiInfoByKey(@Param(value = "id") Integer id,
                          @Param(value = "biId") String biId,
                          @Param(value = "errorMsg") String errorMsg);

    int updateBiInfoByBiId(@Param(value = "biId") String biId,
                           @Param(value = "errorMsg") String errorMsg);

    List<String> selectBatchNoByIds(@Param(value = "ids") Integer[] ids);

    List<String> selectMonthByIds(@Param(value = "ids") Integer[] ids);

    int checkIdenticalMonth(@Param(value = "ids") Integer[] ids);

    int countDataNumByMonthAndUser(@Param(value = "userList") List<Integer> userList,
                                   @Param(value = "operationYearMonth") String operationYearMonth);

    int checkAmbAdjustmentNum(@Param(value = "ids") Integer[] ids);

    List<Integer> selectOperationTypeByIds(@Param(value = "ids") Integer[] ids);

    int checkRecord(Forecast record);

    int rejectDataByIds(@Param(value = "ids") Integer[] ids,
                        @Param(value = "operationRemark") String operationRemark);

    int updatePoPriceById(@Param(value = "id") Integer id,
                          @Param(value = "poPrice") String poPrice);
}