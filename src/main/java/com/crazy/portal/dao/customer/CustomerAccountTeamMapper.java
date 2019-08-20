package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.cusotmer.CustomerAccountTeam;

public interface CustomerAccountTeamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerAccountTeam record);

    int insertSelective(CustomerAccountTeam record);

    CustomerAccountTeam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerAccountTeam record);

    int updateByPrimaryKey(CustomerAccountTeam record);
}