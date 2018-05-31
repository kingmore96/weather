package com.ging.weather.controller;

import com.ging.weather.pojo.WeatherResponse;
import com.ging.weather.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;

/**
 * 天气服务controller，负责查询天气数据
 * 面向前端，是一个后端服务，被直接调用
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherDataService weatherDataService;

    @GetMapping("/cityId/{cityId}")
    public WeatherResponse getWeatherByCityId(@PathVariable String cityId){
        return weatherDataService.getDataByCityId(cityId);
    }

    @GetMapping("/cityName/{cityName}")
    public WeatherResponse getWeatherByCityName(@PathVariable String cityName){
        return weatherDataService.getDataByCityName(cityName);
    }

}
