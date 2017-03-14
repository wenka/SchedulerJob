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

## 2. 添加/修改任务
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
## 3. 数据库
   建立对应数据库
   
   
## 4.说明

- 此项目为 Quertz与Spring的整合。如果直接下载，若不能运行，则直接将源代码拷贝至你的项目中即可。进而实现必要的接口。对于 *SchedulerJob* 类中的字段，若嫌多余，则可忽略，其对应数据库字段设置为可为空即可。
   
- 此项目文件默认为定时非循环任务。若想使用循环任务，则放弃使用 SchedulerJobService 中的 createSchedulerJob/updateSchedulerJob 方法。直接注入 **LoadJob** ,自行传入cron时间表达式。然后直接使用其中的两个方法：
```
//开启任务
runTask(SchedulerJob task, Scheduler scheduler)
//更新任务
rescheduleJob(Scheduler scheduler, SchedulerJob schedulerJob)
```
