package com.crazy.portal.task.common;

import com.crazy.portal.service.task.ScheduleJobService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


/**
 * 定时任务初始化
 * @author zq
 *
 */
@Component
@Slf4j
public class SystemInitEvent {

    /** 定时任务service */
    @Resource
    private ScheduleJobService scheduleJobService;

    /**
     * 项目启动时初始化
     */
    @PostConstruct
    public void init() {
        log.info(">  >  >  >  >  schedule jobs init [begin]      >  >  >  >  >  ");
        scheduleJobService.initScheduleJob();
        log.info(">  >  >  >  >  schedule jobs init [end]      >  >  >  >  >  ");
    }
}
