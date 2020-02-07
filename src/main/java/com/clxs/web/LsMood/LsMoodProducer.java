package com.clxs.web.LsMood;

/*
* 生产者
* */

import com.clxs.web.Bean.LsMood;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;


@Service
public class LsMoodProducer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination, final String message){
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    public void sendMessage(Destination destination, LsMood lsMood){
        jmsMessagingTemplate.convertAndSend(destination, lsMood);
    }


}
