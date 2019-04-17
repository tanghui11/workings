package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentCertificateDO;

import java.util.List;

public interface StudentCertificateQueryDao {

    int count(Query query);

    List<StudentCertificateDO> list(Query query);
}
