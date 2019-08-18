package com.crazy.portal.controller.business;

import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.business.rebate.RebateQueryBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.business.rebate.BusinessRebate;
import com.crazy.portal.service.business.RebateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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

    @GetMapping("/list")
    public BaseResponse list(RebateQueryBean bean){
        return successResult(rebateService.list(bean));
    }

    @GetMapping("/find/{id}")
    public BaseResponse find(@PathVariable Integer id){
        return successResult(rebateService.find(id));
    }

    @PostMapping("/confirm")
    public BaseResponse confirm(@RequestBody BusinessRebate bean){
        rebateService.confirm(bean, getCurrentUserId());
        return successResult();
    }

    @PostMapping("/upload/{id}")
    public BaseResponse upload(@PathVariable Integer id, MultipartFile file){
        return successResult(rebateService.fileUpload(id, getCurrentUserId(), file));
    }

    @GetMapping("/download/{id}")
    public void download(@PathVariable Integer id, HttpServletResponse response){
        rebateService.fileDownload(id, response);
    }
}
