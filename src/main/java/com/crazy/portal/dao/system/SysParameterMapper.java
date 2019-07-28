package com.crazy.portal.dao.system;

import com.crazy.portal.entity.system.SysParameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysParameterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysParameter record);

    int insertSelective(SysParameter record);

    SysParameter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysParameter record);

    int updateByPrimaryKey(SysParameter record);

    List<SysParameter> selectAll();

    List<SysParameter> selectAllModel();

    List<SysParameter> selectAllFunction(String model);

    List<SysParameter> selectByMAndF(@Param("model") String model, @Param("function") String function);
}