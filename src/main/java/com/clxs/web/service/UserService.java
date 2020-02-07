package com.clxs.web.service;


import com.clxs.web.model.User;
import org.springframework.data.domain.Page;


import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

public interface UserService {

    User findById(String id);
    List<User> findAll();
    User save(User user);
    void delete(String id);

    //分页
    Page<User> findAll(Pageable pageable);



    //自定义
    User findByUsername(String username);
    List<User> findByUsernameLike(String username);
    List<User> findByIdIn(Collection<String> ids);


    //异步
   Future<List<User>> asyncFindAll();

   //重试
    User findByUsernameRetry(String username);

}
