package com.crazy.portal.task.quartz;

import com.crazy.portal.config.quartz.annotation.Task;
import com.crazy.portal.entity.task.ScheduleJob;
import com.crazy.portal.service.product.ProductService;
import com.crazy.portal.service.task.ScheduleJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

/**
 * @ClassName: ProductJob
 * @Author: God Man Qiu~
 * @Date: 2019/8/11 21:09
 */
@Task(value = "产品同步",scheduleCode = "productSync")
@DisallowConcurrentExecution
@Slf4j
public class ProductJob implements Job{
    @Resource
    private ScheduleJobService scheduleJobService;
    @Resource
    private ProductService productService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = scheduleJobService.selectByJobCode("productSync");
        try {
            productService.syncProduct();
        }catch (Exception e){
            log.error("产品信息同步异常！",e);
        }
    }
}
