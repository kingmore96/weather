package com.ging.weather.handle;

import com.ging.weather.enums.ResultEnum;
import com.ging.weather.exception.WeatherException;
import com.ging.weather.pojo.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerAOP {

    @ExceptionHandler(WeatherException.class)
    public WeatherResponse handlerWeatherException(WeatherException e){
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setStatus(e.getCode()+"");
        weatherResponse.setDesc(e.getMessage());
        return weatherResponse;
    }

    @ExceptionHandler(Exception.class)
    public WeatherResponse handlerException(Exception e){
        log.error("未知异常：{}",e);
        //TODO 邮件通知
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setStatus(ResultEnum.UNKNOWN_ERROR.getCode()+"");
        weatherResponse.setDesc(ResultEnum.UNKNOWN_ERROR.getMessage());
        return weatherResponse;
    }

}
