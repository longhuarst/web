package com.clxs.web.dao;

/*
*   用户dao
* */


import com.clxs.web.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper //重要注解，Mybatis 根据接口定义与Mapper 文件中的SQL语句动态创建接口实现。
public interface UserDao {

    //通过用户名和密码查询用户
    //@Param 注解参数， 在Mapper.xml 配置文件中，可以采用 #{} 的方式对 @Param 注解括号内的参数进行引用
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);


    User findByUsername(@Param("username") String username);

}
