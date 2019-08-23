package com.crazy.portal.controller.webservice.business;

import com.crazy.portal.bean.business.idr.call.ApprovalRequestBean;
import com.crazy.portal.bean.business.idr.call.ApprovalResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author Shawn
 * @Date 2019-08-23 23:09
 * @Modify by
 */
@Slf4j
@RestController
public class IDRReceiveController {

    /**
     * BPM报价申请审批结果同步至Portal
     * @param bean
     * @return
     */
    @RequestMapping("/bpm/idr/approval")
    public ApprovalResult approval(@RequestBody ApprovalRequestBean bean){
        ApprovalResult result = new ApprovalResult();
        try{
            result.success();
        }catch (Exception e){
            log.error("保差退审批同步异常", e);
            result.fail("");
        }
        return result;
    }
}
