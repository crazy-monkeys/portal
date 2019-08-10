package com.crazy.portal.controller.handover;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.handover.HandoverService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lee on 2019/8/9.
 */

@RestController
public class HandoverController extends BaseController {

    @Resource
    private HandoverService handoverService;

    /**
     * 出货数据查询
     * @param dealerName
     * @param status
     * @param uploadStartTime
     * @param uploadEndTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/handover")
    public BaseResponse getPageList(String dealerName, Integer status,
                                    String uploadStartTime, String uploadEndTime,
                                    Integer pageNum, Integer pageSize) {
        return super.successResult(handoverService.getPageList(dealerName, status, uploadStartTime, uploadEndTime, pageNum, pageSize));
    }

    /**
     * 代理商自己查询上传记录
     * @param dealerId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/handover/dealer")
    public BaseResponse getDealerPageList(String dealerId, Integer pageNum, Integer pageSize) {
        return super.successResult(handoverService.getDealerPageList(dealerId, pageNum, pageSize));
    }

    /**
     * 出货数据详情
     * @param id
     * @return
     */
    @GetMapping(value = "/handover/detail/{id}")
    public BaseResponse getDetailInfo(@PathVariable Integer id, String type, Integer pageNum, Integer pageSize) {
        return super.successResult(handoverService.getDetailInfo(id, type, pageNum, pageSize));
    }

    /**
     * 出货数据审核（运作部审核）
     * @return
     */
    @GetMapping(value = "/handover/approval/{id}")
    public BaseResponse approvalDeliverInfo(@PathVariable Integer id, String type) {
        handoverService.approvalDeliverInfo(id, getCurrentUser().getId(), type);
        return super.successResult();
    }


    /**
     * 模版下载
     * @param response
     * @param type deliver:出货 receive:收货
     */
    @GetMapping(value = "/handover/template")
    public void downloadTemplate(String type, HttpServletResponse response) {
        handoverService.downloadTemplate(response, type);
    }

    /**
     * 出货数据上传 并 校验
     * @param excel
     * @return
     */
    @PostMapping(value = "/handover/template")
    public BaseResponse uploadTemplateData(MultipartFile excel, String type) {
        List<?> data = handoverService.uploadTemplateData(excel, getCurrentUser().getId(), type);
        return super.successResult(handoverService.verificationData(data, getCurrentUser().getId(), type));
    }

    /**
     * 错误数据修正
     * @param excel
     * @param type
     * @param recordId
     * @return
     */
    @PostMapping(value = "/handover/error/retry")
    public BaseResponse modifyErrorData(MultipartFile excel, String type, Integer recordId) {
        List<?> data = handoverService.uploadTemplateData(excel, getCurrentUser().getId(), type);
        return super.successResult(handoverService.verificationDataByErrorData(data, getCurrentUser().getId(), type, recordId));
    }

    /**
     * 出货数据校验失败信息下载
     * @param response
     */
    @GetMapping(value = "/handover/error")
    public void downloadError(HttpServletResponse response, String type, String fileName) {
        handoverService.downloadError(response, fileName, type);
    }

    /**
     * 出货数据提交
     * @return
     */
    @PostMapping(value = "/handover/detail")
    public BaseResponse submitData(Integer recordId, String type) {
        handoverService.submitData(recordId, type);
        return super.successResult();
    }

}
