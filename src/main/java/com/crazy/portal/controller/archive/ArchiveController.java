package com.crazy.portal.controller.archive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lee on 2019/6/4.
 */

@Slf4j
@RestController
public class ArchiveController {


    /**
     * 分页列表
     */
    @GetMapping(value = "/archive")
    public void getListByPage(){

    }

    /**
     * 文档发布，既将文档授权给相关角色或用户
     */
    @PostMapping(value = "/archive/authorize")
    public void authorize(){

    }

    /**
     * 撤销已经发布的文档
     */
    @GetMapping(value = "/archive/revoke/{id}")
    public void revokeById() {

    }
}
