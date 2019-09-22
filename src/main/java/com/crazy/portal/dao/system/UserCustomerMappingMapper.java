package com.crazy.portal.dao.system;

import com.crazy.portal.bean.system.UserCustomerMappingBean;
import com.crazy.portal.entity.system.UserCustomerMapping;

import java.util.List;

public interface UserCustomerMappingMapper {
    int deleteByPrimaryKey(Integer mappingId);

    int insert(UserCustomerMapping record);

    int insertSelective(UserCustomerMapping record);

    UserCustomerMapping selectByPrimaryKey(Integer mappingId);

    int updateByPrimaryKeySelective(UserCustomerMapping record);

    int updateByPrimaryKey(UserCustomerMapping record);

    List<UserCustomerMapping> selectByPage(UserCustomerMappingBean bean);

    UserCustomerMapping selectUserMapping(Integer userId, String mappingModel);
}