package com.crazy.portal.service.handover;

import com.alibaba.fastjson.JSONObject;
import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.util.CallApiUtils;
import com.crazy.portal.util.Enums;
import com.crazy.portal.util.FTPClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

import static com.crazy.portal.util.ErrorCodes.BusinessEnum.*;

/**
 * Created by lee on 2019/8/24.
 */

@Slf4j
public abstract class AbstractHandover {
    @Resource
    private FTPClientUtil ftpClientUtil;

    private String SUCCESS_CODE = "OK";
    private String ERROR_CODE = "NG";

    protected abstract String pushFtpPath();

    protected abstract String pullLocalPath();

    protected BiCheckResult callBiServerByFtp(Enums.BI_FUNCTION_CODE functionCode, String filePath, String fileName, String pullPath) {
        try {
            log.info("ftp path:======================"+filePath+"/"+pullPath);
            //推送文件的地址信息
            String pushServerFile = String.format("%s%s", pushFtpPath(), fileName);
            String pushLocalFile = String.format("%s%s", filePath, fileName);

            String pushServerFile1 = String.format("%s%s", "bitest/upload/", fileName);

            if(log.isDebugEnabled()) {
                log.debug("Portal to BI >> Ftp file path:{} , Local file path:{}", pushServerFile, pushLocalFile);
            }
            ftpClientUtil.put(pushServerFile, pushLocalFile);
            BiCheckResult result = callBiServer(functionCode, pushServerFile1, "bitest/download/");
            log.info("BI handle result info >> {}", JSONObject.toJSONString(result));
            //获取文件的地址信息
            String biFileName = result.getFilePath().substring(result.getFilePath().lastIndexOf("/") + 1,result.getFilePath().lastIndexOf("\\") );
            String pullLocalFile = String.format("%s%s", pullPath, biFileName);

            log.info("BI to Portal info >> Ftp file path:{} , Local file path:{}", result.getFilePath(), pullLocalFile);
            result.setFilePath(pullLocalFile);
            return result;
            /*
            if(log.isDebugEnabled()) {
                log.debug("BI to Portal info >> Ftp file path:{} , Local file path:{}", result.getFilePath(), pullLocalFile);
            }
            ftpClientUtil.get(pullLocalPath() + biFileName, pullLocalFile);
            result.setFilePath(pullLocalFile);
            return result;*/
        }catch (Exception ex) {
            throw new RuntimeException("Ftp exception or bi server exception", ex);
        }
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
            String response = CallApiUtils.callBiApi(functionCode, pushPath, pullPath);
            if(StringUtils.isEmpty(response)){
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
