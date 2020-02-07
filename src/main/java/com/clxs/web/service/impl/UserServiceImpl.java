package com.clxs.web.service.impl;

import com.clxs.web.error.BusinessException;
import com.clxs.web.mail.SendJunkMailService;
import com.clxs.web.model.User;
import com.clxs.web.repository.UserRepository;
import com.clxs.web.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class UserServiceImpl implements UserService {

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Resource
    private RedisTemplate redisTemplate;

    private static final String ALL_USER = "ALL_USER_LIST";


    public static final Logger logger = LogManager.getLogger(UserServiceImpl.class);


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


        try{
            System.out.println("开始做任务");
            long start = System.currentTimeMillis();
            List<User> userList = userRepository.findAll();
            long end = System.currentTimeMillis();
            System.out.println("完成任务， 耗时：" + (end-start) + " 毫秒");

            return userList;

        }catch (Exception e){
            logger.error("method [findAll] error ", e);
            return Collections.EMPTY_LIST;
        }finally {

        }
//        return Collections.EMPTY_LIST;

//        return userRepository.findAll();
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
    public User findByUsername(String username) {

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
            return targetList.get(0);
        }

        //从数据库查询
        targetList = userRepository.findByUsername(username);

        if (userList != null && userList.size() > 0){
            //这里没有更新到redis
        }

        return targetList.get(0);//userRepository.findByUsername(username);
    }

    @Override
    public List<User> findByUsernameLike(String username) {
        return userRepository.findByUsernameLike(username);
    }

    @Override
    public List<User> findByIdIn(Collection<String> ids) {
        return findByIdIn(ids);
    }

    @Override
    @Async
    public Future<List<User>> asyncFindAll() {
        try{
            System.out.println("开始做任务");
            long start = System.currentTimeMillis();
            List<User> userList = userRepository.findAll();
            long end = System.currentTimeMillis();
            System.out.println("完成任务， 耗时：" + (end-start) + " 毫秒");

            return new AsyncResult<List<User>>(userList);

        }catch (Exception e){
            logger.error("method [findAll] error ", e);
            return new AsyncResult<List<User>>(null);
        }finally {

        }
//        return null;
    }

    // value 属性表示当出现哪些异常的时候触发重试，
    // maxAttempts表示最大重试次数，默认为3
    // delay 表示重试的延迟时间
    // multiplier 表示上一次延时时间是这一次的倍数
    @Override
    @Retryable(value={BusinessException.class}, maxAttempts = 5, backoff = @Backoff(delay = 5000, multiplier = 2))
    public User findByUsernameRetry(String username) {
        System.out.println("[findByUsernameRetry 方法失败重试了]");
        throw new BusinessException();
    }
}
