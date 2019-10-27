package com.crazy.portal.util;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.metadata.Head;
import com.crazy.portal.config.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        Integer excelHeadCount = context.currentReadHolder().excelReadHeadProperty().getHeadMap().entrySet().size();
        Long classFieldCount = Arrays.stream(t.getClass().getDeclaredFields()).filter(e->e.getAnnotation(ExcelProperty.class) != null).count();
        BusinessUtil.assertTrue(excelHeadCount.intValue() == classFieldCount.intValue(), ErrorCodes.BusinessEnum.EXCEL_PROPERTY_DIFF);
    }
    //二期开启
//    @Override
//    public void onException(Exception exception, AnalysisContext context) throws Exception {
//        Map<Integer, Head> headMap = context.currentReadHolder().excelReadHeadProperty().getHeadMap();
//        if (exception instanceof ExcelDataConvertException) {
//            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
//            Integer rowIndex = excelDataConvertException.getRowIndex();
//            Integer columnIndex = excelDataConvertException.getColumnIndex();
//            throw new BusinessException(ErrorCodes.BusinessEnum.EXCEL_PARAM_FAIL.getCode(),
//                    ErrorCodes.BusinessEnum.EXCEL_PARAM_FAIL.getZhMsg().concat("，")
//                    .concat("第"+rowIndex.toString()+"行")
//                    .concat("第"+columnIndex.toString()+"列")
//                    .concat("的"+headMap.get(columnIndex).getHeadNameList().get(0)));
//        }
//        throw exception;
//    }

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
