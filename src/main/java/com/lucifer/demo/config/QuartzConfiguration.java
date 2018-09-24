package com.lucifer.demo.config;

import com.lucifer.demo.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Lucifer
 * @create: 2018-09-24 18:11
 * @description:
 **/
@Configuration
public class QuartzConfiguration {

    private static final int TIME =1800 ;

    @Bean
    public JobDetail weathDataSyncJobJobDetail(){
        return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJob").storeDurably().build();
    }

    @Bean
    public Trigger weathDataSyncTrigger(){
        SimpleScheduleBuilder scheduleBuilder=SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(TIME).repeatForever();
        return TriggerBuilder.newTrigger().forJob(weathDataSyncJobJobDetail()).withIdentity("weathDataSyncTrigger").withSchedule(scheduleBuilder).build();
    }

}
