package com.crazy.portal.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {
    private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);
    private BeanUtils() {
        throw new IllegalStateException("BeanUtils class");
    }

   /**
     * 用于model修改时的对象复制,把srcModel复制到destModel,srcModel中为null的字段不复制，同名且类型相同的属性才复制
     * 
     * @param srcModel
     *            表单提交的源对象
     * @param destModel
     *            数据库中的目标对象
     */
    public static void copyNotNullFields(Object srcModel, Object destModel) throws IntrospectionException,
            IllegalAccessException, InvocationTargetException{
        if (srcModel == null || destModel == null) {
            return;
        }
         invokePropertyCopy(srcModel, destModel);
    }

    private static void invokePropertyCopy(Object srcModel, Object destModel) throws IntrospectionException,
            IllegalAccessException, InvocationTargetException {
    	
        PropertyDescriptor[] srcDescriptors = Introspector.getBeanInfo(srcModel.getClass()).getPropertyDescriptors();
        PropertyDescriptor[] destDescriptors = Introspector.getBeanInfo(destModel.getClass()).getPropertyDescriptors();
        Map<String, PropertyDescriptor> destPropertyNameDescriptorMap = new HashMap<>();
        for (PropertyDescriptor destPropertyDescriptor : destDescriptors) {
            destPropertyNameDescriptorMap.put(destPropertyDescriptor.getName(), destPropertyDescriptor);
        }
        for (PropertyDescriptor srcDescriptor : srcDescriptors) {
            PropertyDescriptor destDescriptor = destPropertyNameDescriptorMap.get(srcDescriptor.getName());
            // 类型相同的属性才复制
            if (destDescriptor != null && destDescriptor.getPropertyType() == srcDescriptor.getPropertyType()
                    && destDescriptor.getPropertyType() != Class.class) {
                Object val = srcDescriptor.getReadMethod().invoke(srcModel);
                if (val != null && destDescriptor.getWriteMethod() != null) {
                    destDescriptor.getWriteMethod().invoke(destModel, val);
                }
            }
        }

    }
}
