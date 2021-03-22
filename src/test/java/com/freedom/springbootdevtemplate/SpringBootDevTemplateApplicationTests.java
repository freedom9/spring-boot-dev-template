package com.freedom.springbootdevtemplate;

import com.freedom.springbootdevtemplate.common.utils.RedisUtils;
import com.freedom.springbootdevtemplate.common.utils.TimeUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SpringBootDevTemplateApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Test
    @DisplayName("测试Redis连接池")
    public void testRedisConnectionFactory() {
        System.out.println(redisConnectionFactory.getClass());
    }

    @Test
    @DisplayName("测试RestTemplate和组合断言")
    public void testRestTemplate() {
        String url = "http://restapi.amap.com/v3/geocode/geo?address=%E5%B9%BF%E4%B8%9C%E7%9C%81%E5%8D%97%E5%B1%B1%E5%8C%BA%E6%80%A1%E5%8C%96%E9%87%91%E8%9E%8D%E7%A7%91%E6%8A%80%E5%A4%A7%E5%8E%A6&output=JSON&key=031a144c48ecc34528550ce2e0c49cf2";
        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
        JsonObject jsonObject = JsonParser.parseString(response.getBody().toString()).getAsJsonObject();

        assertAll(() -> assertEquals("1", jsonObject.get("status").getAsString()),
                () -> assertEquals("OK", jsonObject.get("info").getAsString()));
    }

    @Test
    @DisplayName("测试RedisUtils工具类")
    public void testRedis() {
        String key = "user";
        String value = "freedom";
        RedisUtils.setEx(key, value, 360);
        assertEquals(value, RedisUtils.get(key));
    }

    @Test
    @DisplayName("测试TimeUtil工具类")
    public void testTime() {
        String dateTime = TimeUtils.defaultFormatDateTime(LocalDateTime.now());
        System.out.println(dateTime);
        System.out.println(TimeUtils.convertTimeToLong(dateTime, TimeUtils.DEFAULT_PATTERN));
    }

}
