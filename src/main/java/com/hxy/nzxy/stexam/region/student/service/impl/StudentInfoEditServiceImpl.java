package com.hxy.nzxy.stexam.region.student.service.impl;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.region.student.dao.StudentInfoEditDao;
import com.hxy.nzxy.stexam.region.student.domain.StudentInfoEditDO;
import com.hxy.nzxy.stexam.region.student.service.StudentInfoEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ypp
 * @Title: StudentInfoEditServiceImpl
 * @Description:
 * @date 2018/12/1211:15
 */
@Service
public class StudentInfoEditServiceImpl implements StudentInfoEditService {

    @Autowired
    private StudentInfoEditDao studentInfoEditDao;

    @Override
    public StudentInfoEditDO list(Long studentid) {
        return studentInfoEditDao.list(studentid);
    }

    @Override
    public int count(Query query) {
        return studentInfoEditDao.count(query);
    }

    @Override
    public int save(StudentInfoEditDO studentInfoEdit) {
        return studentInfoEditDao.save(studentInfoEdit);
    }
}
