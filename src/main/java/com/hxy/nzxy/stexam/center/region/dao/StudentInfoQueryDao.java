package com.hxy.nzxy.stexam.center.region.dao;

import com.hxy.nzxy.stexam.center.region.domain.StudentInfoQueryDO;
import com.hxy.nzxy.stexam.common.utils.Query;

import java.util.List;
import java.util.Map;

public interface StudentInfoQueryDao {

    List<StudentInfoQueryDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    List<StudentInfoQueryDO> listDetail(Query query);

    int countDetail(Query query);
}
