/*
package com.hxy.nzxy.stexam.system.service.impl;

import com.hxy.nzxy.stexam.common.service.RedisService;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.system.domain.FieldDictDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AsyncTaskService {
    @Autowired
    private RedisService redisService;
    @Async
    public void executeAsyncTask(List<FieldDictDO> list,int i) {
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
        System.out.println("线程" + Thread.currentThread().getName() + " 完成："+i );
    }

}
*/
