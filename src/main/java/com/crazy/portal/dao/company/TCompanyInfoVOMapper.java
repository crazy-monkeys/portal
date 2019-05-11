package com.crazy.portal.dao.company;

import com.crazy.portal.entity.company.TCompanyInfoVO;
import org.apache.ibatis.annotations.Param;

public interface TCompanyInfoVOMapper {
    int deleteByPrimaryKey(Integer companyId);

    int insert(TCompanyInfoVO record);

    int insertSelective(TCompanyInfoVO record);

    TCompanyInfoVO selectByPrimaryKey(Integer companyId);

    int updateByPrimaryKeySelective(TCompanyInfoVO record);

    int updateByPrimaryKey(TCompanyInfoVO record);

    TCompanyInfoVO getUserCompany(Integer userId);

    TCompanyInfoVO selectByName(@Param("name")String name, @Param("region")String region);

    TCompanyInfoVO selectByUserIdAndCompanyName(@Param("userId") int userId, @Param("companyName") String companyName);
}