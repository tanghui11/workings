/*
package com.hxy.nzxy.stexam.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.service.RedisService;
import com.hxy.nzxy.stexam.common.utils.SpringContextHolder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
@Configuration
public class MyListener{
    @Autowired
    private CommonService commonService;
    @Autowired
    private RedisService redisService;;
    @Value("${chache.field-dict.enabled}")
    private String enabled;
    private DemoThread thread;
    private ExecutorService taskPool= new ThreadPoolExecutor(
            5, 10, 1000,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10),
            new ThreadPoolExecutor.CallerRunsPolicy());


    @KafkaListener(topics = "xxTopic")
    public void receive(ConsumerRecord<Object, Object> consumerRecord) {
        JSONObject json = JSON.parseObject(consumerRecord.value().toString());
        //通过SpringBootUtils获取线程类的实例
        thread = SpringContextHolder.getBean(DemoThread.class);
        //启动线程
        //new Thread(thread).start() ;
        //向线程对象里传值
        thread.init(1);
        //放入线程池执行
        taskPool.execute(thread);

    }
}*/
