package com.crazy.portal.config.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

@Slf4j
public class MonitorJobListener implements JobListener {

    public static final String LISTENER_NAME = "JobListenerName";

    @Override
    public String getName() {
        return LISTENER_NAME;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        log.info("{} job start execute.", jobExecutionContext.getJobDetail().getKey());
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        long second = jobExecutionContext.getJobRunTime();
        log.info("{} job run time : {}s", jobExecutionContext.getJobDetail().getKey(), second/1000);
    }
}
