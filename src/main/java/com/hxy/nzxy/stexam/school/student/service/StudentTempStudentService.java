package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.StudentTempDO;

import java.util.List;
import java.util.Map;

/**
 * 考生基本信息临时表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:45
 */
public interface StudentTempStudentService {
	
	StudentTempDO get(String type);
	
	List<StudentTempDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentTempDO studentTempStudent);
	
	int update(StudentTempDO studentTempStudent);
	
	int remove(String type);
	
	int batchRemove(String[] types);
}
