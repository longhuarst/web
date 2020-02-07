package com.clxs.web.quartz;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
@Configurable
@EnableScheduling
public class SendEmailQuartz {


    //日志对象
    private static final Logger logger = LogManager.getLogger(SendEmailQuartz.class);

    //每5秒发送一次
    @Scheduled(cron = "*/5 * *  * * * ")
    public void reportCurrentByCorn(){
        logger.info("定时器运行了！！！");
    }

}
