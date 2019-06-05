package com.crazy.portal.controller.announcement;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.announcement.AnnouncementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

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
    public BaseResponse previewById() {
        return super.successResult();
    }

    /**
     * 新增/编辑公告
     */
    @PostMapping(value = "/announcement")
    public BaseResponse editByInfo() {
        return super.successResult();
    }

    /**
     * 单个公告撤销
     */
    @GetMapping(value = "/announcement/revoke/{id}")
    public BaseResponse revokeById() {
        return super.successResult();
    }

}
