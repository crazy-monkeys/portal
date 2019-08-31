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

    List<SysParameter> selectAll(@Param("model") Integer model, @Param("function") Integer function, @Param("active") Integer active);

    List<SysParameter> selectByMAndF(@Param("model") String model, @Param("function") String function);

    Integer checkValue(@Param("model") Integer model, @Param("function") Integer function, @Param("pValue") String pValue, @Param("id") Integer id);
}