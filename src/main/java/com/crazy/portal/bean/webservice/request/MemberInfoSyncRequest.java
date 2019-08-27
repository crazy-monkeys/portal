package com.crazy.portal.bean.webservice.request;

import com.crazy.portal.bean.webservice.AbstractRequest;
import com.crazy.portal.bean.webservice.IRequest;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MemberInfoSyncRequest
 * @Author: God Man Qiu~
 * @Date: 2019/8/27 13:24
 */
public class MemberInfoSyncRequest extends AbstractRequest implements IRequest<MemberInfoSyncRequest>{
    protected static final String[] PARAM_NAMES = new String[] {};

    @Override
    public List<String> checkParams(){
        //首先检查接口约定参数
        List<String> commonList = super.checkParams();
        if (commonList.size() > 0) {
            return commonList;
        }
        Map<String, String> paramsMaps = super.getParamsValue();
        List<String> errorMsgLists = new ArrayList<String>();

        return errorMsgLists;
    }

    @Override
    public String[] getParamNames() {
        return (String[]) ArrayUtils.addAll(super.getParamNames(), PARAM_NAMES);
    }
}
