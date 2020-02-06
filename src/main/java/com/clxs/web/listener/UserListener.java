package com.clxs.web.listener;

import com.clxs.web.model.User;
import com.clxs.web.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.security.AllPermission;
import java.util.List;

@WebListener
public class UserListener implements ServletContextListener {


    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserService userService;

    private static final String ALL_USER = "ALL_USER_LIST";


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(getClass().getSimpleName()+" - contextInitialized();");

        List<User> userList = userService.findAll();

        //清除缓存中所有用户数据
        redisTemplate.delete(ALL_USER);

        //将数据放入redis缓存中
        redisTemplate.opsForList().leftPushAll(ALL_USER,userList);

        //在真实项目中需要注释掉，查询所有用户数据
        //0：第一个元素
        //-1：最后一个元素
        List<User> queryUserList = redisTemplate.opsForList().range(ALL_USER,0,-1);
        System.out.println("缓存中目前的用户数有： "+queryUserList.size()+" 人");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        System.out.println(getClass().getSimpleName()+" - contextDestroyed();");

    }
}
