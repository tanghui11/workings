package com.hxy.nzxy.stexam.school.school.service.impl;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.CourseExemptionDO;
import com.hxy.nzxy.stexam.school.school.dao.CourseExemptionDao;
import com.hxy.nzxy.stexam.school.school.service.CourseExemptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ypp
 * @Title: CourseExemptionServiceImpl
 * @Description:
 * @date 2018/11/2017:00
 */

@Service
public class CourseExemptionServiceImpl implements CourseExemptionService {

    @Autowired
    private CourseExemptionDao courseExemptionDao;

    @Override
    public List<CourseExemptionDO> list(Query query) {
        return courseExemptionDao.list(query);
    }

    @Override
    public int count(Query query) {
        return courseExemptionDao.count(query);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return courseExemptionDao.batchRemove(ids);
    }

    @Override
    public int remove(Long id) {
        return courseExemptionDao.remove(id);
    }
}
