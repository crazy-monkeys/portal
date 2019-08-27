package com.crazy.portal.dao.customer;

import com.crazy.portal.bean.customer.visitRecord.VisitRecordQueryBean;
import com.crazy.portal.entity.customer.VisitRecord;

import java.util.List;

public interface VisitRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VisitRecord record);

    int insertSelective(VisitRecord record);

    VisitRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VisitRecord record);

    int updateByPrimaryKey(VisitRecord record);

    List<VisitRecord> selectByPage(VisitRecordQueryBean visitRecordQueryBean);
}