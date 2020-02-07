package com.clxs.web.service.impl;

import com.clxs.web.Bean.LsMood;
import com.clxs.web.LsMood.LsMoodProducer;
import com.clxs.web.repository.LsMoodRepository;
import com.clxs.web.service.LsMoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;


@Service
public class LsMoodServiceImpl implements LsMoodService {

    @Resource
    private LsMoodRepository lsMoodRepository;


    @Override
    public LsMood save(LsMood lsMood) {
        return lsMoodRepository.save(lsMood);
    }

    //队列
    private static Destination destination = new ActiveMQQueue("ls.queue.asyn.save");

    @Resource
    private LsMoodProducer lsMoodProducer;

    @Override
    public String asynSave(LsMood lsMood) {

        //往队列 ls.queue.asyn.save 推送消息，消息内容为说说实体

        lsMoodProducer.sendMessage(destination, lsMood);

        return "success";
    }
}
