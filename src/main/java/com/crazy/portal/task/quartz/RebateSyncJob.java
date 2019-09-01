package com.crazy.portal.task.quartz;


import com.crazy.portal.config.quartz.annotation.Task;
import com.crazy.portal.entity.task.ScheduleJob;
import com.crazy.portal.service.business.RebateService;
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
    @Resource
    private RebateService rebateService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            log.info("rebate数据同步Begin");
            ScheduleJob scheduleJob = scheduleJobService.selectByJobCode("rebate_sync");
            rebateService.rebateDataSync(scheduleJob.getJobConfigParams());
            log.info("rebate数据同步End");
        }catch (Exception e){
            log.error("rebate同步异常", e);
        }
    }
}
