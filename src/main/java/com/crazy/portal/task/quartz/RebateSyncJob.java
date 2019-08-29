package com.crazy.portal.task.quartz;


import com.crazy.portal.config.quartz.annotation.Task;
import com.crazy.portal.entity.task.ScheduleJob;
import com.crazy.portal.service.task.ScheduleJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;


@Task(value = "rebate数据同步",scheduleCode = "rebate_sync")
@DisallowConcurrentExecution
@Slf4j
public class RebateSyncJob implements Job{

    @Resource
    private ScheduleJobService scheduleJobService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {

        }catch (Exception e){
            log.error("rebate同步异常", e);
        }
    }
}