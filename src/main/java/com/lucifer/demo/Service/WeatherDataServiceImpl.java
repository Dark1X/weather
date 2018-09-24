package com.lucifer.demo.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucifer.demo.pojo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author: Lucifer
 * @create: 2018-09-23 23:24
 * @description: 实现类
 **/

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    protected static final Logger logger = LoggerFactory.getLogger(WeatherDataService.class);

    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * StringRedisTemplate 实际是对redis进行了封装
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final long TIME_OUT = 10L;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        return this.getWeatherResponse(uri);
    }


    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        return this.getWeatherResponse(uri);
    }

    private WeatherResponse getWeatherResponse(String uri) {
        String key = uri;
        String strBody = null;
        WeatherResponse resp = null;
        ObjectMapper objectMapper = new ObjectMapper();
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        //先查缓存，如果有缓存有的，取缓存数据
        if (stringRedisTemplate.hasKey(key)) {
            logger.info("Redis has data");
            strBody = ops.get(key);
        } else {
            logger.info("Redis don't has data");
            //缓存没有，再调服务接口获取
            ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
            if (respString.getStatusCodeValue() == 200) {
                strBody = respString.getBody();
            }
            //数据写入缓存
            ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);

        }
        try {
            resp = objectMapper.readValue(strBody, WeatherResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    public void syncDataByCityId(String cityId) {
        String uri=WEATHER_URI + "citykey=" + cityId;;
        this.saveWeatherData(uri);
    }

    /**
     * 把天气数据放在缓存当中
     *
     * @param uri
     */
    private void saveWeatherData(String uri) {
        String key = uri;
        String strBody = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
        if (respString.getStatusCodeValue() == 200) {
            strBody = respString.getBody();
        }
        ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
    }
}