package com.clxs.web.service;

/*
* 自定义用户服务类
* */

import com.clxs.web.error.BusinessException;
import com.clxs.web.model.User;
import com.clxs.web.model.UserAttachmentRel;
import com.clxs.web.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserService implements UserDetailsService {


    public static final int USER = 1;
    public static final int ADMIN = 2;

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUsername(username);

        if (user == null)
            throw new BusinessException("用户不存在");



        //获取用户所关联角色
        Integer role = userRoleService.findRoleIdByUserId(Integer.valueOf(user.getId()));

        List<GrantedAuthority> authorityList = new ArrayList<>();


        if (role != null){
            switch (role){
                case USER :
                    authorityList.add(new SimpleGrantedAuthority("USER"));
                    break;
                case ADMIN:
                    authorityList.add(new SimpleGrantedAuthority("ADMIN"));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + role);
            }
        }



        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorityList);
    }


}







