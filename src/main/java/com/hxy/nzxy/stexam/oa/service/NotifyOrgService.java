package com.hxy.nzxy.stexam.oa.service;

import com.hxy.nzxy.stexam.oa.dao.NotifyOrgDao;
import com.hxy.nzxy.stexam.oa.domain.NotifyOrgDO;
import com.hxy.nzxy.stexam.oa.domain.NotifyOrgDO;

import java.util.List;
import java.util.Map;

public interface NotifyOrgService {
    NotifyOrgDO get(String id);

    List<NotifyOrgDO> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(NotifyOrgDO notifyOrgDO);

    int update(NotifyOrgDO notifyOrgDO);

    int remove (String notifyid);

    int batchRemove(String[] ids);
}
