package com.crazy.portal.util;

import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.config.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.crazy.portal.util.ErrorCodes.BusinessEnum.*;

/**
 * Created by lee on 2019/7/27.
 */

@Slf4j
public class FileUtil {

    public static List<FileVO> upload(MultipartFile[] files, String filePath){
        List<FileVO> result = new ArrayList<>();

        if(files.length == 0 || StringUtils.isEmpty(filePath)){
            throw new BusinessException(FILE_PARAM_EMPTY);
        }
        try {
            long  startTime = System.currentTimeMillis();
            for(MultipartFile multipartFile: files) {
                if(multipartFile != null) {
                    String fileName = convertFileName(multipartFile.getOriginalFilename());
                    String fullPath = String.format("%s%s", filePath, fileName);
                    //上传
                    multipartFile.transferTo(new File(fullPath));
                    pushResultInfo(result, fileName, filePath, fullPath);
                }else{
                    log.info("没有拿到文件信息");
                }
            }
            if(log.isDebugEnabled()){
                log.debug("文件上传处理完成，耗时：{}", System.currentTimeMillis() - startTime);
            }
        }catch (IOException ex) {
            log.error("", ex);
        }catch (Exception ex) {
            log.error("", ex);
        }
        return result;
    }

    private static String convertFileName(String fileName) {
        if(StringUtils.isEmpty(fileName)){
            return null;
        }
        String[] array = fileName.split("\\.");
        long currentTime = System.currentTimeMillis();
        return String.format("%s_%s.%s", array[0], currentTime, array[1]);
    }

    private static void pushResultInfo(List<FileVO> result, String fileName, String filePath, String fullPath) {
        FileVO fileVO = new FileVO();
        fileVO.setFileName(fileName);
        fileVO.setFilePath(filePath);
        fileVO.setFullPath(fullPath);
        result.add(fileVO);
    }

}
