package com.freedom.springbootdevtemplate;

import com.freedom.springbootdevtemplate.common.utils.RedisUtils;
import com.freedom.springbootdevtemplate.common.utils.TimeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@SpringBootTest
class SpringBootDevTemplateApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testTestTemplate() {
        String url = "http://restapi.amap.com/v3/geocode/geo?address=%E5%B9%BF%E4%B8%9C%E7%9C%81%E5%8D%97%E5%B1%B1%E5%8C%BA%E6%80%A1%E5%8C%96%E9%87%91%E8%9E%8D%E7%A7%91%E6%8A%80%E5%A4%A7%E5%8E%A6&output=JSON&key=031a144c48ecc34528550ce2e0c49cf2";
        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
        System.out.println(response);
    }

    @Test
    public void testRedis() {
        String key = "user";
        RedisUtils.setEx(key, "freedom", 360);
        System.out.println(RedisUtils.get(key));
    }

    @Test
    public void testTime() {
        String dateTime = TimeUtils.defaultFormatDateTime(LocalDateTime.now());
        System.out.println(dateTime);
        System.out.println(TimeUtils.convertTimeToLong(dateTime, TimeUtils.DEFAULT_PATTERN));
    }

}
