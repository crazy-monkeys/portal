package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.entity.cusotmer.CustomerAccountTeam;

import java.util.List;

public interface CustomerAccountTeamMapper {
    int deleteByPrimaryKey(Integer teamId);

    int insert(CustomerAccountTeam record);

    int insertSelective(CustomerAccountTeam record);

    CustomerAccountTeam selectByPrimaryKey(Integer teamId);

    int updateByPrimaryKeySelective(CustomerAccountTeam record);

    int updateByPrimaryKey(CustomerAccountTeam record);

    List<CustomerAccountTeam> selectByCustId(Integer custId);

    int deleteByCustId(Integer custId);
}