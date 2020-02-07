package com.clxs.web;


import com.clxs.web.model.User;

import com.clxs.web.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;



@SpringBootTest
public class UserTest {


    @Resource
    private UserService userService;

    @Test
    public void testRepository(){
        //查询所有数据
        User user = userService.findByUsername("admin");
        System.out.println("findByUsername() :" + user.toString());
    }

}
