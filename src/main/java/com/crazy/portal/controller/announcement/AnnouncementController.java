package com.crazy.portal.controller.announcement;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.announcement.Announcement;
import com.crazy.portal.service.announcement.AnnouncementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by lee on 2019/6/4.
 */

@Slf4j
@RestController
@RequestMapping("/announcement")
public class AnnouncementController extends BaseController {

    @Resource
    private AnnouncementService announcementService;

    /**
     * 列表分页
     */
    @GetMapping(value = "/list")
    public BaseResponse getPageList(String title, String releaseStartTime, String releaseEndTime,
                                    Integer pageNum, Integer pageSize, Integer typeId) {
         return super.successResult(announcementService.getPageListByTitleOrTime(title, releaseStartTime, releaseEndTime,
                 pageNum, pageSize, typeId));
    }

    /**
     * 单个公告预览
     */
    @GetMapping(value = "/preview/{id}")
    public BaseResponse previewById(@PathVariable Integer id) {
        return super.successResult(announcementService.previewById(id));
    }

    /**`
     * 新增/编辑公告
     */
    @PostMapping(value = "/info")
    public BaseResponse editByInfo(@Valid @RequestBody Announcement param) {
        return super.successResult(announcementService.editByInfo(param, super.getCurrentUser().getId()));
    }

    /**
     * 单个公告发布
     */
    @GetMapping(value = "/release/{id}")
    public BaseResponse releaseById(@PathVariable Integer id) {
        announcementService.releaseById(id, super.getCurrentUser().getId());
        return super.successResult();
    }

    /**
     * 单个公告撤销
     */
    @GetMapping(value = "/revoke/{id}")
    public BaseResponse revokeById(@PathVariable Integer id) {
        announcementService.revokeById(id, super.getCurrentUser().getId());
        return super.successResult();
    }

    /**
     * 公告文件上传
     * @param files
     * @return
     */
    @PostMapping(value = "/file")
    public BaseResponse pushFile(MultipartFile[] files) {
        return super.successResult(announcementService.pushFile(files));
    }


    /**
     * 公告文档下载或预览
     * @param id    公告ID
     */
    @GetMapping(value = "/file/{id}")
    public void getFileUrl(@PathVariable Integer id, HttpServletResponse response) {
        announcementService.pullFile(id, response);
    }

}
