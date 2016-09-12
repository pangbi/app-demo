package com.pb.job.test.example3;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by zhangqiang on 2016/8/18.
 */
public class TestMyJob {
    public static void main(String[] args){
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            // and start it off
            scheduler.start();
            // define the job and tie it to our MyJob class
            JobDetail job = newJob(MyJob.class)
                    .withIdentity("job1", "group1")
                    .build();

            job.getJobDataMap().put("name","foo");
            job.getJobDataMap().put("age",25);

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(2)
                            .withRepeatCount(10))
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);

            JobDetail job2 = newJob(MyJob.class)
                    .withIdentity("job2", "group1")
                    .build();

            job.getJobDataMap().put("name","bar");
            job.getJobDataMap().put("age",22);

            // Trigger the job to run now, and then repeat every 40 seconds
            Trigger trigger2 = newTrigger()
                    .withIdentity("trigger2", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(2)
                            .withRepeatCount(10))
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job2, trigger2);



            Thread.sleep(100000);
            scheduler.shutdown(true);
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
