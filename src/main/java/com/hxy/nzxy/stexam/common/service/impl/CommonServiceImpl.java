package com.hxy.nzxy.stexam.common.service.impl;

import java.util.*;

import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.dao.CommonDao;
import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.domain.SchoolDO;
import com.hxy.nzxy.stexam.domain.ValueDo;
import com.hxy.nzxy.stexam.system.domain.*;
import com.hxy.nzxy.stexam.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.hxy.nzxy.stexam.common.service.RedisService;

/**
 * @ClassName: CommonService
 * @Description:通用方法类
 * @author: dragon
 * @date: 2017-10-23 13:39:47
 */
@Configuration
@Service("commonService")
public class CommonServiceImpl implements CommonService {
    @Autowired
    private CommonDao commonDao;
    @Autowired
    private RedisService redisService;
    /**
     * 是否启用缓存
     */
    @Value("${chache.field-dict.enabled}")
    private String enabled;

    /**
     * 查询数据字典
     * @param appid
     * @param tableName
     * @param fieldName
     * @return
     */
    @Override
    public List<FieldDictDO> listFieldDict(String appid,String tableName,String fieldName) {
        Map map = new HashMap();
        map.put("appid", appid);
        map.put("tableName", tableName);
        map.put("fieldName", fieldName);
        return listFieldDict(map);
    }

    /**
     * 查询数据字典
     * @param map
     * @return
     */
    @Override
    public List<FieldDictDO> listFieldDict(Map<String, Object> map) {
        if (enabled.equals("true")) {
            List<FieldDictDO> list = new ArrayList<FieldDictDO>();
            String fieldDictKey = map.get("appid").toString() + "," + map.get("tableName").toString() + "," + map.get("fieldName").toString() + ",";
            Set<String> keys = redisService.getKeys(fieldDictKey);
            for (String key : keys) {
                // 表名,字段名,字段值,显示状态,状态=>字段含义
                // 判断该字典是否为显示状态并且为启用状态
                String[] fieldValues = key.replaceAll(fieldDictKey, "").split(",");
                if (fieldValues.length == 4 && fieldValues[1].equals("a") && fieldValues[2].equals("a")) {
                    FieldDictDO fdDO = new FieldDictDO();
                    fdDO.setId(fieldValues[0]);
                    fdDO.setName(redisService.get(key));
                    fdDO.setSeq(Integer.parseInt(fieldValues[3]));
                    list.add(fdDO);
                }
            }
            Collections.sort(list);
            return list;
        } else {
            Map fdDO = new HashMap();
            fdDO.put("appid", map.get("appid").toString());
            fdDO.put("table_name", map.get("tableName").toString());
            fdDO.put("field_name", map.get("fieldName").toString());
            return (List<FieldDictDO>) commonDao.listFieldDictByTableFieldName(fdDO);
        }
    }

    /**
     * 数据字典列表(缓存使用)
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public List<FieldDictDO> listAllFieldDictCache(Map<String, Object> map) {



        map.put("appid", Constant.APPID);
        return (List<FieldDictDO>) commonDao.listAllFieldDictCache(map);
    }
    @Override
    public List<FieldDictDO> listAllFieldDictCache1(Map<String, Object> map) {



        map.put("appid", Constant.APPID);
        return (List<FieldDictDO>) commonDao.listAllFieldDictCache1(map);
    }
    @Override
    public List<FieldDictDO> listAllFieldDictCache2(Map<String, Object> map) {



        map.put("appid", Constant.APPID);
        return (List<FieldDictDO>) commonDao.listAllFieldDictCache2(map);
    }@Override
    public List<FieldDictDO> listAllFieldDictCache3(Map<String, Object> map) {



        map.put("appid", Constant.APPID);
        return (List<FieldDictDO>) commonDao.listAllFieldDictCache3(map);
    }@Override
    public List<FieldDictDO> listAllFieldDictCache4(Map<String, Object> map) {



        map.put("appid", Constant.APPID);
        return (List<FieldDictDO>) commonDao.listAllFieldDictCache4(map);
    }@Override
    public List<FieldDictDO> listAllFieldDictCache5(Map<String, Object> map) {



        map.put("appid", Constant.APPID);
        return (List<FieldDictDO>) commonDao.listAllFieldDictCache5(map);
    }@Override
    public List<FieldDictDO> listAllFieldDictCache6(Map<String, Object> map) {



        map.put("appid", Constant.APPID);
        return (List<FieldDictDO>) commonDao.listAllFieldDictCache6(map);
    }
    /**
     * 用户列表(缓存使用)
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public List<UserDO> listAllUserCache(Map<String, Object> map) {
        return (List<UserDO>) commonDao.listAllUserCache(map);
    }
    /**
     * 获取所用应用信息
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public List<AppDO> listAllApp(Map<String, Object> map) {
        return (List<AppDO>) commonDao.listAllApp(map);
    }

    /**
     * 获取所有考试科目
     * @return
     * @throws Exception
     */
    @Override
    public List<SubjectDO> listAllSubject(Map<String, Object> map) {
        return (List<SubjectDO>) commonDao.listAllSubject(map);
    }
    /**
     * 获取教学大纲列表
     * @return
     * @throws Exception
     */
    public List<KnowledgeDO> listAllKnowledge(Map<String, Object> map) {
        return (List<KnowledgeDO>) commonDao.listAllKnowledge(map);
    }

    /**
     * 根据关键词查询用户信息
     * @return
     * @throws Exception
     */
    @Override
    public List<UserDO> listUserByKey(Map<String, Object> map) {
        return (List<UserDO>) commonDao.listUserByKey(map);
    }

    /**
     * 获取所有的机构信息
     * @return
     * @throws Exception
     */
    @Override
    public List<OrgDO> listAllOrg(Map<String, Object> map){
        return (List<OrgDO>) commonDao.listAllOrg(map);
    }


    /**
     * 按照名称查询数据导出表定义
     * @return
     * @throws Exception
     */
    @Override
    public ExportTableDO getExportTableByName(String name){
        return commonDao.getExportTableByName(name);
    }

    /**
     * 按照数据导出表编号查询导出字段
     * @return
     * @throws Exception
     */
    @Override
    public List<ExportFieldDO> listExportFieldByExportTableid(String exportTableid){
        return commonDao.listExportFieldByExportTableid(exportTableid);
    }

    @Override
    public ValueDo getValueBykeyTable(Map<String, Object> map) {
        return commonDao.getValueBykeyTable(map);
    }

    @Override
    public int searchIfExist(Map<String, Object> map) {
        return commonDao.searchIfExist(map);
    }

    @Override
    public void batchAuditStatus(String tableName, String filedName, String filedValue, String key, Long[] keyValue) {
        Map map =new HashMap();
        map.put("tableName",tableName);
        map.put("filedName",filedName);
        map.put("filedValue",filedValue);
        map.put("key",key);
        map.put("keyValue",keyValue);
        commonDao.batchAuditStatus(map);
    }

    @Override
    public int selectIDCardStudentid(Map<String, Object> map) {
        return commonDao.selectIDCardStudentid(map);
    }

}
