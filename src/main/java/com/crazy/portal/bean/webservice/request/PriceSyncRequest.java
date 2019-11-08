package com.crazy.portal.bean.webservice.request;

import com.crazy.portal.bean.webservice.AbstractRequest;
import com.crazy.portal.bean.webservice.IRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName: PriceSyncRequest
 * @Author: God Man Qiu~
 * @Date: 2019/11/8 16:32
 */
@Getter
@Setter
public class PriceSyncRequest extends AbstractRequest implements IRequest<PriceSyncRequest> {
    protected static final String[] PARAM_NAMES = new String[] {};

    @Override
    public List<String> checkParams(){
        //首先检查接口约定参数
        List<String> commonList = super.checkParams();
        return commonList;
    }
}