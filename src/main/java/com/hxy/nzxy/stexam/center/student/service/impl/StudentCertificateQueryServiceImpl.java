package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.center.student.dao.StudentCertificateQueryDao;
import com.hxy.nzxy.stexam.center.student.service.StudentCertificateQueryService;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentCertificateDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ypp
 * @Title: StudentCertificateQueryImpl
 * @Description:
 * @date 2018/12/1818:16
 */
@Service
public class StudentCertificateQueryServiceImpl implements StudentCertificateQueryService {
    @Autowired
    private StudentCertificateQueryDao studentCertificateQueryDao;

    @Override
    public int count(Query query) {
        return studentCertificateQueryDao.count(query);
    }

    @Override
    public List<StudentCertificateDO> list(Query query) {
        return studentCertificateQueryDao.list(query);
    }
}
