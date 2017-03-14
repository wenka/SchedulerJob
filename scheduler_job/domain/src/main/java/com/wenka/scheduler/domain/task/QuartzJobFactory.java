package com.wenka.scheduler.domain.task;

import com.wenka.scheduler.domain.model.SchedulerJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * 定时任务运行工厂类
 *
 * @author 文卡<wkwenka@gmail.com>  on 17-3-9.
 */
@DisallowConcurrentExecution
@Component
public class QuartzJobFactory implements Job {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IDoSchedulerJob doSchedulerJob;

    public void execute(JobExecutionContext context) throws JobExecutionException {

        // 使得job对象可以通过注解实现依赖注入
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        logger.info("---任务进行---");
        SchedulerJob schedulerJob = (SchedulerJob) context.getMergedJobDataMap().get(
                "scheduleJob");

        //任务主体执行内容
        doSchedulerJob.run(schedulerJob);

        logger.info("---任务结束---");

    }

}
