package com.crazy.portal.service.system;

import com.crazy.portal.dao.system.ResourceDOMapper;
import com.crazy.portal.dao.system.RoleResourceDOMapper;
import com.crazy.portal.dao.system.UserRoleDOMapper;
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
public class ResourceService {

    @javax.annotation.Resource
    private UserRoleDOMapper userRoleDOMapper;
    @javax.annotation.Resource
    private RoleResourceDOMapper roleResourceDOMapper;
    @javax.annotation.Resource
    private ResourceDOMapper resourceDOMapper;

    /**
     * 获取用户所有可访问的资源,可缓存至redis
     * @param userId
     * @return
     */
    public List<Resource> findAllResourceByUserId(Integer userId){
        if(userId == null) return null;

        List<Integer> roleIds = userRoleDOMapper.selectUserRoleByUserId(userId);
        if(roleIds.isEmpty()){
            log.warn("用户没有分配角色");
            return null;
        }
        List<Integer> resourceIds = roleResourceDOMapper.selectRoleResourceByRoleIds(roleIds);
        if(resourceIds.isEmpty()){
            log.warn("用户没有分配访问权限");
            return null;
        }

        return resourceDOMapper.selectResourceByIds(resourceIds);
    }
}
