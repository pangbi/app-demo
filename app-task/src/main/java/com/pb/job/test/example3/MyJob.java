package com.pb.job.test.example3;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhangqiang on 2016/8/18.
 */
public class MyJob implements org.quartz.Job{

    public MyJob(){}
    Logger logger = LoggerFactory.getLogger(MyJob.class);
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        JobDataMap data = context.getJobDetail().getJobDataMap();
        String name = data.getString("name");
        int age = data.getInt("age");
        logger.info("JobDataMap:name="+name+",age="+age);
        age++;
        //data.put("age", age);
        //data.put("name", "sb");
        logger.info("SimpleJob says: " + jobKey + " executing at " + new Date());
        logger.info("Hello World!  MyJob is executing.");
    }
}
