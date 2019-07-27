package com.crazy.portal.util;

import com.crazy.portal.config.exception.BusinessException;
import com.crazy.portal.config.exception.ErrorInfo;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by lee on 2019/7/27.
 */
public class BusinessUtil {

    public static void assertTrue(boolean bool, ErrorInfo errorInfo) {
        if(!bool){
            throw new BusinessException(errorInfo);
        }
    }

    public static void assertFlase(boolean bool, ErrorInfo errorInfo) {
        if(bool){
            throw new BusinessException(errorInfo);
        }
    }

    public static void assertNotEmpty(String value, ErrorInfo errorInfo) {
        if(StringUtils.isEmpty(value)){
            throw new BusinessException(errorInfo);
        }
    }

    public static void assertEmpty(String value, ErrorInfo errorInfo) {
        if(StringUtils.isNotEmpty(value)){
            throw new BusinessException(errorInfo);
        }
    }

}
