package com.crazy.portal.service.webservice.handler;

import com.crazy.portal.bean.webservice.AbstractHandler;
import com.crazy.portal.bean.webservice.IHandler;
import com.crazy.portal.bean.webservice.request.MemberInfoSyncRequest;
import com.crazy.portal.bean.webservice.response.MemberInfoSyncResponse;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MemberInfoSyncHandler
 * @Author: God Man Qiu~
 * @Date: 2019/8/27 13:24
 */
@Component(value = "portal.member.sync")
public class MemberInfoSyncHandler extends AbstractHandler implements IHandler<MemberInfoSyncRequest,MemberInfoSyncResponse>{
    @Override
    public MemberInfoSyncResponse process(MemberInfoSyncRequest request) {
        MemberInfoSyncResponse response = new MemberInfoSyncResponse();
        response.success();
        return response;
    }
}
