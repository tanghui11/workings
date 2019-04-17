package com.hxy.nzxy.stexam.center.region.service.impl;

import com.hxy.nzxy.stexam.center.region.dao.StudentInfoQueryDao;
import com.hxy.nzxy.stexam.center.region.domain.StudentInfoQueryDO;
import com.hxy.nzxy.stexam.center.region.service.StudentInfoQueryService;
import com.hxy.nzxy.stexam.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ypp
 * @Title: StudentInfoQueryServiceImpl
 * @Description:
 * @date 2018/12/1210:27
 */
@Service
public class StudentInfoQueryServiceImpl implements StudentInfoQueryService {
    @Autowired
    private StudentInfoQueryDao studentInfoQueryDao;
    @Override
    public List<StudentInfoQueryDO> list(Map<String, Object> map) {
        return studentInfoQueryDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return studentInfoQueryDao.count(map);
    }

    @Override
    public List<StudentInfoQueryDO> listDetail(Query query) {
        return studentInfoQueryDao.listDetail(query);
    }
    @Override
    public int countDetail(Query query) {
        return studentInfoQueryDao.countDetail(query);
    }
}
