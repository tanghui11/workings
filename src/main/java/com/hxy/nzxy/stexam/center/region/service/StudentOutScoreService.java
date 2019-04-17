package com.hxy.nzxy.stexam.center.region.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentOutScoreDO;

import java.util.List;

public interface StudentOutScoreService {

    StudentOutScoreDO get(Long id);

    List<StudentOutScoreDO> list(Query query);

    int count(Query query);
}