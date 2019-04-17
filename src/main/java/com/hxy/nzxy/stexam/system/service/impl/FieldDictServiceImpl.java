package com.hxy.nzxy.stexam.system.service.impl;

import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.domain.CommonDO;
import com.hxy.nzxy.stexam.common.service.RedisService;
import com.hxy.nzxy.stexam.common.utils.FieldDictUtil;
import com.hxy.nzxy.stexam.system.dao.FieldDictDao;
import com.hxy.nzxy.stexam.system.domain.FieldDictDO;
import com.hxy.nzxy.stexam.system.service.FieldDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Service
public class FieldDictServiceImpl implements FieldDictService {
    @Autowired
    private FieldDictDao fieldDictDao;
    @Autowired
    private RedisService redisService;
    /**
     * 是否启用缓存
     */
    @Value("${chache.field-dict.enabled}")
    private String enabled;

    @Override
    public FieldDictDO get(Map<String, Object> map) {
        return fieldDictDao.get(map);
    }

    @Override
    public List<FieldDictDO> list(Map<String, Object> map) {
        return fieldDictDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return fieldDictDao.count(map);
    }

    @Override
    public int save(FieldDictDO fieldDict) {
        return fieldDictDao.save(fieldDict);
    }

    @Override
    public int update(FieldDictDO fieldDict) {
        return fieldDictDao.update(fieldDict);
    }

    @Override
    public int remove(Map<String, Object> map) {
        return fieldDictDao.remove(map);
    }

    @Override
    public List<CommonDO> listAllTables(Map<String, Object> map) {
        return fieldDictDao.listAllTables(map);
    }

    @Override
    public List<CommonDO> listAllFields(Map<String, Object> map) {
        return fieldDictDao.listAllFields(map);
    }

    @Override
    public int saveCache(FieldDictDO fieldDict) {
        try {
            //将字典添加到数据字典工具类
            FieldDictUtil.set(fieldDict.getAppid(), fieldDict.getTableName(), fieldDict.getFieldName(), fieldDict.getFieldValue(), fieldDict.getFieldMean());
            FieldDictUtil.set(fieldDict.getAppid(), fieldDict.getTableName(), fieldDict.getFieldName(),  fieldDict.getFieldMean(),fieldDict.getFieldValue());
            if (enabled.equals("true")) {
                // 添加缓存redis缓存
                String fieldDictKey = fieldDict.getAppid() + "," + fieldDict.getTableName() + "," + fieldDict.getFieldName() + "," + fieldDict.getFieldValue() + ",";
                Set<String> keys = redisService.getKeys(fieldDictKey);
                for (String key : keys) {
                    redisService.delete(key);
                }
                redisService.set(fieldDictKey + fieldDict.getDisplayStatus() + "," + fieldDict.getStatus() + "," + fieldDict.getSeq(), fieldDict.getFieldMean());
            }
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public int updateCache(FieldDictDO fieldDict) {
        try {
            //将字典添加到数据字典工具类
            FieldDictUtil.set(fieldDict.getAppid(), fieldDict.getTableName(), fieldDict.getFieldName(), fieldDict.getFieldValue(), fieldDict.getFieldMean());
            FieldDictUtil.set(fieldDict.getAppid(), fieldDict.getTableName(), fieldDict.getFieldName(),  fieldDict.getFieldMean(),fieldDict.getFieldValue());
            if (enabled.equals("true")) {
                // 修改缓存redis缓存
                String fieldDictKey = fieldDict.getAppid() + "," + fieldDict.getTableName() + "," + fieldDict.getFieldName() + "," + fieldDict.getFieldValue() + ",";
                Set<String> keys = redisService.getKeys(fieldDictKey);
                for (String key : keys) {
                    redisService.delete(key);
                    redisService.set(fieldDictKey + fieldDict.getDisplayStatus() + "," + fieldDict.getStatus() + "," + fieldDict.getSeq(), fieldDict.getFieldMean());
                }
            }
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public int removeCache(FieldDictDO fieldDict) {
        try {
            //将字典添加到数据字典工具类
            FieldDictUtil.remove(fieldDict.getAppid(), fieldDict.getTableName(), fieldDict.getFieldName(), fieldDict.getFieldValue());
            FieldDictUtil.remove(fieldDict.getAppid(), fieldDict.getTableName(), fieldDict.getFieldName(),  fieldDict.getFieldMean());
            if (enabled.equals("true")) {
                // 删除缓存redis缓存
                String fieldDictKey = fieldDict.getAppid() + "," + fieldDict.getTableName() + "," + fieldDict.getFieldName() + "," + fieldDict.getFieldValue() + ",";
                Set<String> keys = redisService.getKeys(fieldDictKey);
                for (String key : keys) {
                    redisService.delete(key);
                }
            }
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public int batchremoveCache(FieldDictDO fieldDict, String[] ids) {
        try {

            if (enabled.equals("true")) {

                for (String id : ids) {
                    //将字典从数据字典工具类移除
                    FieldDictUtil.remove(Constant.APPID , fieldDict.getTableName(), fieldDict.getFieldName(), id);
                    String fieldDictKey =  Constant.APPID + ","+fieldDict.getTableName() + "," + fieldDict.getFieldName() + "," + id + ",";
                    Set<String> keys = redisService.getKeys(fieldDictKey);
                    for (String key : keys) {
                        // 删除缓存redis缓存
                        redisService.delete(key);
                    }
                }



            }
        } catch (Exception e) {
            return 0;
        }

        return 1;
    }

}
