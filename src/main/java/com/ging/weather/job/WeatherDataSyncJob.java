package com.ging.weather.job;

import com.ging.weather.pojo.City;
import com.ging.weather.service.CityDataService;
import com.ging.weather.service.WeatherDataService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

@Slf4j
public class WeatherDataSyncJob extends QuartzJobBean {

    @Autowired
    private CityDataService cityDataService;

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Weather Data sync start");
        List<City> cityList = null;
        try {
            cityList = cityDataService.getCityList();
        } catch (Exception e) {
            log.info("exception{}",e);
        }

        for (City city : cityList ) {
            weatherDataService.syncWeatherDataByCityId(city.getCityId());
            log.info("Weather sync city name:" + city.getCityName());
        }
        log.info("Weather Data sync end");
    }
}
