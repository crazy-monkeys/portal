package com.crazy.portal.service.handover;

import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.util.Enums;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import java.util.Date;

import static com.crazy.portal.util.ErrorCodes.BusinessEnum.*;

/**
 * Created by lee on 2019/8/24.
 */

@Slf4j
public abstract class AbstractHandover {

    private String SUCCESS_CODE = "OK";
    private String ERROR_CODE = "NG";

    /**
     *
     * @param functionCode
     * @param pushPath
     * @param pullPath
     * @return
     */
    protected BiCheckResult callBiServer(Enums.BI_FUNCTION_CODE functionCode, String pushPath, String pullPath) {
        try {
            String response;
            if(functionCode == Enums.BI_FUNCTION_CODE.CHECK_SALES_IMPORT_FILE){
                response = mockThirdResult() ? "\"OK:/Users/lee/Documents/job_code/portal_file/pull_thrid/deliver/ok.xlsx\"" :
                        "\"NG:/Users/lee/Documents/job_code/portal_file/pull_thrid/deliver/error.xlsx\"";
            }else{
                response = mockThirdResult() ? "\"OK:/Users/lee/Documents/job_code/portal_file/pull_thrid/receive/ok.xlsx\"" :
                        "\"NG:/Users/lee/Documents/job_code/portal_file/pull_thrid/receive/error.xlsx\"";
            }
//            String response = CallApiUtils.BITest(functionCode, pushPath, pullPath);
            if(StringUtils.isEmpty(response)){
                log.error("{} -> {}", HANDOVER_BI_RESPONSE_EXCEPTION.getZhMsg(), response);
                throw new BusinessException(HANDOVER_BI_RESPONSE_EXCEPTION);
            }
            response = response.replace("\"", "");
            if(log.isDebugEnabled()){
                log.debug("Bi server response info -> pushPath:[{}], pullPath:[{}], response:[{}]", pushPath, pullPath, response);
            }
            String fileName = response.split(":")[1];
            if(response.contains(SUCCESS_CODE)){
                return new BiCheckResult(true, fileName);
            }
            if(response.contains(ERROR_CODE)){
                return new BiCheckResult(false, fileName);
            }
            log.error(HANDOVER_BI_RESPONSE_EXCEPTION.getZhMsg(), response);
            throw new BusinessException(HANDOVER_BI_RESPONSE_EXCEPTION);

        }catch (Exception ex) {
            log.error(HANDOVER_BI_SERVER_EXCEPTION.getZhMsg(), ex);
            throw new BusinessException(HANDOVER_BI_SERVER_EXCEPTION);
        }
    }

    /**
     * 模拟第三方结果
     * 截取时间戳最后一个数字，0 ～ 4：false 5 ～ 9：true
     * @return
     */
    private boolean mockThirdResult() {
        String time = String.valueOf(new Date().getTime());
        int len = time.length();
        int i = Integer.parseInt(String.valueOf(time.charAt(len - 1)));
        return i > 4;
    }

    class BiCheckResult {

        //是否成功处理
        private boolean isSuccess = false;
        //文件地址（完整路径+文件名称）
        private String filePath;

        BiCheckResult() {
        }

        public BiCheckResult(boolean isSuccess, String filePath) {
            this.isSuccess = isSuccess;
            this.filePath = filePath;
        }

        public boolean isSuccess() {
            return isSuccess;
        }

        public String getFilePath() {
            return filePath;
        }
    }

}
