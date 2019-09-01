package com.crazy.portal.util;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.crazy.portal.config.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lee on 2019/3/15.
 */
@Slf4j
public class ExcelListener<T> extends AnalysisEventListener<T> {

    private List<T> data = new ArrayList<>();

    public ExcelListener(){
    }

    @Override
    public void invoke(T t, AnalysisContext context) {
        checkPropertyLength(t, context);
        data.add(t);
    }

    private void checkPropertyLength(T t, AnalysisContext context) {
        int headCount = context.getExcelHeadProperty().getColumnPropertyList().size();
        Field[] fields = t.getClass().getDeclaredFields();
        int classFieldCount = 0;
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ExcelProperty anno = field.getAnnotation(ExcelProperty.class);
            if (anno != null) {
                classFieldCount++;
            }
        }
        BusinessUtil.assertTrue(headCount == classFieldCount, ErrorCodes.BusinessEnum.EXCEL_PROPERTY_DIFF);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
//        datas.clear();
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
