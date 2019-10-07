package com.crazy.portal.util;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
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
        Integer excelHeadCount = context.getExcelHeadProperty().getColumnPropertyList().size();
        Long classFieldCount = Arrays.stream(t.getClass().getDeclaredFields()).filter(e->e.getAnnotation(ExcelProperty.class) != null).count();
        BusinessUtil.assertTrue(excelHeadCount.intValue() == classFieldCount.intValue(), ErrorCodes.BusinessEnum.EXCEL_PROPERTY_DIFF);
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
