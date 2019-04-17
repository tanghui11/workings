package com.hxy.nzxy.stexam.center.region.service;

import com.hxy.nzxy.stexam.center.region.domain.StudentRegionQueryDO;
import com.hxy.nzxy.stexam.common.utils.Query;

import java.util.List;

public interface StudentRegionQueryService {
    List<StudentRegionQueryDO> list(Query query);

    int count(Query query);
}
