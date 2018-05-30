package com.ging.weather.service.impl;

import com.ging.weather.WeatherApplicationTests;
import com.ging.weather.pojo.City;
import com.ging.weather.service.CityDataService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@SuppressWarnings("ALL")
public class CityDataServiceImplTest extends WeatherApplicationTests {

    @Autowired
    private CityDataService cityDataService;

    @Test
    public void getCityList() throws Exception {
        List<City> cityList = cityDataService.getCityList();
        Assert.assertNotNull(cityList);
    }
}