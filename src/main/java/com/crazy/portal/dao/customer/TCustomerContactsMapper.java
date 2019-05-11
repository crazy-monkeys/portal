package com.crazy.portal.dao.customer;

import com.crazy.portal.entity.customer.TCustomerContacts;

public interface TCustomerContactsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCustomerContacts record);

    int insertSelective(TCustomerContacts record);

    TCustomerContacts selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCustomerContacts record);

    int updateByPrimaryKey(TCustomerContacts record);
}