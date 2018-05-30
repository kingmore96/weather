package com.ging.weather.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Weather implements Serializable{
    private Yesterday yesterday;
    private String city;
    private String aqi;
    private List<Forecast> forecast;
    private String ganmao;
    private String wendu;
}
