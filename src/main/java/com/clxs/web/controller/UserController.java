package com.clxs.web.controller;


import com.clxs.web.error.BusinessException;
import com.clxs.web.model.User;
import com.clxs.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/user/")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("center")
    String userCenter(){


        return "UserCenter";
    }


    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);
        throw new BusinessException("业务异常");
    }

    @RequestMapping("/findByUsernameRetry")
    public String findByUsernameRetry(Model model){
        User user = userService.findByUsernameRetry("测试");
        model.addAttribute("users",user);
        return "success";
    }
}
