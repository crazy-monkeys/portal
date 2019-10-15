package com.crazy.portal.controller.business;

import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.business.idr.BusinessIdrQueryBean;
import com.crazy.portal.bean.business.idr.IdrUploadBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.business.idr.BusinessIdrInfo;
import com.crazy.portal.service.business.IDRService;
import com.crazy.portal.util.Enums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by bill on 2019/7/30.
 * I: insurance 保价
 * D: diff 差价
 * R: returns 退货
 */
@Slf4j
@RestController
@RequestMapping("/business/idr")
public class IDRController extends BaseController {

    @Resource
    private IDRService idrService;

    /**
     * 查询列表
     * @return
     */
    @GetMapping("/list")
    public BaseResponse list(BusinessIdrQueryBean bean){
        if(super.getCurrentUser().getUserType().equals(Enums.USER_TYPE.agent.toString())){
            bean.setDealerId(super.getCurrentUserId());
        }
        return super.successResult(idrService.selectByPage(bean));
    }

    /**
     * 查询明细
     * @param id
     * @return
     */
    @GetMapping("/find/{id}")
    public BaseResponse find(@PathVariable Integer id){
        return super.successResult(idrService.selectIdrDetail(id));
    }

    /**
     * 模板下载
     * @param type
     * @param response
     */
    @GetMapping("/templateDownload")
    public void templateDownload(Integer type, HttpServletResponse response) {
        idrService.templateDownload(type, response);
    }
    /**
     * 上传附件
     * @param bean
     * @return
     */
    @OperationLog
    @PostMapping("/upload")
    public BaseResponse upload(IdrUploadBean bean) {
        return super.successResult(idrService.upload(bean, this.getCurrentUserId()));
    }

    /**
     * 提交信息
     * @param bean
     * @return
     */
    @OperationLog
    @PostMapping("/submit")
    public BaseResponse submit(@RequestBody @Valid BusinessIdrInfo bean) {
        idrService.submit(bean, this.getCurrentUserId());
        return super.successResult();
    }


}
