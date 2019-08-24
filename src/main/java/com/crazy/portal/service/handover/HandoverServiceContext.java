package com.crazy.portal.service.handover;

import com.crazy.portal.util.BusinessUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

import static com.crazy.portal.util.ErrorCodes.BusinessEnum.HANDOVER_PARAM_TYPE_ERROR;

/**
 * Created by lee on 2019/8/24.
 */

@Component
public class HandoverServiceContext {

    private String deliver_type = "deliver";
    private String receive_type = "receive";

    @Autowired
    private Map<String, IHandover> handoverServiceMap;

    public IHandover getService(String type) {
        checkHandoverValue(type);
        return handoverServiceMap.get(type);
    }

    private void checkHandoverValue(String type) {
        boolean condition = !deliver_type.equals(type) && !receive_type.equals(type);
        BusinessUtil.assertFlase(condition, HANDOVER_PARAM_TYPE_ERROR);
    }
}
