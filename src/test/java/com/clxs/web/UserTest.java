package com.clxs.web;


import com.clxs.web.model.User;
import com.clxs.web.service.UserService;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

public class UserTest {

    @Resource
    private UserService userService;

    @Test
    public void testRepository(){
        //查询所有数据
        List<User> userList = userService.findAll();
        System.out.println("findAll() :" + userList.size());
    }

}
