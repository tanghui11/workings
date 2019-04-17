package com.hxy.nzxy.stexam.region.region.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.RegionGeneralExaminationCourseDO;

import java.util.List;

public interface RegionGeneralExaminationCourseService {

    List<RegionGeneralExaminationCourseDO> list(Query query);

    int count(Query query);

}
