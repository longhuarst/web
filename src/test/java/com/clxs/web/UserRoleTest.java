package com.clxs.web;


import com.clxs.web.service.UserRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserRoleTest {


    @Resource
    private UserRoleService userRoleService;


    @Test
    void test(){

        Integer id = userRoleService.findRoleIdByUserId(1);

        System.out.println("user id 1 's user id = "+id);


    }

}
