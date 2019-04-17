package com.hxy.nzxy.stexam.oa.service.impl;

import com.hxy.nzxy.stexam.oa.dao.NotifyOrgDao;
import com.hxy.nzxy.stexam.oa.domain.NotifyOrgDO;
import com.hxy.nzxy.stexam.oa.service.NotifyOrgService;
import com.hxy.nzxy.stexam.oa.dao.NotifyOrgDao;
import com.hxy.nzxy.stexam.oa.domain.NotifyOrgDO;
import com.hxy.nzxy.stexam.oa.service.NotifyOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NotifyOrgServiceImpl implements NotifyOrgService {
    @Autowired
    private NotifyOrgDao notifyOrgDao;

    @Override
    public NotifyOrgDO get(String id) {
        return notifyOrgDao.get(id);
    }

    @Override
    public List<NotifyOrgDO> list(Map<String, Object> map) {
        return notifyOrgDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return notifyOrgDao.count(map);
    }

    @Override
    public int save(NotifyOrgDO notifyOrgDO) {
        return notifyOrgDao.save(notifyOrgDO);
    }

    @Override
    public int update(NotifyOrgDO notifyOrgDO) {
        return notifyOrgDao.update(notifyOrgDO);
    }

    @Override
    public int remove(String notifyid) {
        return notifyOrgDao.remove(notifyid);
    }

    @Override
    public int batchRemove(String[] ids) {
        return notifyOrgDao.batchRemove(ids);
    }
}
