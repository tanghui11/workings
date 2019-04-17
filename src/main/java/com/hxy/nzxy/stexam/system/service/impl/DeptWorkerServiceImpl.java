package com.hxy.nzxy.stexam.system.service.impl;

import com.hxy.nzxy.stexam.system.dao.DeptWorkerDao;
import com.hxy.nzxy.stexam.system.domain.DeptWorkerDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.system.dao.DeptWorkerDao;
import com.hxy.nzxy.stexam.system.domain.DeptWorkerDO;
import com.hxy.nzxy.stexam.system.service.DeptWorkerService;


@Service
public class DeptWorkerServiceImpl implements DeptWorkerService {
    @Autowired
    private DeptWorkerDao deptWorkerDao;

    @Override
    public DeptWorkerDO get(Long id) {
        return deptWorkerDao.get(id);
    }

    @Override
    public List<DeptWorkerDO> list(Map<String, Object> map) {
        return deptWorkerDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return deptWorkerDao.count(map);
    }

    @Override
    public int save(DeptWorkerDO deptWorker) {
        return deptWorkerDao.save(deptWorker);
    }

    @Override
    public int update(DeptWorkerDO deptWorker) {
        return deptWorkerDao.update(deptWorker);
    }

    @Override
    public int remove(Long id) {
        return deptWorkerDao.remove(id);
    }

    @Override
    public int removeByWorkerid(String workerid) {
        return deptWorkerDao.removeByWorkerid(workerid);
    }
}
