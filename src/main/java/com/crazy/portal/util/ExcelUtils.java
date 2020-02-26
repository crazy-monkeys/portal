package com.crazy.portal.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.excel.exception.ExcelGenerateException;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.crazy.portal.config.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.crazy.portal.util.ErrorCodes.BusinessEnum.EXCEL_READ_ERROR;
import static com.crazy.portal.util.ErrorCodes.BusinessEnum.EXCEL_TYPE_ERROR;
import static com.crazy.portal.util.ErrorCodes.BusinessEnum.EXCEL_WRITE_ERROR;

/**
 * @Description EasyExcel工具类
 * @Author Shawn
 * @Date 2019-08-01 21:51
 * @Modify by
 */
@Slf4j
public class ExcelUtils {

    private static int DEFAULT_SHEET_NO = 0;

    private static int DEFAULT_READ_LINE_NUM = 1;

    public static final String DEFAULT_SHEET_NAME = "Sheet1";

    /**
     * 导出excel 支持一张表导出多个sheet
     * @Param OutputStream 输出流
     * Map<String, List>  sheetName和每个sheet的数据
     * ExcelTypeEnum 要导出的excel的类型 有ExcelTypeEnum.xls 和有ExcelTypeEnum.xlsx
     */
    public static void createExcelStreamMutilByEaysExcel(HttpServletResponse response, Map<String, List> SheetNameAndDateList, String fileName, ExcelTypeEnum type) {
        if (checkParam(SheetNameAndDateList, type)){
            return;
        }
        try {
            fileName = URLEncoder.encode(fileName, "utf-8").concat(type.getValue());
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;fileName=\"".concat(fileName).concat("\"; filename*=\"utf-8''").concat(fileName).concat("\""+";filename*=utf-8''").concat(fileName));
            ExcelWriter writer = EasyExcel.write(response.getOutputStream()).build();
            int i = 0;
            for (Map.Entry<String, List> stringListEntry : SheetNameAndDateList.entrySet()) {
                WriteSheet writeSheet = EasyExcel.writerSheet(i, stringListEntry.getKey()).head(stringListEntry.getValue().get(0).getClass()).build();
                writer.write(stringListEntry.getValue(), writeSheet);
                i++;
            }
            writer.finish();
        } catch (IOException e) {
            log.error(EXCEL_WRITE_ERROR.getZhMsg(), e);
            throw new BusinessException(EXCEL_WRITE_ERROR);
        }
    }

    public static void writeExcel(HttpServletResponse response, List data, Class clazz) {
        try {
            if(data == null){
                data = new ArrayList();
            }
            String fileName = String.format("%s.%s", System.currentTimeMillis(), ExcelTypeEnum.XLSX);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setCharacterEncoding("UTF-8");
            EasyExcel.write(response.getOutputStream(), clazz).sheet(DEFAULT_SHEET_NAME).doWrite(data);
        }catch (Exception ex) {
            log.error(EXCEL_WRITE_ERROR.getZhMsg(), ex);
            throw new BusinessException(EXCEL_WRITE_ERROR);
        }
    }

    public static String writeExcel(String filePath, List data, Class clazz) {
        try {
            //暂时根据时间戳定义文件，防止名称一样
            String fileName = String.format("%s%s", System.currentTimeMillis(), ExcelTypeEnum.XLSX.getValue());
            File file = new File(String.format("%s%s", filePath, fileName));
            file.createNewFile();
            EasyExcel.write(file, clazz).sheet(DEFAULT_SHEET_NAME).doWrite(data);
            return fileName;
        }catch (Exception ex) {
            log.error(EXCEL_WRITE_ERROR.getZhMsg()+filePath, ex);
            throw new BusinessException(EXCEL_WRITE_ERROR);
        }
    }

    /**
     * 默认读取第一个sheet页，并从数据第二行开始读取数据，适用于第一行作为表头的Excel文档
     * @param filePath
     * @param fileName
     * @param clazz
     * @param <T>
     * @return
     */
    public static<T> List<T> readExcel(String filePath, String fileName, Class clazz) {
        return readExcel(filePath, fileName, clazz, DEFAULT_SHEET_NO);
    }

    /**
     * 读取指定sheet页，默认从数据第二行开始读取数据，适用于第一行作为表头的Excel文档
     * @param filePath
     * @param fileName
     * @param clazz
     * @param sheetNo
     * @param <T>
     * @return
     */
    public static<T> List<T> readExcel(String filePath, String fileName, Class clazz, int sheetNo) {
        return readExcel(filePath, fileName, clazz, sheetNo, DEFAULT_READ_LINE_NUM);
    }

    /**
     * 读取指定sheet页，指定数据行的Excel文档
     * @param filePath
     * @param fileName
     * @param clazz
     * @param sheetNo
     * @param headLineNum
     * @param <T>
     * @return
     */
    public static<T> List<T> readExcel(String filePath, String fileName, Class clazz, int sheetNo, int headLineNum){
        FileInputStream in;
        try {
            in = new FileInputStream(String.format("%s%s", filePath, fileName));
        }catch (FileNotFoundException ex) {
            log.warn("File does not exist, filePath:[{}], fileName:[{}]", filePath, fileName);
            return Collections.emptyList();
        }
        try {
            return readExcel(in, clazz, fileName, sheetNo, headLineNum);
        }catch (Exception ex) {
            log.error(EXCEL_READ_ERROR.getZhMsg(), ex);
            throw new BusinessException(EXCEL_READ_ERROR);
        }
    }

    public static<T> List<T> readExcel(String fullFilePath, Class clazz) {
        return readExcel(fullFilePath, clazz, DEFAULT_SHEET_NO);
    }

    public static<T> List<T> readExcel(String fullFilePath, Class clazz, int sheetNo) {
        return readExcel(fullFilePath, clazz, sheetNo, DEFAULT_READ_LINE_NUM);
    }

    public static<T> List<T> readExcel(String fullFilePath, Class clazz, int sheetNo, int headLineNum){
        FileInputStream in;
        try {
            in = new FileInputStream(fullFilePath);
        }catch (FileNotFoundException ex) {
            log.warn("File does not exist, fullFilePath:[{}]", fullFilePath);
            return Collections.emptyList();
        }
        try {
            return readExcel(in, clazz, fullFilePath, sheetNo, headLineNum);
        } catch (BusinessException e){
            throw e;
        } catch (ExcelAnalysisException e) {
            BusinessExceptionHandler(e);
            log.error(EXCEL_READ_ERROR.getZhMsg(), e);
            throw new BusinessException(EXCEL_READ_ERROR);
        } catch (Exception ex) {
            log.error(EXCEL_READ_ERROR.getZhMsg(), ex);
            throw new BusinessException(EXCEL_READ_ERROR);
        }
    }

    private static void BusinessExceptionHandler(ExcelAnalysisException e) {
        if(e.getCause() instanceof BusinessException){
            throw new BusinessException(((BusinessException) e.getCause()).getErrorCode(), e.getCause().getMessage());
        }
        if(e.getCause() instanceof ExcelGenerateException){
            String message = null;
            if(e.getCause().getCause() instanceof NumberFormatException){
                message = "不能转换为数字：".concat(getIndexMessage(e.getCause().getCause().getMessage()));
            }
            if(e.getCause().getCause() instanceof BusinessException){
                throw (BusinessException)e.getCause().getCause();
            }
            throw new BusinessException(ErrorCodes.BusinessEnum.EXCEL_PARAM_FAIL.getCode(), ErrorCodes.BusinessEnum.EXCEL_PARAM_FAIL.getZhMsg().concat("，").concat(message));
        }
    }

    public static String getIndexMessage(String message){
        if(StringUtil.isBlank(message) || message.indexOf("\"") == -1){
            return "未知列";
        }
        return message.substring(message.indexOf("\"") + 1, message.lastIndexOf("\""));
    }
    /**
     * 默认读取第一个sheet页，并从数据第二行开始读取数据，适用于第一行作为表头的Excel文档
     * @param excel
     * @param clazz
     * @param <T>
     * @return
     */
    public static<T> List<T> readExcel(MultipartFile excel, Class clazz) {
        return readExcel(excel, clazz, DEFAULT_SHEET_NO);
    }

    /**
     * 读取指定sheet页，默认从数据第二行开始读取数据，适用于第一行作为表头的Excel文档
     * @param excel
     * @param clazz
     * @param sheetNo
     * @param <T>
     * @return
     */
    public static<T> List<T> readExcel(MultipartFile excel, Class clazz, int sheetNo) {
        return readExcel(excel, clazz, sheetNo, DEFAULT_READ_LINE_NUM);
    }

    /**
     * 读取指定sheet页，指定数据行的Excel文档
     * @param excel
     * @param clazz
     * @param sheetNo
     * @param headLineNum
     * @param <T>
     * @return
     */
    public static<T> List<T> readExcel(MultipartFile excel, Class clazz, int sheetNo, int headLineNum) {
        if(null == excel){
            log.warn("Excel is null");
            return Collections.emptyList();
        }
        try {
            return readExcel(excel.getInputStream(), clazz, excel.getOriginalFilename(), sheetNo, headLineNum);
        } catch (ExcelAnalysisException e) {
            BusinessExceptionHandler(e);
            log.error(EXCEL_READ_ERROR.getZhMsg(), e);
            throw new BusinessException(EXCEL_READ_ERROR);
        }catch (Exception ex) {
            log.error(EXCEL_READ_ERROR.getZhMsg(), ex);
            throw new BusinessException(EXCEL_READ_ERROR);
        }
    }

    private static<T> List<T> readExcel(InputStream in, Class clazz, String fileName, int sheetNo, int headLineNum) {
        checkExcelType(fileName);
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = EasyExcel.read(in).autoTrim(false).build();
        ReadSheet readSheet1 = EasyExcel.readSheet(sheetNo).headRowNumber(headLineNum).head(clazz).registerReadListener(excelListener).build();
        reader.read(readSheet1);
        List<T> data = excelListener.getData();
        reader.finish();
        BusinessUtil.assertTrue((data != null && data.size() > 0), ErrorCodes.BusinessEnum.EXCEL_FILE_IS_NULL);
        return data;
    }

    /**
     * 校验Excel文件后缀是否符合要求
     * @param fileName 文件名
     */
    private static void checkExcelType(String fileName) {
        fileName = null == fileName ? "" : fileName.toLowerCase();
        if(!(fileName.endsWith(ExcelTypeEnum.XLS.getValue()) || fileName.endsWith(ExcelTypeEnum.XLSX.getValue()))){
            log.error("Excel type is error, fullName : {}", fileName);
            throw new BusinessException(EXCEL_TYPE_ERROR);
        }
    }

    /**
     * 校验参数
     */
    private static boolean checkParam(Map<String, List> SheetNameAndDateList, ExcelTypeEnum type) {
        if (CollectionUtils.isEmpty(SheetNameAndDateList)) {
            log.error("SheetNameAndDateList不能为空");
            return true;
        } else if (type == null) {
            log.error("导出的excel类型不能为空");
            return true;
        }
        return false;
    }
}
