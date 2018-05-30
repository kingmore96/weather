package com.ging.weather.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Object fromJson(String json,Class typeClass){
        try {
            return objectMapper.readValue(json,typeClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
