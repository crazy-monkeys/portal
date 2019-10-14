package com.crazy.portal.controller.business;

import com.crazy.portal.annotation.OperationLog;
import com.crazy.portal.bean.BaseResponse;
import com.crazy.portal.bean.business.rebate.RebateConfirmBean;
import com.crazy.portal.bean.business.rebate.RebateQueryBean;
import com.crazy.portal.bean.business.rebate.RebateUploadBean;
import com.crazy.portal.controller.BaseController;
import com.crazy.portal.entity.system.User;
import com.crazy.portal.service.business.RebateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
        bean.setDealerId(getCurrentUser().getDealerId());
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
     * @param bean
     * @return
     */
    @OperationLog
    @PostMapping("/upload")
    public BaseResponse upload(@Valid RebateUploadBean bean){
        User user = getCurrentUser();
        return successResult(rebateService.fileUpload(bean, user.getId(), user.getDealerId()));
    }

    /**
     * 下载附件
     * @param id
     */
    @GetMapping("/download/{id}")
    public BaseResponse download(@PathVariable Integer id){
        return successResult(rebateService.fileDownload(id));
    }

    /**
     * 修改备注
     * @param id
     * @param remark
     * @return
     */
    @OperationLog
    @PostMapping("/modifyRemark")
    public BaseResponse modifyRemark(Integer id, String remark){
        rebateService.modifyRemark(id, remark, getCurrentUserId());
        return successResult();
    }

}
