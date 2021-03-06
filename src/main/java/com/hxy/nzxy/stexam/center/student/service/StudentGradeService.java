package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.StudentGradeDO;

import java.util.List;
import java.util.Map;

/**
 * 毕业前考生信息修改表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
public interface StudentGradeService {
	
	StudentGradeDO get(String id);
	
	List<StudentGradeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentGradeDO studentGrade);
	
	int update(StudentGradeDO studentGrade);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
