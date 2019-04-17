package com.hxy.nzxy.stexam.center.region.dao;

import com.hxy.nzxy.stexam.center.region.domain.StudentRegionQueryDO;
import com.hxy.nzxy.stexam.common.utils.Query;

import java.util.List;

public interface StudentRegionQueryDao {
    List<StudentRegionQueryDO> list(Query query);

    int count(Query query);
}
