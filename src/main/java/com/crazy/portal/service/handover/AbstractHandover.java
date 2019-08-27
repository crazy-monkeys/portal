package com.crazy.portal.service.handover;

import com.alibaba.fastjson.JSONObject;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.util.Enums;
import com.crazy.portal.util.FTPClientUtil;
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

    FTPClientUtil ftp = new FTPClientUtil("10.11.14.50", 22, "vmuser", "");

    protected abstract String pushFtpPath();

    protected abstract String pullLocalPath();

    protected BiCheckResult callBiServerByFtp(Enums.BI_FUNCTION_CODE functionCode, String filePath, String fileName, String pullPath) {
        try {
            //推送文件的地址信息
            String pushServerFile = String.format("%s%s", pushFtpPath(), fileName);
            String pushLocalFile = String.format("%s%s", filePath, fileName);
            if(log.isDebugEnabled()) {
                log.debug("Portal to BI >> Ftp file path:{} , Local file path:{}", pushServerFile, pushLocalFile);
            }
            ftp.put(pushServerFile, pushLocalFile);
            BiCheckResult result = callBiServer(functionCode, pushServerFile, pullLocalPath());
            log.info("BI handle result info >> {}", JSONObject.toJSONString(result));
            //获取文件的地址信息
            String biFileName = result.getFilePath().substring(result.getFilePath().lastIndexOf("/") + 1);
            String pullLocalFile = String.format("%s%s", pullPath, biFileName);
            if(log.isDebugEnabled()) {
                log.debug("BI to Portal info >> Ftp file path:{} , Local file path:{}", result.getFilePath(), pullLocalFile);
            }
            ftp.get(result.getFilePath(), pullLocalFile);
            result.setFilePath(pullLocalFile);
            return result;
        }catch (Exception ex) {
            throw new RuntimeException("Ftp exception or bi server exception", ex);
        }
    }

    public static void main(String[] args) {
        System.out.println("/a/b/c/d.txt".substring("/a/b/c/d.txt".lastIndexOf("/") + 1));
    }

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

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }
    }

}
