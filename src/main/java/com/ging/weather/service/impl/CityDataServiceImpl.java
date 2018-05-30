package com.ging.weather.service.impl;

import com.ging.weather.pojo.City;
import com.ging.weather.pojo.CityList;
import com.ging.weather.service.CityDataService;
import com.ging.weather.util.XmlUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class CityDataServiceImpl implements CityDataService {

    @Override
    public List<City> getCityList() throws Exception {
        Resource resource = new ClassPathResource("cityList.xml");
        File file = resource.getFile();
        String xmlStr = FileUtils.readFileToString(file,"utf-8");
        CityList cityList = (CityList) XmlUtil.xmlStrToObject(xmlStr,CityList.class);
        return cityList.getCityList();
    }
}
