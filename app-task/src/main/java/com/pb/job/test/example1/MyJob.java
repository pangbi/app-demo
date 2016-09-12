package com.pb.job.test.example1;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by zhangqiang on 2016/8/18.
 */
public class MyJob implements org.quartz.Job{

    public MyJob(){}
    Logger logger = LoggerFactory.getLogger(MyJob.class);
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        logger.info("SimpleJob says: " + jobKey + " executing at " + new Date());
        logger.info("Hello World!  MyJob is executing.");
    }
}
