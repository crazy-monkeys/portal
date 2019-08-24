package com.crazy.portal.bean.webservice;

import com.crazy.portal.config.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AbstractHandler {

    protected void checkParams(AbstractRequest request) throws Exception{
        List<String> checkParamList = request.checkParams();
        if(!checkParamList.isEmpty()){
            throw new BusinessException(INTERFACE_CODE.NO_ENTITY.getValue(),checkParamList.toString());
        }
    }

}
