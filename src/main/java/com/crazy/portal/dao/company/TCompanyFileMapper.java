package com.crazy.portal.dao.company;

import com.crazy.portal.entity.company.TCompanyFile;

public interface TCompanyFileMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(TCompanyFile record);

    int insertSelective(TCompanyFile record);

    TCompanyFile selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(TCompanyFile record);

    int updateByPrimaryKey(TCompanyFile record);
}