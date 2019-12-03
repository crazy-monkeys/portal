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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
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
        ServletOutputStream out = null;
        FileInputStream inputStream = null;
        try {
            response.setContentType("multipart/form-data");
            fileName = URLEncoder.encode(fileName,"utf-8");
            response.setHeader("Content-disposition", "attachment;fileName=\"".concat(fileName).concat("\"; filename*=\"utf-8''").concat(fileName).concat("\""+";filename*=utf-8''").concat(fileName));
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

    /**
     * 多文件保存
     * @param files 文件
     * @param filePath  文件保存路径
     * @return
     */
    public static List<FileVO> upload(MultipartFile[] files, String filePath){
        List<FileVO> result = new ArrayList<>();
        if(StringUtils.isEmpty(filePath) || null == files){
            log.error("File path is not empty or [files] is null");
            throw new BusinessException(FILE_UPLOAD_PARAM_EMPTY);
        }
        if(files.length == 0){
            log.warn("File size is zero");
            return Collections.emptyList();
        }
        try {
            if(checkDirExists(filePath)) {
                long startTime = System.currentTimeMillis();
                for (MultipartFile multipartFile : files) {
                    if (multipartFile != null) {
                        String fileName = convertFileName(multipartFile.getOriginalFilename());
                        String fullPath = String.format("%s%s", filePath, fileName);
                        multipartFile.transferTo(new File(fullPath));
                        pushResultInfo(result, fileName, filePath, fullPath);
                    }
                }
                if (log.isDebugEnabled()) {
                    log.debug("File upload takes {} ms", System.currentTimeMillis() - startTime);
                }
            }
        }catch (Exception ex) {
            log.error("Exception in file upload", ex);
        }
        return result;
    }

    public static FileVO upload(MultipartFile file, String filePath){
        List<FileVO> fileVos = upload(new MultipartFile[]{file}, filePath);
        if(fileVos != null && fileVos.size() > 0){
            return fileVos.get(0);
        }
        return null;
    }

    /**
     * 检查文件路径是否存在，不存在则进行创建
     * @param filePath
     */
    private static boolean checkDirExists(String filePath) {
        if(StringUtil.isBlank(filePath)){
            return false;
        }
        File fileDir = new File(filePath);
        if(!fileDir.exists()){
            log.warn("File path is not exists , create file path : {}", filePath);
            return fileDir.mkdir();
        }
        return true;
    }

    private static String convertFileName(String fileName) {
        if(StringUtils.isEmpty(fileName)){
            return null;
        }
        int index = fileName.lastIndexOf(".");
        String perfix = fileName;
        String subfix = null;
        if(index != -1){
            perfix = fileName.substring(0, index);
            subfix = fileName.substring(index + 1, fileName.length());
        }
        long currentTime = System.currentTimeMillis();
        if(subfix == null){
            return String.format("%s_%s", perfix, currentTime);
        }else{
            return String.format("%s_%s.%s", perfix, currentTime, subfix);
        }
    }

    private static void pushResultInfo(List<FileVO> result, String fileName, String filePath, String fullPath) {
        FileVO fileVO = new FileVO();
        fileVO.setFileName(fileName);
        fileVO.setFilePath(filePath);
        fileVO.setFullPath(fullPath);
        result.add(fileVO);
    }
}
