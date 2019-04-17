package com.hxy.nzxy.stexam.region.region.service.impl;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.RegionCourseExemptionDO;
import com.hxy.nzxy.stexam.domain.StudentDO;
import com.hxy.nzxy.stexam.region.region.dao.RegionCourseExemptionDao;
import com.hxy.nzxy.stexam.region.region.service.RegionCourseExemptionService;
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
public class RegionCourseExemptionServiceImpl implements RegionCourseExemptionService {

    @Autowired
    private RegionCourseExemptionDao regionCourseExemptionDao;

    @Override
    public List<StudentDO> list(Query query) {
        return regionCourseExemptionDao.list(query);
    }

    @Override
    public int count(Query query) {
        return regionCourseExemptionDao.count(query);
    }

    @Override
    public int batchRemove(Long[] ids) {
        return regionCourseExemptionDao.batchRemove(ids);
    }

    @Override
    public int remove(Long id) {
        return regionCourseExemptionDao.remove(id);
    }
}
