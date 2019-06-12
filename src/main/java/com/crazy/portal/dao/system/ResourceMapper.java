package com.crazy.portal.dao.system;

import com.crazy.portal.entity.system.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);

    List<Resource> selectResourceByIds(@Param("ids") List<Integer> ids);

    /**
     * 根据排序号查询不为空的菜单
     * @param resourceOrder
     * @return
     */
    List<Resource> queryResourceByResourId(Integer resourceOrder);

    List<Resource> findActiveList();

    /**
     * 查询所有菜单总合
     * @return
     */
    int countOrder();

    int getRoleCountByResourceId(Integer resourceId);
}