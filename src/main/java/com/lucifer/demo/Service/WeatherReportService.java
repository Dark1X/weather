package com.lucifer.demo.Service;

import com.lucifer.demo.pojo.Weather;

public interface WeatherReportService {
    Weather getDataByCityId(String cityId);

}
