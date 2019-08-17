package com.crazy.portal.dao.business.idr;


import com.crazy.portal.entity.business.idr.IdrBaseEntity;

public interface IdrBaseMapper {

    int insertSelective(IdrBaseEntity record);
}
