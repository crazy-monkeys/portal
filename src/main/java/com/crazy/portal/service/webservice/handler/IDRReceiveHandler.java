package com.crazy.portal.service.webservice.handler;

import com.crazy.portal.bean.webservice.AbstractHandler;
import com.crazy.portal.bean.webservice.IHandler;
import com.crazy.portal.bean.webservice.request.IDRApprovalRequest;
import com.crazy.portal.bean.webservice.response.IDRApprovalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description TODO
 * @Author Shawn
 * @Date 2019-08-23 23:09
 * @Modify by
 */
@Slf4j
@Component("bpm.business.idr.approval")
public class IDRReceiveHandler extends AbstractHandler implements IHandler<IDRApprovalRequest, IDRApprovalResponse> {

    /**
     * BPM报价申请审批结果同步至Portal
     * @param bean
     * @return
     */
    public IDRApprovalResponse approval(@RequestBody IDRApprovalRequest bean){
        IDRApprovalResponse result = new IDRApprovalResponse();

        return result;
    }

    @Override
    public IDRApprovalResponse process(IDRApprovalRequest request) {
        IDRApprovalResponse result = new IDRApprovalResponse();
        try{

            result.success();
        }catch (Exception e){
            log.error("保差退审批同步异常", e);
        }
        return result;
    }
}
