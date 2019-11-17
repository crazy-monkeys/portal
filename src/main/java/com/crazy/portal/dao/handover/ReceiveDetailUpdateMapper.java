package com.crazy.portal.dao.handover;

import com.crazy.portal.entity.handover.ReceiveDetailUpdate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReceiveDetailUpdateMapper {
    int insert(ReceiveDetailUpdate record);

    int insertSelective(ReceiveDetailUpdate record);

    int batchInsertByBiId(@Param(value = "biIds") List<String> biIds);

    int batchDeleteByBiId(@Param(value = "biId") String biId);

    int deleteByRecordId(@Param(value = "recordId") Integer recordId);
}