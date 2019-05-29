package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentDO;
import com.hxy.nzxy.stexam.domain.StudentRegDO;
import com.hxy.nzxy.stexam.domain.StudentSpecialityDO;

import java.util.List;
import java.util.Map;

/**
 * 考生注册
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:45
 */
public interface StudentRegStudentService {
	
	StudentRegDO get(Long id);
	
	List<StudentRegDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentRegDO studentRegStudent);
	
	int update(StudentRegDO studentRegStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<StudentSpecialityDO> listStudent(Query query);

	int countStudent(Query query);

	int selectYear(StudentRegDO studentReg);

    List<StudentSpecialityDO> listStudentStudent(Query query);

	int countStudentStudent(Query query);

    void batchAuditPass(Long[] ids);

	void batchAuditNoPass(Long[] ids);
}