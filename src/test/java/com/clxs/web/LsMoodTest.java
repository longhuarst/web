package com.clxs.web;


import com.clxs.web.Bean.LsMood;
import com.clxs.web.LsMood.LsMoodProducer;
import com.clxs.web.service.LsMoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.Date;

@SpringBootTest
public class LsMoodTest {


    @Resource
    private LsMoodService lsMoodService;



    @Test
    public void test(){


        LsMood lsMood = new LsMood();
        lsMood.setId("1");
        lsMood.setUserId("1");
        lsMood.setPraiseNum(0);
        lsMood.setContent("第一条");
        lsMood.setPublishTime(new Date());

        lsMoodService.save(lsMood);



    }


    @Resource
    private LsMoodProducer lsMoodProducer;

    @Test
    public void test2(){
        Destination destination = new ActiveMQQueue("ls.queue");
        lsMoodProducer.sendMessage(destination,"hello, activemq!");
    }





    @Test
    public void test3(){
        LsMood lsMood = new LsMood();
        lsMood.setId("2");
        lsMood.setUserId("2");
        lsMood.setPraiseNum(0);
        lsMood.setContent("第二");
        lsMood.setPublishTime(new Date());
        String msg = lsMoodService.asynSave(lsMood);
        System.out.println("异步发表说说 ： "+msg);
    }

}
