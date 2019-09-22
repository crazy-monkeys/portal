package com.crazy.portal.controller.business;

import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.business.rebate.RebateConfirmBean;
import com.crazy.portal.bean.business.rebate.RebateQueryBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.service.business.RebateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 客户Rebate
 * @Author Shawn
 * @Date 2019-08-16
 */
@Slf4j
@RestController
@RequestMapping("/business/rebate")
public class RebateController extends BaseController {

    @Resource
    private RebateService rebateService;

    /**
     * 客户Rebate列表
     * @param bean
     * @return
     */
    @GetMapping("/list")
    public BaseResponse list(RebateQueryBean bean){
        return successResult(rebateService.list(bean));
    }

    /**
     * Rebate查询列表
     * @param bean
     * @return
     */
    @GetMapping("/items")
    public BaseResponse item(RebateQueryBean bean){
        return successResult(rebateService.items(bean));
    }

    /**
     * Rebate明细查询
     * @param id
     * @return
     */
    @GetMapping("/find/{id}")
    public BaseResponse find(@PathVariable Integer id){
        return successResult(rebateService.find(id));
    }

    /**
     * 发送确认函
     * @param bean
     * @return
     */
    @OperationLog
    @PostMapping("/confirm")
    public BaseResponse confirm(@Valid @RequestBody RebateConfirmBean bean) {
        rebateService.confirm(bean, getCurrentUserId());
        return successResult();
    }

    /**
     * 上传附件
     * @param id
     * @param file
     * @return
     */
    @OperationLog
    @PostMapping("/upload/{id}")
    public BaseResponse upload(@PathVariable Integer id, MultipartFile file){
        return successResult(rebateService.fileUpload(id, getCurrentUserId(), file));
    }

    /**
     * 下载附件
     * @param id
     * @param response
     */
    @GetMapping("/download/{id}")
    public void download(@PathVariable Integer id, HttpServletResponse response){
        rebateService.fileDownload(id, response);
    }
}
