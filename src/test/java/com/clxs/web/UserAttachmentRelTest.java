package com.clxs.web;


import com.clxs.web.model.User;
import com.clxs.web.model.UserAttachmentRel;
import com.clxs.web.service.UserAttachmentRelService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserAttachmentRelTest {


    @Resource
    UserAttachmentRelService userAttachmentRelService;

    @Test
    public void test(){
        UserAttachmentRel userAttachmentRel = new UserAttachmentRel();

        userAttachmentRel.setId("1");
        userAttachmentRel.setUserId("1");
        userAttachmentRel.setFileName("test.txt");

        userAttachmentRelService.save(userAttachmentRel);

        System.out.println("保存成功");
    }



}
