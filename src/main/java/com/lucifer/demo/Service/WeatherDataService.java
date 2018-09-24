package com.lucifer.demo.Service;

import com.lucifer.demo.pojo.WeatherResponse;

/**
 * 天气数据接口
 */
public interface WeatherDataService {

    /**
     * 根据城市ID查询天气数据
     * @param CityId
     * @return
     */
    WeatherResponse getDataByCityId(String CityId);

    /**
     * 根据城市名称查询天气数据
     * @param cityName
     * @return
     */
    WeatherResponse getDataByCityName(String cityName);


    /**
     * 根据城市id同步数据
     */
    void syncDataByCityId(String cityId);


}



