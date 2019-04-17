package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.StudentChangeDO;

import java.util.List;
import java.util.Map;

/**
 * 考生信息变更表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
public interface StudentChangeStudentService {
	
	StudentChangeDO get(Long id);
	
	List<StudentChangeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentChangeDO studentChangeStudent);
	
	int update(StudentChangeDO studentChangeStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
