package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.StudentGradeDO;

import java.util.List;
import java.util.Map;

/**
 * 毕业前考生信息修改表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
public interface StudentGradeStudentService {
	
	StudentGradeDO get(String id);
	
	List<StudentGradeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentGradeDO studentGradeStudent);
	
	int update(StudentGradeDO studentGradeStudent);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
