package com.crazy.portal.controller.archive;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.archive.Archive;
import com.crazy.portal.service.archive.ArchiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by lee on 2019/6/4.
 */

@Slf4j
@RestController
@RequestMapping("/archive")
public class ArchiveController extends BaseController {

    @Resource
    private ArchiveService archiveService;

    /**
     * 分页列表
     */
    @GetMapping(value = "/list")
    public BaseResponse getListByPage(String thirdFileName, String version, Integer pageNum, Integer pageSize){
        return super.successResult(archiveService.getListByPage(thirdFileName, version, pageNum, pageSize));
    }

    @GetMapping(value = "/{id}")
    public BaseResponse getDetailInfoById(@PathVariable Integer id) {
        return super.successResult(archiveService.getDetailInfoById(id));
    }

    /**
     * 文档发布，既将文档授权给相关角色或用户
     */
    @PostMapping(value = "/authorize")
    public BaseResponse authorize(@Valid @RequestBody Archive param){
        archiveService.authorizeArchiveToUser(param, super.getCurrentUser().getId());
        return super.successResult();
    }

    /**
     * 撤销已经发布的文档
     */
    @GetMapping(value = "/revoke/{id}")
    public BaseResponse revokeById(@PathVariable Integer id) {
        archiveService.revokeById(id, super.getCurrentUser().getId());
        return super.successResult();
    }
}
