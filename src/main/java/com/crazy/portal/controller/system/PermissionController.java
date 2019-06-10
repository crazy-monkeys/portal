package com.crazy.portal.controller.system;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.system.Resource;
import com.crazy.portal.service.system.PermissionService;
import com.crazy.portal.util.Enums;
import com.crazy.portal.util.ErrorCodes.SystemManagerEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 23:37 2019/6/10
 * @Modified by:
 */
@RestController
@Slf4j
@RequestMapping("/permission")
public class PermissionController extends BaseController{

    @javax.annotation.Resource
    private PermissionService permissionService;
    /**
     * 获取角色对应的资源id
     * @param roleIds
     * @return
     */
    @GetMapping(value = "/findPermission")
    public BaseResponse findPermission(@RequestParam List<Integer> roleIds) {
        if(roleIds == null){
            return new BaseResponse(SystemManagerEnum.ROLE_EMPTY_ID.getCode(),
                    SystemManagerEnum.ROLE_EMPTY_ID.getZhMsg());
        }
        List<Integer> resourceIds = permissionService.findPermissionIds(roleIds);
        return super.successResult(resourceIds);
    }

    /**
     * 获取所有资源
     * @return
     */
    @GetMapping(value="/getAll")
    public BaseResponse getAll(){
        List<Resource> list = permissionService.queryResourceList();
        if(list.isEmpty()){
            return super.successResult(list);
        }
        //获取树形结构
        List<Resource> resources = this.initialResource(list,true);
        //追加一个root节点
        Resource root = new Resource();
        root.setId(0);
        root.setParentId(-1);
        root.setResourceType(1);
        root.setResourceName("root");
        root.setChildren(resources);
        return super.successResult(root);
    }


    /**
     * 准备数据
     * @return
     */
    @GetMapping(value="/preEdit/{resourceId}")
    public BaseResponse preEdit(@PathVariable Integer resourceId){
        Resource resource = permissionService.findResource(resourceId);
        if(resource == null){
            return new BaseResponse(SystemManagerEnum.PERMISSION_NOT_EXIST.getCode(),
                    SystemManagerEnum.PERMISSION_NOT_EXIST.getZhMsg());
        }
        return super.successResult(resource);
    }

    /**
     * 修改资源信息
     * @return
     */
    @PostMapping(value="/edit")
    public BaseResponse editResource(@RequestBody Resource resource){
        if(resource.getResourceType() == null
                || StringUtils.isEmpty(resource.getResourceName())
                || StringUtils.isEmpty(resource.getResourceUrl())
                || resource.getId() == null){

            return new BaseResponse(SystemManagerEnum.PERMISSION_ILLEGAL.getCode(),
                    SystemManagerEnum.PERMISSION_ILLEGAL.getZhMsg());
        }

        if(!Enums.RESOURCE_TYPE_ENUM.getResourceTypes().contains(resource.getResourceType())){
            return new BaseResponse(SystemManagerEnum.PERMISSION_UN_TYPE_OPTIONAL.getCode(),
                    SystemManagerEnum.PERMISSION_UN_TYPE_OPTIONAL.getZhMsg());
        }

        if(null == permissionService.findResource(resource.getId())){

            return new BaseResponse(SystemManagerEnum.PERMISSION_NOT_EXIST.getCode(),
                    SystemManagerEnum.PERMISSION_NOT_EXIST.getZhMsg());
        }
        resource.setUpdateTime(new Date());
        resource.setUpdateUserId(super.getCurrentUser().getId());
        permissionService.saveResource(resource);
        return super.successResult();
    }

    /**
     * 删除资源信息
     * @return
     */
    @DeleteMapping(value="/del/{resourceId}")
    public BaseResponse deleteResource(@PathVariable Integer resourceId){
        if(permissionService.findResource(resourceId) == null){
            return new BaseResponse(SystemManagerEnum.PERMISSION_NOT_EXIST.getCode(),
                    SystemManagerEnum.PERMISSION_NOT_EXIST.getZhMsg());
        }
        int result = permissionService.getRoleCountByResourceId(resourceId);
        if(result > 0){
            log.warn("该权限已配置角色，请先删除该权限的角色信息!");
            return new BaseResponse(SystemManagerEnum.PERMISSION_USED.getCode(),
                    SystemManagerEnum.PERMISSION_USED.getZhMsg());
        }
        permissionService.deleteResource(resourceId);
        return super.successResult();
    }

    /**
     * 添加资源信息
     * @return
     */
    @PostMapping(value="/add")
    public BaseResponse addResource(@RequestBody Resource resource){
        if(resource.getParentId() == null
                || StringUtils.isEmpty(resource.getResourceName())
                || StringUtils.isEmpty(resource.getResourceUrl())
                || resource.getResourceType() == null){

            return new BaseResponse(SystemManagerEnum.PERMISSION_ILLEGAL.getCode(),
                    SystemManagerEnum.PERMISSION_ILLEGAL.getZhMsg());
        }
        if(!Enums.RESOURCE_TYPE_ENUM.getResourceTypes().contains(resource.getResourceType().intValue())){
            return new BaseResponse(SystemManagerEnum.PERMISSION_UN_TYPE_OPTIONAL.getCode(),
                    SystemManagerEnum.PERMISSION_UN_TYPE_OPTIONAL.getZhMsg());
        }
        if(permissionService.findResource(resource.getParentId()) == null){
            return new BaseResponse(SystemManagerEnum.PERMISSION_NOT_EXIST.getCode(),
                    SystemManagerEnum.PERMISSION_NOT_EXIST.getZhMsg());
        }
        resource.setActive((short)1);
        resource.setCreateTime(new Date());
        resource.setCreateUserId(super.getCurrentUser().getId());
        permissionService.saveResource(resource);
        return super.successResult();
    }

    private List<Resource> initialResource(List<Resource> menuList, boolean isAllRes){
        List<Resource> menuResources = new ArrayList<>();
        for (Resource tCrmResourceDO : menuList) {
            if (tCrmResourceDO.getParentId().equals(0L) && tCrmResourceDO.getResourceType().equals((short) 1)) {
                Resource crmResourceDO = this.deepFindResouce(tCrmResourceDO,menuList,isAllRes);
                menuResources.add(crmResourceDO);
            }
        }
        return menuResources;
    }

    private Resource deepFindResouce(Resource tCrmResourceDO,List<Resource> menuList,boolean isAllRes){
        for(Resource currResource : menuList){
            if(currResource.getParentId().equals(tCrmResourceDO.getId())
                    &&  (isAllRes || currResource.getResourceType().equals((short) 1))){

                List<Resource> childrenResource = tCrmResourceDO.getChildren();
                childrenResource.add(currResource);
                tCrmResourceDO.setChildren(childrenResource);
                //递归寻找
                this.deepFindResouce(currResource,menuList,isAllRes);
            }
        }
        return tCrmResourceDO;
    }
}
