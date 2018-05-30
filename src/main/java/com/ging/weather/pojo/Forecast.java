package com.ging.weather.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Forecast implements Serializable {
    private String date;
    private String high;
    private String fengli;
    private String low;
    private String fengxiang;
    private String type;
}
