package com.crazy.portal.config.quartz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import java.util.Properties;

@Configuration
public class ExecutorConfig {

    @Resource
    private JobFactory jobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(jobFactory);
        Properties properties = new Properties();
        properties.setProperty("org.quartz.scheduler.skipUpdateCheck","true");
        factory.setQuartzProperties(properties);
        return factory;
    }
}
