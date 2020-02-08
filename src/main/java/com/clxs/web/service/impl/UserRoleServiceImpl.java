package com.clxs.web.service.impl;

import com.clxs.web.model.UserRole;
import com.clxs.web.repository.UserRoleRepository;
import com.clxs.web.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {


    @Resource
    private UserRoleRepository userRoleRepository;


    @Override
    public Integer findRoleIdByUserId(Integer userId) {
        List<UserRole> userRoleList = userRoleRepository.findByUserId(userId);
        if (userRoleList != null && userRoleList.size() > 0){
            return userRoleList.get(0).getRoleId();
        }
        return null;
    }
}
