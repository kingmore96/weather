package com.ging.weather.service.impl;

import com.ging.weather.enums.ResultEnum;
import com.ging.weather.exception.WeatherException;
import com.ging.weather.pojo.WeatherResponse;
import com.ging.weather.service.WeatherDataService;
import com.ging.weather.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class WeatherDataServiceImpl implements WeatherDataService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final Long TIME_OUT = 1800L;

    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI  +  "citykey=" + cityId;
        return doGetWeather(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI  + "city=" + cityName;
        return doGetWeather(uri);
    }

    @Override
    public void syncWeatherDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        syncWeatherData(uri);
    }

    private void syncWeatherData(String uri) {
        String key = uri;
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
        String result = restTemplate.getForObject(uri,String.class);
        ops.set(key,result,TIME_OUT,TimeUnit.SECONDS);
    }

    private WeatherResponse doGetWeather(String uri) {
        String key = uri;
        String result = null;
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();

        //查询缓存，查到了，从缓存中取出来
        if(stringRedisTemplate.hasKey(key)){
            log.info("redis has it!" );
            result = ops.get(key);
        }
        else{
            //没查到，再从第三方获取，获取之后存入redis
            log.info("redis don't has it!" );
            result = restTemplate.getForObject(uri, String.class);

            if (result == null || result.length() == 0)
                throw new WeatherException(ResultEnum.DATA_ERROR);

            ops.set(key,result,TIME_OUT,TimeUnit.SECONDS);

        }

        WeatherResponse response = (WeatherResponse) JsonUtil.fromJson(result, WeatherResponse.class);

        if (response == null || !response.getStatus().equals("1000") || response.getData() == null) {
            throw new WeatherException(ResultEnum.DATA_ERROR);
        }
        return response;
    }
}
