package com.ging.weather.controller;

import com.ging.weather.pojo.Weather;
import com.ging.weather.service.CityDataService;
import com.ging.weather.service.WeatherReportService;
import com.ging.weather.util.IpAddressUtil;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@RestController
@RequestMapping("/report")
public class WeatherReportController {

    @Autowired
    private CityDataService cityDataService;

    @Autowired
    private WeatherReportService weatherReportService;

    @RequestMapping("/cityId/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable String cityId,Model model) throws Exception {
        model.addAttribute("title","老王的天气预报");
        model.addAttribute("report",weatherReportService.getReportByCityId(cityId));
        return new ModelAndView("weather/report","reportModel",model);
    }

    @GetMapping("/latest")
    public ModelAndView getReportByCityName(Model model, HttpServletRequest request) throws IOException, GeoIp2Exception {
        //通过request得到ip地址
        String ipAddr = IpAddressUtil.getIpAdrress(request);

        //根据ip地址，使用reader读取该项ip对应的城市国家等信息
        Resource resource = new ClassPathResource("GeoLite2-City.mmdb");
        File database = resource.getFile();
        DatabaseReader reader = new DatabaseReader.Builder(database).build();
        InetAddress ipAddress = InetAddress.getByName(ipAddr);
        CityResponse cityResponse = reader.city(ipAddress);

        //得到cityName
        City city = cityResponse.getCity();
        String cityName = city.getNames().get("zh-CN");

        //调用service查询该城市对应天气
        Weather weather = weatherReportService.getReprtByCityName(cityName);
        model.addAttribute("title","老王的天气预报,小任专属");
        model.addAttribute("report",weather);
        model.addAttribute("cityName",cityName);
        return new ModelAndView("weather/report","reportModel",model);
    }

}
