package com.ging.weather.config;

import com.ging.weather.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 定时任务配置
 */
@Configuration
public class QuartzConfiguration {

    /**
     * 每隔半小时=1800秒拉取一次天气数据
     */
    private final int INTERVAL_SECONDS = 1800;

    @Bean
    public JobDetail weatherDataSynJobDetail(){
        return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJob").storeDurably().build();
    }

    @Bean
    public Trigger weatherDataSyncTrigger(){
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(INTERVAL_SECONDS)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(weatherDataSynJobDetail()).withIdentity("weatherDataSyncTrigger").
                withSchedule(simpleScheduleBuilder).build();
    }
}
