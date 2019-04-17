package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentCertificateDO;

import java.util.List;

public interface StudentCertificateQueryService {
    int count(Query query);

    List<StudentCertificateDO> list(Query query);
}
