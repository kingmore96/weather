package com.ging.weather.service.impl;

import com.ging.weather.pojo.Weather;
import com.ging.weather.service.WeatherDataService;
import com.ging.weather.service.WeatherReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    public Weather getReportByCityId(String cityId) {
        return weatherDataService.getDataByCityId(cityId).getData();
    }

    @Override
    public Weather getReprtByCityName(String cityName) {
        return weatherDataService.getDataByCityName(cityName).getData();
    }
}
