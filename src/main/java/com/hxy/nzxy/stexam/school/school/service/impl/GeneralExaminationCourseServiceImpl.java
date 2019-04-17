package com.hxy.nzxy.stexam.school.school.service.impl;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.GeneralExaminationCourseDO;
import com.hxy.nzxy.stexam.school.school.dao.GeneralExaminationCourseDao;
import com.hxy.nzxy.stexam.school.school.service.GeneralExaminationCourseService;
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
public class GeneralExaminationCourseServiceImpl implements GeneralExaminationCourseService {
    @Autowired
    private GeneralExaminationCourseDao generalExaminationCourseDao;
    @Override
    public List<GeneralExaminationCourseDO> list(Query query) {
        return generalExaminationCourseDao.list(query);
    }

    @Override
    public int count(Query query) {
        return generalExaminationCourseDao.count(query);
    }
}