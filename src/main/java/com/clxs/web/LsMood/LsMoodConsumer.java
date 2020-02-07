package com.clxs.web.LsMood;

import com.clxs.web.Bean.LsMood;
import com.clxs.web.service.LsMoodService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LsMoodConsumer {

    @JmsListener(destination = "ls.queue")
    public void receiveQueue(String text){
        System.out.println("用户发表说说【 "+text+" 】");
    }


    @Resource
    private LsMoodService lsMoodService;

    @JmsListener(destination = "ls.queue.asyn.save")
    public void receiveQueue(LsMood lsMood){
        lsMoodService.save(lsMood);
    }
}
