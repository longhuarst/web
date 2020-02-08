package com.clxs.web.configure;

import com.clxs.web.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


//自定义的WebSecurity 配置


@Configuration
@EnableWebSecurity  //
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        System.out.println("configure .......");

        http.authorizeRequests().antMatchers("/").permitAll();
//                .antMatchers("/user/**").hasAnyRole("USER");


        http
                .formLogin()//开启登陆页面
                .failureForwardUrl("/login?error")//错误页面
                .defaultSuccessUrl("/user/dashboard")//登陆成功跳转到用户控制台
                .permitAll();//登陆页面全部授权访问


        http.csrf().ignoringAntMatchers("/druid/*");//忽略druid的请求

        http.logout().logoutSuccessUrl("/");//开启logout方法

        super.configure(http);
    }


    //配置内存用户
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("test").password("test").roles("ADMIN");
    }




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);

        System.out.println("configure(AuthenticationManagerBuilder auth)");
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("admin").password("admin").roles("USER");
    }




    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new CustomUserService();
    }


}
