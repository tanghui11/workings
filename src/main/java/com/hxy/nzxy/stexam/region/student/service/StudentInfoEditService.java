package com.hxy.nzxy.stexam.region.student.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.region.student.domain.StudentInfoEditDO;

public interface StudentInfoEditService {

    StudentInfoEditDO list(Long id);

    int count(Query query);

    int save(StudentInfoEditDO studentInfoEdit);


}
