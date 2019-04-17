package com.hxy.nzxy.stexam.oa.service.impl;

import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.domain.UserDO;
import com.hxy.nzxy.stexam.system.service.SessionService;
import com.hxy.nzxy.stexam.oa.dao.NotifyDao;
import com.hxy.nzxy.stexam.oa.dao.NotifyRecordDao;
import com.hxy.nzxy.stexam.oa.domain.NotifyDO;
import com.hxy.nzxy.stexam.oa.service.NotifyService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.hxy.nzxy.stexam.common.utils.DateUtils;
import com.hxy.nzxy.stexam.common.utils.PageUtils;
import com.hxy.nzxy.stexam.oa.dao.NotifyDao;
import com.hxy.nzxy.stexam.oa.dao.NotifyRecordDao;
import com.hxy.nzxy.stexam.oa.domain.NotifyDO;
import com.hxy.nzxy.stexam.oa.domain.NotifyDTO;
import com.hxy.nzxy.stexam.oa.domain.NotifyRecordDO;
import com.hxy.nzxy.stexam.oa.service.NotifyService;
import com.hxy.nzxy.stexam.system.dao.UserDao;

@Service
public class NotifyServiceImpl implements NotifyService {
    @Autowired
    private NotifyDao notifyDao;
    @Autowired
    private NotifyRecordDao recordDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public NotifyDO get(String id) {
        return notifyDao.get(id);
    }

    @Override
    public List<NotifyDO> list(Map<String, Object> map) {
        return notifyDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return notifyDao.count(map);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int save(NotifyDO notify) {

        return notifyDao.save(notify);
    }

    @Override
    public int update(NotifyDO notify) {
        return notifyDao.update(notify);
    }

    @Transactional
    @Override
    public int remove(String id) {
        return notifyDao.remove(id);
    }

    @Transactional
    @Override
    public int batchRemove(String[] ids) {
        return notifyDao.batchRemove(ids);
    }

    @Override
    public List<NotifyDO> listNotifyView(Map<String, Object> map) {
        return notifyDao.listNotifyView(map);
    }

    @Override
    public int notifyViewCount(Map<String, Object> map) {
        return notifyDao.notifyViewCount(map);
    }

}
