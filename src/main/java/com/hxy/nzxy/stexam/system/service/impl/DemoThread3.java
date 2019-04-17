
package com.hxy.nzxy.stexam.system.service.impl;

import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.service.RedisService;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.common.utils.SpringContextUtil;
import com.hxy.nzxy.stexam.system.domain.FieldDictDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class DemoThread3 extends Thread
{
    //注意这里，如果你没有实现把多线程类的实例注入到spring容器中，这里你是无法拿到其他自动装配的对象实例的的，这也是我们第一步的意义所在。

    @Autowired
    private CommonService commonService;
    @Autowired
    private RedisService redisService;

    public DemoThread3() {
        //new的时候注入需要的bean
        this.commonService =SpringContextUtil.getBean(CommonService.class);
        this.redisService = SpringContextUtil.getBean(RedisService.class);

    }

    public CommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    public RedisService getRedisService() {
        return redisService;
    }

    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public List<FieldDictDO> getList() {
        return list;
    }

    public void setList(List<FieldDictDO> list) {
        this.list = list;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * 是否启用缓存
     */

    private String enabled;
    private List<FieldDictDO>list=new ArrayList();
    private int value;
    //@PostConstruct
    public void start() {
        super.start();
    }

    public void run() {
        // Ok,在这里你就可以实现线程要实现的功能逻辑了，自然也可以直接使用装配好的sevice对象实例。
        try {
           if (enabled.equals("true")) {
                for (FieldDictDO item : list) {
                    // 如果字典缓存已经存在，清除后再增加。
                    String fieldDictKey = item.getAppid() + "," + item.getTableName() + "," + item.getFieldName() + "," + item.getFieldValue() + ",";
                    Set<String> keys = redisService.getKeys(fieldDictKey);
                    for (String key : keys) {
                        redisService.delete(key);
                    }
                    redisService.set(fieldDictKey + item.getDisplayStatus() + "," + item.getStatus() + "," + item.getSeq(), item.getFieldMean());
                    //将字典添加到数据字典工具类
                    FieldDictUtil.set(item.getAppid(), item.getTableName(), item.getFieldName(), item.getFieldValue(), item.getFieldMean());
                    FieldDictUtil.set(item.getAppid(), item.getTableName(), item.getFieldName(),item.getFieldMean() , item.getFieldValue());
                }
            } else {
                List<FieldDictDO> list = commonService.listAllFieldDictCache(null);
                for (FieldDictDO item : list) {
                    FieldDictUtil.set(item.getAppid(), item.getTableName(), item.getFieldName(), item.getFieldValue(), item.getFieldMean());
                }
            }
            System.out.println("=================================================");
            System.out.println("==============系统缓存数据字典线程"+value+"==============");
            System.out.println("=================================================");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void init(List<FieldDictDO> list,int Value,String enabled) {
        this.value=Value;
        this.list=list;
        this.enabled=enabled;
    }
}
