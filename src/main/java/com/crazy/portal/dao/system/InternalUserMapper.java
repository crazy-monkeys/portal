package com.crazy.portal.dao.system;

import com.crazy.portal.entity.system.InternalUser;

import java.util.List;

public interface InternalUserMapper {
    int deleteByPrimaryKey(Integer inUserId);

    int insert(InternalUser record);

    int insertSelective(InternalUser record);

    InternalUser selectByPrimaryKey(Integer inUserId);

    int updateByPrimaryKeySelective(InternalUser record);

    int updateByPrimaryKey(InternalUser record);

    InternalUser selectByUserId(Integer userId);

    InternalUser selectByUserNo(String userNo);

    List<InternalUser> selectSales();

    List<InternalUser> selectCS();

    List<InternalUser> selectDS();

    InternalUser selectUserByName(String userName);

    List<String> selectUserbyOrg(String selectUserbyOrg);

    List<String> selectUserbyParenOrg(String selectUserbyOrg);

    InternalUser selectSD(String department);

    List<InternalUser> selectInUser();

}