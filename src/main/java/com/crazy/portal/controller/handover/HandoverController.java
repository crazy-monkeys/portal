package com.crazy.portal.controller.handover;

import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.handover.HandoverServiceContext;
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
    @Resource
    private HandoverServiceContext handoverServiceContext;

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
    @GetMapping(value = "/handover/dealer/reject")
    public BaseResponse getRejectInfo(Integer dealerId, String type, Integer pageNum, Integer pageSize) {
        return super.successResult(handoverService.getRejectInfo(dealerId, type, pageNum, pageSize));
    }

    @GetMapping(value = "/handover/dealer/detail")
    public BaseResponse getDetailList(Integer dealerId, String type, Integer pageNum, Integer pageSize) {
        return super.successResult(handoverServiceContext.getService(type).getDetailList(dealerId, pageNum, pageSize));
    }

    @GetMapping(value = "/handover/dealer/reject/download")
    public void downloadRejectData(Integer recordId, String type, HttpServletResponse response) {
        handoverServiceContext.getService(type).downloadRejectData(recordId, response);
    }

    /**
     * 数据详情
     * @param id
     * @return
     */
    @GetMapping(value = "/handover/detail/{id}")
    public BaseResponse getDetailInfo(@PathVariable Integer id, String type, Integer pageNum, Integer pageSize) {
        return super.successResult(handoverServiceContext.getService(type).getDetailInfo(id, pageNum, pageSize));
    }

    /**
     * 数据操作（运作部审核 || 驳回）
     * @param id
     * @param type
     * @param status
     * @param remark
     * @return
     */
    @OperationLog
    @GetMapping(value = "/handover/operation/{id}")
    public BaseResponse operationDeliverInfo(@PathVariable Integer id, String type, Integer status, String remark) {
        handoverService.operationDeliverInfo(id, getCurrentUser().getId(), type, status, remark);
        return super.successResult();
    }


    /**
     * 模版下载
     * @param response
     * @param type deliver:出货 receive:收货
     */
    @GetMapping(value = "/handover/template")
    public void downloadTemplate(String type, HttpServletResponse response) {
        handoverServiceContext.getService(type).downloadTemplate(response);
    }

    /**
     * 数据上传 并 校验
     * @param excel
     * @return
     */
    @OperationLog
    @PostMapping(value = "/handover/template")
    public BaseResponse uploadTemplateData(MultipartFile excel, String type) {
        List<?> data = handoverServiceContext.getService(type).uploadTemplateData(excel, getCurrentUser().getId());
        return super.successResult(handoverServiceContext.getService(type).verificationData(data, getCurrentUser().getId()));
//        return super.successResult();
    }

    /**
     * 错误数据修正
     * @param excel
     * @param type
     * @param recordId
     * @return
     */
    @OperationLog
    @PostMapping(value = "/handover/error/retry")
    public BaseResponse modifyErrorData(MultipartFile excel, String type, Integer recordId) {
        List<?> data = handoverServiceContext.getService(type).uploadTemplateData(excel, getCurrentUser().getId());
        return super.successResult(handoverServiceContext.getService(type).verificationDataByErrorData(data, getCurrentUser().getId(), recordId));
    }

    /**
     * 数据校验失败信息下载
     * @param response
     */
    @GetMapping(value = "/handover/error")
    public void downloadError(HttpServletResponse response, String type, String fileName) {
        handoverServiceContext.getService(type).downloadError(response, fileName);
    }

    /**
     * 数据提交
     * @return
     */
    @OperationLog
    @PostMapping(value = "/handover/detail")
    public BaseResponse submitData(Integer recordId, String type) {
        handoverService.checkDataStatus(recordId);
        return super.successResult(handoverServiceContext.getService(type).saveData(recordId, getCurrentUser().getId()));
    }

    /**
     * 数据删除
     * @param ids
     * @param type
     * @return
     */
    @OperationLog
    @PostMapping(value = "/handover/detail/delete")
    public BaseResponse deleteData(Integer[] ids, String type) {
        handoverServiceContext.getService(type).batchDeleteData(ids, getCurrentUser().getId());
        return super.successResult();
    }

}
