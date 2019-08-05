package com.crazy.portal.dao.rate;

import com.crazy.portal.entity.rate.DealerRateBatch;

public interface DealerRateBatchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DealerRateBatch record);

    int insertSelective(DealerRateBatch record);

    DealerRateBatch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DealerRateBatch record);

    int updateByPrimaryKey(DealerRateBatch record);

    DealerRateBatch selectByBatchSeq(String batchSeq);

    int inActive();
}