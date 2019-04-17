package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.StudentPreDO;

import java.util.List;
import java.util.Map;

/**
 * 考生基本信息_预报名
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
public interface StudentPreStudentService {
	
	StudentPreDO get(Long id);
	
	List<StudentPreDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentPreDO studentPreStudent);
	
	int update(StudentPreDO studentPreStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
