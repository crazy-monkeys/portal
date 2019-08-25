package com.crazy.portal.service.webservice.handler;

import com.crazy.portal.bean.webservice.AbstractHandler;
import com.crazy.portal.bean.webservice.IHandler;
import com.crazy.portal.bean.webservice.INTERFACE_CODE;
import com.crazy.portal.bean.webservice.request.IDRApprovalRequest;
import com.crazy.portal.bean.webservice.response.IDRApprovalResponse;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.service.business.IDRService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * @Description BPM报价申请审批结果同步至Portal
 * @Author Shawn
 * @Date 2019-08-23 23:09
 * @Modify by
 */
@Slf4j
@Component("bpm.business.idr.approval")
public class IDRReceiveHandler extends AbstractHandler implements IHandler<IDRApprovalRequest, IDRApprovalResponse> {

    @Resource
    private IDRService idrService;

    @Override
    public IDRApprovalResponse process(IDRApprovalRequest request) {
        IDRApprovalResponse result = new IDRApprovalResponse();
        try{
            idrService.receiveApproval(request);
            result.success();
        } catch (BusinessException e) {
            result.exception(e);
        } catch (Exception e){
            log.error("保差退审批同步异常", e);
            result.exception(INTERFACE_CODE.SYSTEM_ERROR);
        }
        return result;
    }
}
