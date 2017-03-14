package com.wenka.scheduler.domain.task;


import com.wenka.scheduler.domain.model.SchedulerJob;

/**
 * @author 文卡<wkwenka@gmail.com>  on 17-3-13.
 */
public interface IDoSchedulerJob {

    void run(SchedulerJob schedulerJob);
}
