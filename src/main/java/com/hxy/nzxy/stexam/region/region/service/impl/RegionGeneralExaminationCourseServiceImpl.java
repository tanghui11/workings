package com.hxy.nzxy.stexam.region.region.service.impl;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.RegionGeneralExaminationCourseDO;
import com.hxy.nzxy.stexam.region.region.dao.RegionGeneralExaminationCourseDao;
import com.hxy.nzxy.stexam.region.region.service.RegionGeneralExaminationCourseService;
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
public class RegionGeneralExaminationCourseServiceImpl implements RegionGeneralExaminationCourseService {
    @Autowired
    private RegionGeneralExaminationCourseDao regionGeneralExaminationCourseDao;

    @Override
    public List<RegionGeneralExaminationCourseDO> list(Query query) {
        return regionGeneralExaminationCourseDao.list(query);
    }

    @Override
    public int count(Query query) {
        return regionGeneralExaminationCourseDao.count(query);
    }
}