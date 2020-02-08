package com.clxs.web.service.impl;

import com.clxs.web.model.UserAttachmentRel;
import com.clxs.web.repository.UserAttachmentRelRepository;
import com.clxs.web.service.UserAttachmentRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserAttachmentRelServiceImpl implements UserAttachmentRelService {

    @Resource
    private UserAttachmentRelRepository userAttachmentRelRepository;


    @Override
    public UserAttachmentRel save(UserAttachmentRel userAttachmentRel) {
        return userAttachmentRelRepository.save(userAttachmentRel);
    }

}
