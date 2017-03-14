package com.wenka.scheduler.domain.task;

import com.wenka.scheduler.domain.model.SchedulerJob;
import com.wenka.scheduler.domain.service.SchedulerJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.List;

/**
 * @author 文卡<wkwenka@gmail.com>  on 17-3-9.
 */
public class LoadJob {
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private SchedulerJobService schedulerJobService;

    public void initTask() throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        // 可执行的任务列表
        List<SchedulerJob> taskList = schedulerJobService.getSchedulerJobList();
        for (SchedulerJob task : taskList) {
            runTask(task, scheduler);
        }
    }

    /**
     * 开启任务
     *
     * @param task
     * @param scheduler
     * @throws Exception
     */
    public void runTask(SchedulerJob task, Scheduler scheduler) throws Exception {
        // 任务名称和任务组设置规则：
        // 名称：task_1 ..
        // 组 ：group_1 ..
        TriggerKey triggerKey = TriggerKey.triggerKey(
                "task_" + task.getId(), "group_" + task.getId());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        // 不存在，创建一个
        if (null == trigger) {
            JobDetail jobDetail = JobBuilder
                    .newJob(QuartzJobFactory.class)
                    .withIdentity("task_" + task.getId(),
                            "group_" + task.getId()).build();
            jobDetail.getJobDataMap().put("scheduleJob", task);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                    .cronSchedule(task.getCronExpression());
            // 按新的表达式构建一个新的trigger
            trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("task_" + task.getId(),
                            "group_" + task.getId())
                    .withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            // trigger已存在，则更新相应的定时设置
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
                    .cronSchedule(task.getCronExpression());
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }

    /**
     * 更新任务
     *
     * @param scheduler
     * @param schedulerJob
     */
    public void rescheduleJob(Scheduler scheduler, SchedulerJob schedulerJob) throws Exception {

        TriggerKey triggerKey = TriggerKey.triggerKey(schedulerJob.getName(),
                schedulerJob.getGroup());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(schedulerJob
                .getCronExpression());

        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                .withSchedule(scheduleBuilder).build();

        scheduler.rescheduleJob(triggerKey, trigger);
    }

}
