package com.clxs.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;



@SpringBootTest
public class RedisTest {


    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testResdis(){

        //增key： name，value：ay
        redisTemplate.opsForValue().set("name","ay");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);


        //删除
        redisTemplate.delete("name");

        //更新
        redisTemplate.opsForValue().set("name","al");

        //查询
        name = (String)redisTemplate.opsForValue().get("name");

        System.out.println(name);
    }




}
