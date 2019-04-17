package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.StudentLogDO;

import java.util.List;
import java.util.Map;

/**
 * 考生变更日志
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
public interface StudentLogStudentService {
	
	StudentLogDO get(String changeType);
	
	List<StudentLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentLogDO studentLogStudent);
	
	int update(StudentLogDO studentLogStudent);
	
	int remove(String changeType);
	
	int batchRemove(String[] changeTypes);
}
