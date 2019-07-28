package com.crazy.portal.util;

import com.alibaba.fastjson.util.IOUtils;
import com.crazy.portal.bean.customer.basic.FileVO;
import com.crazy.portal.config.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.crazy.portal.util.ErrorCodes.BusinessEnum.*;

/**
 * Created by lee on 2019/7/27.
 */

@Slf4j
public class FileUtil {

    /**
     * 文件下载
     * @param response  Servlet
     * @param filePath  文件存放路径
     * @param fileName  文件名
     */
    public static void download(HttpServletResponse response, String filePath, String fileName) {
        long startTime = System.currentTimeMillis();
        if(StringUtils.isEmpty(filePath)){
            log.error("File path is not empty");
            throw new BusinessException(FILE_DOWNLOAD_PARAM_EMPTY);
        }
        if(StringUtils.isEmpty(fileName)){
            log.error("File name is not empty");
            throw new BusinessException(FILE_DOWNLOAD_PARAM_EMPTY);
        }
        String fullPath = String.format("%s%s", filePath, fileName);
        File file = new File(fullPath);
        if(!file.exists()){
            log.error("File not found , [filePath]:{} , [fileName]:{}", filePath, fileName);
            throw new BusinessException(FILE_DOWNLOAD_FILE_NOT_FOUND);
        }
        if(log.isDebugEnabled()){
            log.debug("File found in path, [fullPath]:{}", fullPath);
        }
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        ServletOutputStream out = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            out = response.getOutputStream();
            int b = 0;
            byte[] buffer = new byte[512];
            while (b != -1){
                b = inputStream.read(buffer);
                out.write(buffer,0,b);
            }
            if(log.isDebugEnabled()){
                log.debug("File download takes {} ms", System.currentTimeMillis() - startTime);
            }
        } catch (Exception ex) {
            log.error("Exception in file download", ex);
        }finally {
            IOUtils.close(inputStream);
            IOUtils.close(out);
        }
    }

    public static List<FileVO> upload(MultipartFile[] files, String filePath){
        List<FileVO> result = new ArrayList<>();
        BusinessUtil.assertFlase(files.length == 0 || StringUtils.isEmpty(filePath), FILE_UPLOAD_PARAM_EMPTY);
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
