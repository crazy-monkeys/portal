package com.crazy.portal.service.system;

import com.crazy.portal.bean.system.PermissionBean;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.dao.system.*;
import com.crazy.portal.entity.system.*;
import com.crazy.portal.util.DateUtil;
import com.crazy.portal.util.ErrorCodes;
import com.crazy.portal.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

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
    private RoleMapper roleMapper;
    @javax.annotation.Resource
    private RoleResourceMapper roleResourceMapper;
    @javax.annotation.Resource
    private ResourceMapper resourceMapper;


    /**
     * 获取用户所有可访问的资源,可缓存至redis
     * @param userName
     * @return
     */
    public List<Resource> findAllPerMissionByUserId(String userName){
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
        List<Integer> resourceIds = roleResourceMapper.selectRoleResourceByRoleIds(roleIds,true);
        if(resourceIds.isEmpty()){
            log.warn("用户没有分配访问权限");
            return Collections.EMPTY_LIST;
        }

        return resourceMapper.selectResourceByIds(resourceIds);
    }

    /**
     * 获取用户下所有权限
     * @return
     */
    public List<Resource> queryResourceList(Integer roleId){
        return resourceMapper.findActiveList(roleId);
    }

    /**
     * 查询资源id绑定的角色
     * @return
     */
    public int getRoleCountByResourceId(Integer resourceId){
        return resourceMapper.getRoleCountByResourceId(resourceId);
    }

    /**
     * 获取资源详情
     * @param resId
     * @return
     */
    public Resource findResource(Integer resId){
        return resourceMapper.selectByPrimaryKey(resId);
    }

    public Resource findResource(String resName){
        return resourceMapper.selectResourceByName(resName);
    }

    /**
     * 根据角色id获取拥有的权限
     * @param roleIds
     * @return
     */
    public List<Integer> findPermissionIds(List<Integer> roleIds){
        return roleResourceMapper.selectRoleResourceByRoleIds(roleIds,false);
    }

    /**
     * 将list转为tree结构
     * @param menuList
     * @return
     */
    public List<Resource> resourceTree(List<Resource> menuList){
        List<Resource> menuResources = new ArrayList<>();
        for (Resource tCrmResourceDO : menuList) {
            if (tCrmResourceDO.getParentId().equals(0) && tCrmResourceDO.getResourceType().equals(1)) {
                Resource crmResourceDO = this.deepFindResouce(tCrmResourceDO,menuList);
                menuResources.add(crmResourceDO);
            }
        }
        return menuResources;
    }

    private Resource deepFindResouce(Resource tCrmResourceDO,List<Resource> menuList){
        for(Resource currResource : menuList){
            if(currResource.getParentId().equals(tCrmResourceDO.getId())
                    &&  currResource.getResourceType().equals(1)){

                List<Resource> childrenResource = tCrmResourceDO.getChildren();
                childrenResource.add(currResource);
                tCrmResourceDO.setChildren(childrenResource);
                //递归寻找
                this.deepFindResouce(currResource,menuList);
            }
        }
        return tCrmResourceDO;
    }

    /**
     * 添加/修改资源
     *
     * @param resource
     * @return
     */
    public int saveResource(Resource resource,Integer userId){
        int result;
        if(resource.getId() == null) {
            //菜单顺序暂时不做维护
            int maxOrder = resourceMapper.maxOrder();
            resource.setResourceOrder(maxOrder + 1);
            resource.setActive((short)1);
            resource.setCreateUserId(userId);
            resource.setCreateUserId(1);
            resource.setCreateTime(DateUtil.getCurrentTS());
            result = resourceMapper.insertSelective(resource);
        }else{
            resource.setUpdateUserId(userId);
            resource.setUpdateTime(DateUtil.getCurrentTS());
            result = resourceMapper.updateByPrimaryKeySelective(resource);
        }
        return result > 0 ? 1 : -1;
    }

    /**
     * 删除资源
     * @param id
     * @return
     */
    public int deleteResource(Integer id){
        int num = resourceMapper.deleteByPrimaryKey(id);
        return num>0?1:-1;
    }

    /**
     * 分配权限
     * @param permissionBean
     * @param userId
     * @return
     */
    public boolean savePermission(PermissionBean permissionBean, Integer userId){
        String roleCode = permissionBean.getRoleCode();
        List<Integer> addPermissionIds = permissionBean.getAddPermissionIds();
        List<Integer> rmPermissionIds = permissionBean.getRmPermissionIds();
        synchronized (this){
            Role roleDO = roleMapper.findRoleByCode(roleCode);
            if(roleDO == null){
                throw new BusinessException(ErrorCodes.SystemManagerEnum.ROLE_NOT_EXIST.getCode(),
                        ErrorCodes.SystemManagerEnum.ROLE_NOT_EXIST.getZhMsg());
            }
            if(addPermissionIds != null && !addPermissionIds.isEmpty()){
                this.setPermission(addPermissionIds, roleDO.getId(),true,userId);
            }
            if(rmPermissionIds != null && !rmPermissionIds.isEmpty()){
                this.setPermission(rmPermissionIds, roleDO.getId(),false,userId);
            }
        }
        return true;
    }

    public boolean improveUserPerm(Integer roleId,Integer userId,Integer currentUserId){
        UserRole userRole = userRoleMapper.selectByUserId(userId);
        if(userRole == null){
            userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRole.setCreateTime(new Date());
            userRole.setCreateId(currentUserId);
            userRoleMapper.insertSelective(userRole);
        }else{
            if(userRole.getRoleId() != roleId){
                userRole.setRoleId(roleId);
                userRole.setUpdateId(currentUserId);
                userRole.setUpdateTime(new Date());
                userRoleMapper.updateByPrimaryKeySelective(userRole);
            }
        }
        return true;
    }

    private void setPermission(List<Integer> resourcesIds, Integer roleId, boolean isAdd, Integer userId) {
        if(resourcesIds != null && !resourcesIds.isEmpty()){
            for(Integer resourceId : resourcesIds){
                if(resourceId == null){
                    continue;
                }
                Resource currentResource = resourceMapper.selectByPrimaryKey(resourceId);
                if(currentResource == null){
                    throw new BusinessException(ErrorCodes.SystemManagerEnum.PERMISSION_NOT_EXIST.getCode(),
                            ErrorCodes.SystemManagerEnum.PERMISSION_NOT_EXIST.getZhMsg());
                }
                RoleResource roleResourceDO = roleResourceMapper.selectByRoleResource(roleId,resourceId);
                if(isAdd){
                    //新增权限
                    if(roleResourceDO != null){
                        continue;
                    }
                    RoleResource roleRes = new RoleResource();
                    roleRes.setRoleId(roleId);
                    roleRes.setResourceId(resourceId);
                    roleRes.setCreateTime(new Date());
                    roleRes.setCreateId(userId);
                    roleResourceMapper.insertSelective(roleRes);
                }else{
                    //删除权限
                    if(roleResourceDO != null){
                        currentResource.getParentId();
                        roleResourceMapper.deleteByPrimaryKey(roleResourceDO.getId());
                    }
                }
            }
        }
    }
}
