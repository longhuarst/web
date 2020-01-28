package com.clxs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class RootController {

    @RequestMapping("/index.html")
    public String root(){
        return "index.html";
    }


}
