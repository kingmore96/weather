package com.ging.weather.exception;

import com.ging.weather.enums.ResultEnum;

public class WeatherException extends RuntimeException {

    private Integer code;

    public WeatherException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }


}
