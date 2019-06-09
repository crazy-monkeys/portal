package com.crazy.portal.service.system;

import com.crazy.portal.dao.system.ResourceMapper;
import com.crazy.portal.dao.system.RoleResourceMapper;
import com.crazy.portal.dao.system.UserRoleMapper;
import com.crazy.portal.entity.system.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 22:00 2019/6/7
 * @Modified by:
 */
@Service
@Slf4j
public class PermissionService {

    @javax.annotation.Resource
    private UserRoleMapper userRoleMapper;
    @javax.annotation.Resource
    private RoleResourceMapper roleResourceMapper;
    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    /**
     * 获取用户所有可访问的资源,可缓存至redis
     * @param userId
     * @return
     */
    public List<Resource> findAllPerMissionByUserId(Integer userId){
        if(userId == null) return null;

        List<Integer> roleIds = userRoleMapper.selectUserRoleByUserId(userId);
        if(roleIds.isEmpty()){
            log.warn("用户没有分配角色");
            return null;
        }
        List<Integer> resourceIds = roleResourceMapper.selectRoleResourceByRoleIds(roleIds);
        if(resourceIds.isEmpty()){
            log.warn("用户没有分配访问权限");
            return null;
        }

        return resourceMapper.selectResourceByIds(resourceIds);
    }

    public List<Resource> findAll(){
        return resourceMapper.findAll();
    }

}
