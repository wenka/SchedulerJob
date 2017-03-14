package com.wenka.scheduler.domain.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * 任务信息
 *
 * @author 文卡<wkwenka@gmail.com>  on 17-3-9.
 */
@Entity
@Table(name = "scheduler_job")
public class SchedulerJob extends AbstractEntity {

    @Column(name = "job_name")
    private String name; //任务名称

    @Column(name = "job_group")
    private String group; //任务分组

    private Integer state; //状态

    @Column(name = "cron_expresstion", nullable = false)
    private String cronExpression; //时间表达式

    private String remark;//备注

    @Column(name = "create_time", nullable = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime = new Date();

    @Column(name = "end_time", nullable = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @JSONField(serialize = false)
    @ManyToOne
    private Organ organ;

    @JSONField(serialize = false)
    @ManyToOne
    private User creator;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Organ getOrgan() {
        return organ;
    }

    public void setOrgan(Organ organ) {
        this.organ = organ;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
