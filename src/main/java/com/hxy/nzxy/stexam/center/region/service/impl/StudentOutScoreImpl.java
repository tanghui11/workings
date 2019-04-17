package com.hxy.nzxy.stexam.center.region.service.impl;

import com.hxy.nzxy.stexam.center.region.dao.StudentOutScoreDao;
import com.hxy.nzxy.stexam.center.region.service.StudentOutScoreService;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentOutScoreDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ypp
 * @Title: StudentOutScoreImpl
 * @Description:
 * @date 2018/11/2812:01
 */

@Service
public class StudentOutScoreImpl implements StudentOutScoreService {
    @Autowired
    StudentOutScoreDao studentOutScoreDao;

    @Override
    public StudentOutScoreDO get(Long id) {
        return studentOutScoreDao.get(id);
    }

    @Override
    public List<StudentOutScoreDO> list(Query query) {
        return studentOutScoreDao.list(query);
    }

    @Override
    public int count(Query query) {
        return studentOutScoreDao.count(query);
    }
}