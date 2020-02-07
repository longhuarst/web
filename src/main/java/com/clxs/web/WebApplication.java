package com.clxs.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@ServletComponentScan   //使用该注解后，Servlet、Filter、Listener可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册，无需其他代码。
@EnableAsync            //开启异步调用
@EnableRetry            //开启Retry重试
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);







    }

}
