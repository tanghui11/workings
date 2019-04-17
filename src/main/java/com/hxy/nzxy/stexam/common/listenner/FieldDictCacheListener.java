package com.hxy.nzxy.stexam.common.listenner;

import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.service.RedisService;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.system.domain.FieldDictDO;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.system.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Configuration
@Order(value = 1)
@Import({FieldDictCacheListener.ThreadConfig.class})

public class FieldDictCacheListener implements CommandLineRunner {
    @Autowired
    private RedisService redisService;
    @Autowired
    private CommonService commonService;
    /**
     * 是否启用缓存
     */
    @Value("${chache.field-dict.enabled}")
    private String enabled;

    @Override
    public void run(String... arg0) throws Exception {
        try {

                Map map =new HashMap<>();
                List<FieldDictDO> list = commonService.listAllFieldDictCache(map);
                int count =list.size()/1000;

                for (int i=0;i<=list.size()/1000;i++){
                    DemoThread thread= new DemoThread();

                    if(count==i){
                        thread.init(list.subList(i*1000,list.size()),i,enabled);
                    }else{
                        thread.init(list.subList(i*1000,(i+1)*1000),i,enabled);
                    }


                    thread.start();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   System.out.println(i+":"+df.format(System.currentTimeMillis()));
                    //  thread.run();
                }

            System.out.println("=================================================");
            System.out.println("==============系统缓存数据字典完成！==============");
            System.out.println("=================================================");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  //ComponentScan注解会扫描com.demo.thead下，也就是多线程类所在的包下的文件
    @Configuration
    @ComponentScan(basePackages = { "com.hxy.nzxy.stexam.system.service.impl"})
    public class ThreadConfig{

    }
}
