package com.hxy.nzxy.stexam.school.school.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.GeneralExaminationCourseDO;
import java.util.List;

public interface GeneralExaminationCourseDao {

    int count(Query query);

    List<GeneralExaminationCourseDO> list(Query query);
}