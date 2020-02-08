package com.clxs.web.repository;

import com.clxs.web.model.UserAttachmentRel;
import org.springframework.data.mongodb.repository.MongoRepository;

/*
* 用户附件 Repository
* */
public interface UserAttachmentRelRepository extends MongoRepository<UserAttachmentRel,String> {

}
