package com.crazy.portal.util;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.crazy.portal.config.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.crazy.portal.util.ErrorCodes.BusinessEnum.*;

/**
 * @Description EasyExcel工具类
 * @Author Shawn
 * @Date 2019-08-01 21:51
 * @Modify by
 */
@Slf4j
public class ExcelUtils {

    /**
     * 导出excel 支持一张表导出多个sheet
     * @Param OutputStream 输出流
     * Map<String, List>  sheetName和每个sheet的数据
     * ExcelTypeEnum 要导出的excel的类型 有ExcelTypeEnum.xls 和有ExcelTypeEnum.xlsx
     */
    public static void createExcelStreamMutilByEaysExcel(HttpServletResponse response, Map<String, List<? extends BaseRowModel>> SheetNameAndDateList, ExcelTypeEnum type) throws UnsupportedEncodingException {
        if (checkParam(SheetNameAndDateList, type)){
            return;
        }
        try {
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + "default" + type.getValue());
            ServletOutputStream out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, type, true);
            setSheet(SheetNameAndDateList, writer);
            writer.finish();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static<T> List<T> readExcel(MultipartFile excel, Class clazz, int sheetNo) {
        return readExcel(excel, clazz, sheetNo, 1);
    }


    public static<T> List<T> readExcel(MultipartFile excel, Class clazz, int sheetNo, int headLineNum) {
        if(null == excel){
            log.warn("Excel is null");
            return Collections.emptyList();
        }
        ExcelListener excelListener = new ExcelListener();
        try {
            ExcelReader reader = getReader(excel, excelListener);
            Sheet sheet = new Sheet(sheetNo, headLineNum, clazz);
            reader.read(sheet);
        }catch (Exception ex) {
            log.error("Read excel exception", ex);
            throw new BusinessException(EXCEL_READ_ERROR);
        }
        return excelListener.getData();
    }

    /**
     *
     * @param excel
     * @param excelListener
     * @return
     * @throws IOException
     */
    private static ExcelReader getReader(MultipartFile excel, ExcelListener excelListener) throws IOException {
        String lowerFileName = null == excel.getOriginalFilename() ? "" : excel.getOriginalFilename().toLowerCase();
        if(!(lowerFileName.endsWith(ExcelTypeEnum.XLS.getValue()) || lowerFileName.endsWith(ExcelTypeEnum.XLSX.getValue()))){
            log.error("Excel type is error, fullName : {}", lowerFileName);
            throw new BusinessException(EXCEL_TYPE_ERROR);
        }
        InputStream inputStream = new BufferedInputStream(excel.getInputStream());
        return new ExcelReader(inputStream, null, excelListener, false);
    }

    /**
     * setSheet数据
     */
    private static void setSheet(Map<String, List<? extends BaseRowModel>> SheetNameAndDateList, ExcelWriter writer) {
        int sheetNum = 1;
        for (Map.Entry<String, List<? extends BaseRowModel>> stringListEntry : SheetNameAndDateList.entrySet()) {
            Sheet sheet = new Sheet(sheetNum, 0, stringListEntry.getValue().get(0).getClass());
            sheet.setSheetName(stringListEntry.getKey());
            writer.write(stringListEntry.getValue(), sheet);
            sheetNum++;

        }
    }

    /**
     * 校验参数
     */
    private static boolean checkParam(Map<String, List<? extends BaseRowModel>> SheetNameAndDateList, ExcelTypeEnum type) {
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
