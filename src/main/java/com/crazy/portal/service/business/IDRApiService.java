package com.crazy.portal.service.business;

import com.crazy.portal.util.CallApiUtils;
import com.crazy.portal.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Slf4j
@Service
public class IDRApiService {

    private static final String BPM_IDR_APPROVAL_URL = "/http/PORTAL/BPM/PROJECT_WEB_SUBMIT";
    /**
     * 提交保差退审批到BPM
     * @param requestBody 请求体
     * @return
     */
    public String portalSubmitApprovalToBPM(String requestBody) throws IOException {
        return HttpClientUtils.post(CallApiUtils.ECC_API_URL.concat(BPM_IDR_APPROVAL_URL), requestBody);
    }
}
