package com.crazy.portal.controller.announcement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lee on 2019/6/4.
 */

@Slf4j
@RestController
public class AnnouncementController {

    /**
     * 列表分页
     */
    @GetMapping(value = "/announcement")
    public void getListByPage() {

    }

    /**
     * 单个公告预览
     */
    @GetMapping(value = "/announcement/preview/{id}")
    public void previewById() {

    }

    /**
     * 新增/编辑公告
     */
    @PostMapping(value = "/announcement")
    public void editByInfo() {

    }

    /**
     * 单个公告撤销
     */
    @GetMapping(value = "/announcement/revoke/{id}")
    public void revokeById() {

    }

}
