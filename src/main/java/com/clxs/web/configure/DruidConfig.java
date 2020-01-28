package com.clxs.web.configure;


import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfig {


    @Bean
    public ServletRegistrationBean druidStatViewServle(){
        //ServletRegistrationBean 提供类的进行注册
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        //添加初始化参数：initParams
        //白名单：
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");

        //IP 黑名单 （存在共同时，deny 优先于 allow）
        //如果满足 deny ， 就提示：Sorry, you are not permitted to view
    }
}
