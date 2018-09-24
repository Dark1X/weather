package com.lucifer.demo.Service;

import com.lucifer.demo.pojo.City;

import java.util.List;

/**
 * @author: Lucifer
 * @create: 2018-09-24 18:51
 * @description:
 **/
public interface CityDataService {
    /**
     * 获取City
     * @return
     * @throws Exception
     */
    List<City> listCity() throws Exception;
}
