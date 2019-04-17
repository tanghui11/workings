package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.region.student.domain.StudentInfoEditDO;

public interface StudentInfoEditDao {

    StudentInfoEditDO list(Long studentid);

    int count(Query query);

    int save(StudentInfoEditDO studentInfoEdit);
}
