package com.hxy.nzxy.stexam.school.school.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.GeneralExaminationCourseDO;
import java.util.List;

public interface GeneralExaminationCourseService {

    List<GeneralExaminationCourseDO> list(Query query);

    int count(Query query);

}
