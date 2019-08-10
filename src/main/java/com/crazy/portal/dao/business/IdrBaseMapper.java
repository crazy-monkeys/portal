package com.crazy.portal.dao.business;


import com.crazy.portal.entity.business.IdrBaseEntity;

public interface IdrBaseMapper {

    int insertSelective(IdrBaseEntity record);
}
