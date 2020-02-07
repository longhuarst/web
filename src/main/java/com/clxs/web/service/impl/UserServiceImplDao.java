package com.clxs.web.service.impl;

import com.clxs.web.dao.UserDao;
import com.clxs.web.model.User;
import com.clxs.web.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

public class UserServiceImplDao implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User findById(String id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> findByUsernameLike(String username) {
        return null;
    }

    @Override
    public List<User> findByIdIn(Collection<String> ids) {
        return null;
    }

    @Override
    public Future<List<User>> asyncFindAll() {
        return null;
    }

    @Override
    public User findByUsernameRetry(String username) {
        return null;
    }
}
