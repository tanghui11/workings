package com.hxy.nzxy.stexam.region.region.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.RegionGeneralExaminationCourseDO;

import java.util.List;

public interface RegionGeneralExaminationCourseDao {

    List<RegionGeneralExaminationCourseDO> list(Query query);

    int count(Query query);
}