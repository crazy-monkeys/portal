package com.crazy.portal.dao.handover;

import com.crazy.portal.entity.handover.DeliverDetailUpdate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeliverDetailUpdateMapper {
    int insert(DeliverDetailUpdate record);

    int insertSelective(DeliverDetailUpdate record);

    int batchInsertByBiId(@Param(value = "biIds") List<String> biIds);

    int batchDeleteByBiId(@Param(value = "biId") String biId);
}