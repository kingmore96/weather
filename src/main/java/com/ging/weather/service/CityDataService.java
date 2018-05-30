package com.ging.weather.service;

import com.ging.weather.pojo.City;

import java.util.List;

public interface CityDataService {

    /**
     * 获取城市列表
     * @return
     * @throws Exception
     */

    List<City> getCityList() throws Exception;
}
