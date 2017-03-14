package com.wenka.scheduler.domain.dao;

import com.wenka.scheduler.domain.model.SchedulerJob;
import org.springframework.stereotype.Repository;

/**
 * 任务调度
 *
 * @author 文卡<wkwenka@gmail.com>  on 17-3-9.
 */
@Repository
public class SchedulerJobDao extends BaseDao<SchedulerJob, String> {
}
