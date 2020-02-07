package com.clxs.web.mail.impl;

import com.clxs.web.mail.SendJunkMailService;
import com.clxs.web.model.User;
import com.clxs.web.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class SendJunkEmailServiceImpl implements SendJunkMailService {

    @Autowired
    JavaMailSender mailSender;

    @Resource
    private UserService userService;

    @Value("${spring.mail.username}")  //@Value 可以将application.properties配置文件中的设置到属性中
    private String from;

    public static final Logger logger = LogManager.getLogger(SendJunkMailService.class);




    @Override
    public boolean sendJunkEmail(List<User> userList) {
        try{
            if (userList == null || userList.size() <= 0){
                return false;
            }

            for(User user : userList){
                MimeMessage mimeMessage = this.mailSender.createMimeMessage();
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

                //邮件发送方
                message.setFrom(from);
                //邮件主题
                message.setSubject("springboot-测试邮件");
                //邮件接收方
                message.setTo("738423172@qq.com");
                //邮件内容
                message.setText(user.getUsername() + " 测试 Test");
                //发送邮件
                this.mailSender.send(mimeMessage);

            }
        }catch (Exception e){
            logger.error("sendJunkEmail error and user=%s", userList, e);
            return false;
        }finally {

        }

        return true;
    }
}
