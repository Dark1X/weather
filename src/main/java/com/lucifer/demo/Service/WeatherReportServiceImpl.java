package com.lucifer.demo.Service;

import com.lucifer.demo.pojo.Weather;
import com.lucifer.demo.pojo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Lucifer
 * @create: 2018-09-24 20:03
 * @description:
 **/
@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    public Weather getDataByCityId(String cityId) {
        WeatherResponse response = weatherDataService.getDataByCityId(cityId);
        return response.getData();
    }
}
