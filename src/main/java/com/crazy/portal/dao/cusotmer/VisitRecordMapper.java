package com.crazy.portal.dao.cusotmer;

import com.crazy.portal.bean.customer.visitRecord.VisitRecordQueryBean;
import com.crazy.portal.entity.cusotmer.AgentManExportVisitRecord;
import com.crazy.portal.entity.cusotmer.VisitRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VisitRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VisitRecord record);

    int insertSelective(VisitRecord record);

    VisitRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VisitRecord record);

    int updateByPrimaryKey(VisitRecord record);

    List<VisitRecord> selectByPage(VisitRecordQueryBean visitRecordQueryBean);

    int approve(@Param("id") Integer id, @Param("c4cId") String c4cId);

    List<VisitRecord> selectAll();

    List<AgentManExportVisitRecord> agentManSelectByPage(VisitRecordQueryBean visitRecordQueryBean);

}