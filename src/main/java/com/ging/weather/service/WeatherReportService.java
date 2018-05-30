package com.ging.weather.service;

import com.ging.weather.pojo.Weather;

public interface WeatherReportService {

    /**
     * 通过城市id得到该城市的天气信息
     *
     * @param cityId
     * @return
     */
    Weather getReportByCityId(String cityId);

    /**
     * 通过cityName获取城市天气信息
     * @param cityName
     * @return
     */

    Weather getReprtByCityName(String cityName);
}
