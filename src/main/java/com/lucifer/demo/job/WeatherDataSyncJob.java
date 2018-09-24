package com.lucifer.demo.job;

import com.lucifer.demo.Service.CityDataService;
import com.lucifer.demo.Service.WeatherDataService;
import com.lucifer.demo.pojo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * @author: Lucifer
 * @create: 2018-09-24 18:08
 * @description:
 **/
public class WeatherDataSyncJob extends QuartzJobBean {

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    private CityDataService cityDataService;

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("Weather Data Sync Job,Start!");
        List<City> cityList = null;
        try {
            cityList = cityDataService.listCity();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (City city : cityList) {
            String cityId=city.getCityId();
            logger.info("weather Data Sync Job,cityId:"+cityId);
            weatherDataService.syncDataByCityId(cityId);
        }
        logger.info("Weather Data Sync Job,End!");

    }
}
