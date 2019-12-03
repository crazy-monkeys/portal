package com.crazy.portal.config.validator;

import com.alibaba.fastjson.serializer.BigDecimalCodec;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @Description: BigDecimal格式转换
 * @Author Shawn
 * @Date 2019-12-03 16:10
 * @Modify by
 */
@Slf4j
public class BigDecimalFormat extends BigDecimalCodec {

    public final static BigDecimalFormat instance = new BigDecimalFormat();

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        try{
            SerializeWriter out = serializer.out;
            if(object == null) {
                out.write(0);
                return;
            }
            DecimalFormat decimalFormat = new DecimalFormat("#0.00000");
            NumberFormat numberFormat = NumberFormat.getInstance();
            out.write(numberFormat.parse(decimalFormat.format(object)).toString());
        }catch (Exception e){
            log.error("BigDecimal转换异常", e);
        }
    }
}
