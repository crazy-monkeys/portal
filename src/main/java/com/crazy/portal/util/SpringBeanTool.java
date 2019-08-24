package com.crazy.portal.util;

import org.springframework.context.ApplicationContext;

/**
 * @Desc:
 * @Author: Bill
 * @Date: created in 14:03 2019/4/23
 * @Modified by:
 */
public class SpringBeanTool {
    public static ApplicationContext applicationContext;


    public static void setApplicationContext(ApplicationContext applicationContext){
        SpringBeanTool.applicationContext = applicationContext;
    }

    public static <T> T getBean(String name){
        return (T)applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    public static String getActiveProfile() {
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }
}
