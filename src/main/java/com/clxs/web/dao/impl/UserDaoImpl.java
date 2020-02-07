package com.clxs.web.dao.impl;

import com.clxs.web.dao.UserDao;
import com.clxs.web.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDaoImpl implements UserDao {

    @Resource
    private UserDao userDao;

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }


}
