package com.clxs.web.service.impl;

import com.clxs.web.model.User;
import com.clxs.web.repository.UserRepository;
import com.clxs.web.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Resource
    private RedisTemplate redisTemplate;

    private static final String ALL_USER = "ALL_USER_LIST";


    @Override
    public User findById(String id) {
        //step.1 查询redis缓存中的所有数据
        List<User> userList = redisTemplate.opsForList().range(ALL_USER,0,-1);

        if (userList != null && userList.size() > 0){
            for (User user : userList){
                if (user.getId().equals(id)){
                    return user;
                }
            }
        }

        //step.2 查询数据库中的数据
        User user = userRepository.findById(id).get();

        if (user != null) {
            //step.3 将数据放入redis缓存中
            redisTemplate.delete(ALL_USER);//先清除已有的数据
            redisTemplate.opsForList().leftPushAll(ALL_USER, userList);
        }

        return user;
    }

    @Override
    public List<User> findAll() {

//        List<User> userList = redisTemplate.opsForList().range(ALL_USER, 0, -1);
//
//        if (userList != null && userList.size() > 0){
//            return userList;
//        }
//
//        userList = userRepository.findAll();
//
//        if (userList != null && userList.size() > 0){
//            redisTemplate.delete(ALL_USER);
//            redisTemplate.opsForList().leftPushAll(ALL_USER, userList);
//        }


        return userRepository.findAll();
    }

    @Override
    public User save(User user) {

        User userRes = userRepository.save(user);

        if (userRes != null){
            redisTemplate.opsForList().leftPush(ALL_USER,userRes);
        }

        return userRes;

    }

    @Override
    public void delete(String id) {

        userRepository.deleteById(id);



    }


    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }








    @Override
    public List<User> findByUsername(String username) {

        List<User> userList = redisTemplate.opsForList().range(ALL_USER, 0, -1);

        List<User> targetList = new ArrayList<>();

        if(userList != null && userList.size() > 0){
            //这里拿到的是全体成员

            for (User user : userList){
                if (user.getUsername().equals(username)){
                    targetList.add(user);
                }
            }
        }

        if(targetList.size() > 0){
            return targetList;
        }

        //从数据库查询
        targetList = userRepository.findByUsername(username);

        if (userList != null && userList.size() > 0){
            //这里没有更新到redis
        }

        return targetList;//userRepository.findByUsername(username);
    }

    @Override
    public List<User> findByUsernameLike(String username) {
        return userRepository.findByUsernameLike(username);
    }

    @Override
    public List<User> findByIdIn(Collection<String> ids) {
        return findByIdIn(ids);
    }
}
