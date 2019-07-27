package com.crazy.portal.dao.basic;

import com.crazy.portal.entity.basic.BasicFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasicFileMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(BasicFile record);

    BasicFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasicFile record);

    List<BasicFile> selectByCustId(@Param("custId")Integer custId);

    List<Integer> selectIdsByCustId(@Param("custId") Integer custId);

    int deleteByCustId(@Param("custId") Integer custId, @Param("userId")Integer userId);
}