package com.lucifer.demo.pojo;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author: Lucifer
 * @create: 2018-09-24 18:42
 * @description: 城市集合
 **/
@XmlRootElement(name = "c")
@XmlAccessorType(XmlAccessType.FIELD)
public class CityList {
    @XmlElement(name = "d")
    private List<City> cityList;

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
