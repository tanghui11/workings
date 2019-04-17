package com.hxy.nzxy.stexam.school.school.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.CourseExemptionDO;

import java.util.List;

public interface CourseExemptionService {

    List<CourseExemptionDO> list(Query query);

    int count(Query query);

    int batchRemove(Long[] ids);

    int remove(Long id);
}
