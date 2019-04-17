package com.hxy.nzxy.stexam.oa.dao;

import com.hxy.nzxy.stexam.oa.domain.NotifyOrgDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NotifyOrgDao {

    NotifyOrgDO get(String id);

    List<NotifyOrgDO> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(NotifyOrgDO notifyOrgDO);

    int update(NotifyOrgDO notifyOrgDO);

    int remove (String notifyid);

    int batchRemove(String[] ids);
}
