package com.hxy.nzxy.stexam.region.region.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.RegionCourseExemptionDO;
import com.hxy.nzxy.stexam.domain.StudentDO;

import java.util.List;

public interface RegionCourseExemptionService {

    List<StudentDO> list(Query query);

    int count(Query query);

    int batchRemove(Long[] ids);

    int remove(Long id);
}
