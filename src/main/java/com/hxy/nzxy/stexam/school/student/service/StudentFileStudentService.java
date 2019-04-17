package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.StudentDO;
import com.hxy.nzxy.stexam.domain.StudentRegDO;
import com.hxy.nzxy.stexam.domain.StudentSpecialityDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface StudentFileStudentService {
    StudentDO get(String id);

    List<StudentDO> list(Map<String, Object> map);
    List<StudentDO> listQu(Map<String, Object> map);

    //List<StudentRegDO> listReg(Map<String, Object> map);

    int count(Map<String, Object> map);
    int countQu(Map<String, Object> map);

    int save(StudentDO studentStudent,StudentSpecialityDO studentSpeciality);

    int update(StudentDO studentStudent,StudentSpecialityDO studentSpeciality);

    int remove(String id);

    int batchRemove(String[] ids);

    int updateTx(String[] ids);

    String batchImport( String fileName, MultipartFile file);
}
