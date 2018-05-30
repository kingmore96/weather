package com.ging.weather.service;

import com.ging.weather.pojo.WeatherResponse;

public interface WeatherDataService {

    /**
     * 根据城市id获取天气数据
     * @param cityId
     * @return
     */
    WeatherResponse getDataByCityId(String cityId);

    /**
     * 根据城市名称获取天气数据
     * @param cityName
     * @return
     */
    WeatherResponse getDataByCityName(String cityName);

    /**
     * 根据城市id查询天气信息，同步到redis
     * @param cityId
     */
    void syncWeatherDataByCityId(String cityId);
}
