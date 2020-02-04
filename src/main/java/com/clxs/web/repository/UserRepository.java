package com.clxs.web.repository;

import com.clxs.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {



    //通过名字相等查询，参数为name
    //相当于 select u from user u where u.name = ?1
    List<User> findByUsername(String username);

    //通过名字like查询，参数为username
    //相当于 select u from user u where u.name like ?1
    List<User> findByUsernameLike(String username);

    //通过主键id集合查询，参数为id集合
    //相当于 select u from user u where id in(?,?,?)
    List<User> findByIdIn(Collection<String> ids);















}
