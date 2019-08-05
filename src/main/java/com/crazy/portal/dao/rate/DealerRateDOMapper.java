package com.crazy.portal.dao.rate;

import com.crazy.portal.entity.rate.DealerRateDO;

import java.util.List;

public interface DealerRateDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DealerRateDO record);

    int insertSelective(DealerRateDO record);

    DealerRateDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DealerRateDO record);

    int updateByPrimaryKey(DealerRateDO record);

    List<DealerRateDO> selectDealerRate(DealerRateDO record);
}