package com.crazy.portal.controller.announcement;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.announcement.Announcement;
import com.crazy.portal.service.announcement.AnnouncementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by lee on 2019/6/4.
 */

@Slf4j
@RestController
public class AnnouncementController extends BaseController {

    @Resource
    private AnnouncementService announcementService;

    /**
     * 列表分页
     */
    @GetMapping(value = "/announcement")
    public BaseResponse getPageList(String title, String releaseStartTime, String releaseEndTime, Integer pageNum, Integer pageSize) {
         return super.successResult(announcementService.getPageListByTitleOrTime(title, releaseStartTime, releaseEndTime, pageNum, pageSize));
    }

    /**
     * 单个公告预览
     */
    @GetMapping(value = "/announcement/preview/{id}")
    public BaseResponse previewById(@PathVariable Integer id) {
        return super.successResult(announcementService.previewById(id));
    }

    /**
     * 新增/编辑公告
     */
    @PostMapping(value = "/announcement")
    public BaseResponse editByInfo(@Valid @RequestBody Announcement param) {
//        announcementService.editByInfo(param, super.getCurrentUser().getId());
        announcementService.editByInfo(param, 1);
        return super.successResult();
    }

    /**
     * 单个公告发布
     */
    @GetMapping(value = "/announcement/release/{id}")
    public BaseResponse releaseById(@PathVariable Integer id) {
//        announcementService.releaseById(id, super.getCurrentUser().getId());
        announcementService.releaseById(id, 1);
        return super.successResult();
    }

    /**
     * 单个公告撤销
     */
    @GetMapping(value = "/announcement/revoke/{id}")
    public BaseResponse revokeById(@PathVariable Integer id) {
//        announcementService.revokeById(id, super.getCurrentUser().getId());
        announcementService.revokeById(id, 1);
        return super.successResult();
    }

    @PostMapping(value = "/announcement/file")
    public BaseResponse pushFile(MultipartFile[] files) {
        return super.successResult(announcementService.pushFile());
    }

}
