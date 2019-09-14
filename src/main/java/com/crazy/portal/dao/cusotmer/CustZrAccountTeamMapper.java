package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.entity.cusotmer.CustZrAccountTeam;

import java.util.List;

public interface CustZrAccountTeamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustZrAccountTeam record);

    int insertSelective(CustZrAccountTeam record);

    CustZrAccountTeam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustZrAccountTeam record);

    int updateByPrimaryKey(CustZrAccountTeam record);

    int deleteByCustId(Integer custId);

    List<CustZrAccountTeam> selectByCustId(Integer custId);

    CustZrAccountTeam selectZRByCustId(Integer custId);
}