# Scheduler

## 环境：Spring+Quertz+gradle

## 1. 任务执行主体方法

- 实现 IDoSchedulerJob 接口 run(SchedulerJob schedulerJob) 方法。
- 参数：SchedulerJob schedulerJob 任务类
    - name：任务名称
    - group：任务分组
    - state：任务状态 -1：删除 0:未启用  1:启用
    - cronExpression：时间表达式
    - remark：备注
    - createTime：创建时间
    - endTime：截止时间
    - organ：组织
    - creator：用户

## 2. 动态任务 
注入 SchedulerJobService。
- getSchedulerJobList()：获取任务集合

- createSchedulerJob(SchedulerJob schedulerJob)：创建新的任务

    - JSON 类型：
     ```
     {
        "endTime":"time",
        "creator":{
          "id":"用户id"
        },
        "organ":{
          "id":"组织id"
        }
     }
     ```
     
- updateSchedulerJob(SchedulerJob schedulerJob)：更新任务

    - JSON 类型：
    ```
    {
        "endTime":"time",
        "creator":{
          "id":"用户id"
        },
        "organ":{
          "id":"组织id"
        }
     }
    ```
    
    
