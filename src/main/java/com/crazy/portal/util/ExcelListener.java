package com.crazy.portal.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lee on 2019/3/15.
 */
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
        int classFieldCount = t.getClass().getDeclaredFields().length;
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
