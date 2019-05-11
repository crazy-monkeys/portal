package com.crazy.portal.dao.company;

import com.crazy.portal.entity.company.TContactInformationVO;

public interface TContactInformationVOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TContactInformationVO record);

    int insertSelective(TContactInformationVO record);

    TContactInformationVO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TContactInformationVO record);

    int updateByPrimaryKey(TContactInformationVO record);
}