package com.crazy.portal.task.quartz;


import com.crazy.portal.annotation.Task;
import com.crazy.portal.entity.task.ScheduleJob;
import com.crazy.portal.service.business.RebateService;
import com.crazy.portal.service.task.ScheduleJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;

/**
 * Rebate数据同步
 * BI => portal
 * 频率：每天0点执行一次
 */
@Task(value = "Rebate数据同步",scheduleCode = "rebate_sync")
@Slf4j
@DisallowConcurrentExecution
public class RebateSyncJob implements Job{

    @Resource
    private ScheduleJobService scheduleJobService;
    @Resource
    private RebateService rebateService;

    @Override
    public void execute(JobExecutionContext context) {
        try {
            StopWatch clock = new StopWatch();
            clock.start("Rebate数据同步");
            log.info("rebate数据同步Begin");
            ScheduleJob scheduleJob = scheduleJobService.selectByJobCode("rebate_sync");
            rebateService.rebateDataSync(scheduleJob.getJobConfigParams());
            log.info("rebate数据同步End");
            clock.stop();
            log.info(clock.prettyPrint());
        }catch (Exception e){
            log.error("Rebate数据同步异常", e);
        }
    }
}
