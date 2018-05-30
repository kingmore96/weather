package com.ging.weather.config;

import com.ging.weather.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {

    @Bean
    public JobDetail weatherDataSynJobDetail(){
        return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJob").storeDurably().build();
    }

    @Bean
    public Trigger weatherDataSyncTrigger(){
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1800)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(weatherDataSynJobDetail()).withIdentity("weatherDataSyncTrigger").
                withSchedule(simpleScheduleBuilder).build();
    }
}
