package com.crazy.portal.service.system;

import com.crazy.portal.dao.system.ResourceMapper;
import com.crazy.portal.dao.system.RoleResourceMapper;
import com.crazy.portal.dao.system.UserMapper;
import com.crazy.portal.dao.system.UserRoleMapper;
import com.crazy.portal.entity.system.Resource;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
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
    private UserMapper userMapper;
    @javax.annotation.Resource
    private RoleResourceMapper roleResourceMapper;
    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    /**
     * 获取用户所有可访问的资源,可缓存至redis
     * @param userName
     * @return
     */
    public List<Resource> findAllPerMissionByUserId(String userName) throws Exception{
        if(StringUtil.isEmpty(userName)){
            return null;
        }
        User user = userMapper.findByLoginName(userName);
        Assert.notNull(user,"current user is null");
        List<Integer> roleIds = userRoleMapper.selectUserRoleByUserId(user.getId());
        if(roleIds.isEmpty()){
            log.warn("用户没有分配角色");
            return null;
        }
        List<Integer> resourceIds = roleResourceMapper.selectRoleResourceByRoleIds(roleIds);
        if(resourceIds.isEmpty()){
            log.warn("用户没有分配访问权限");
            return Collections.EMPTY_LIST;
        }

        return resourceMapper.selectResourceByIds(resourceIds);
    }

    public List<Resource> findAll(){
        return resourceMapper.findAll();
    }

}
