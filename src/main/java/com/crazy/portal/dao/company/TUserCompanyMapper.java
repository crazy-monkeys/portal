package com.crazy.portal.dao.company;

import com.crazy.portal.entity.company.TUserCompany;

public interface TUserCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserCompany record);

    int insertSelective(TUserCompany record);

    TUserCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserCompany record);

    int updateByPrimaryKey(TUserCompany record);
}