package com.clxs.web.mail;


/*
* 发送用户邮件服务
* */


import com.clxs.web.model.User;

import java.util.List;

public interface SendJunkMailService {
    boolean sendJunkEmail(List<User> userList);

}
