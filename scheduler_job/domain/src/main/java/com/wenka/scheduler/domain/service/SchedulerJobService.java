package com.wenka.scheduler.domain.service;

import com.wenka.scheduler.domain.dao.SchedulerJobDao;
import com.wenka.scheduler.domain.model.SchedulerJob;
import com.wenka.scheduler.domain.model.User;
import com.wenka.scheduler.domain.task.LoadJob;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 任务调度服务类
 *
 * @author 文卡<wkwenka@gmail.com>  on 17-3-9.
 */
@Service
public class SchedulerJobService {

    @Autowired
    private SchedulerJobDao schedulerJobDao;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private LoadJob loadJob;

    /**
     * 获取任务集
     *
     * @return
     */
    public List<SchedulerJob> getSchedulerJobList() {

        String hql = "FROM SchedulerJob WHERE state <> -1";
        List<SchedulerJob> schedulerJobs = schedulerJobDao.find(hql);
        return schedulerJobs;
    }

    /**
     * 创建新的定时任务
     *
     * @param schedulerJob
     */
    public void createSchedulerJob(SchedulerJob schedulerJob) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        schedulerJob.setCronExpression(formateDateToCron(schedulerJob.getEndTime()));

        loadJob.runTask(schedulerJob, scheduler);
        schedulerJobDao.save(schedulerJob);
    }

    /**
     * 更新定时任务
     *
     * @param schedulerJob
     * @throws Exception
     */
    public void updateSchedulerJob(SchedulerJob schedulerJob) throws Exception {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        SchedulerJob schedulerJobByUser = getSchedulerJobByUser(schedulerJob.getCreator());
        Date endTime = schedulerJob.getEndTime();

        if (schedulerJobByUser != null) {
            schedulerJobByUser.setEndTime(endTime);
            schedulerJobByUser.setCronExpression(formateDateToCron(endTime));

            loadJob.rescheduleJob(scheduler, schedulerJobByUser);
            schedulerJobDao.saveOrUpdate(schedulerJobByUser);
        } else {
            throw new RuntimeException("数据异常");
        }


    }


    /**
     * 内部方法 格式化时间
     *
     * @param date
     * @return
     */
    private String formateDateToCron(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
        String dateStr = "";
        if (date != null) {
            dateStr = format.format(date);
        }
        return dateStr;
    }

    private SchedulerJob getSchedulerJobByUser(User user) {
        String hql = "FROM ScheduleJob WHERE 1=1";
        if (user != null) {
            String creator_id = user.getId();
            hql += " AND creator.id=?";

            List<SchedulerJob> schedulerJobs = schedulerJobDao.find(hql, creator_id);

            if (schedulerJobs != null && schedulerJobs.size() > 0) {
                return schedulerJobs.get(0);
            }
        }
        return null;
    }
}
