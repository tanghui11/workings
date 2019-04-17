package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.StudentInDO;

import java.util.List;
import java.util.Map;

/**
 * 考生转入考籍
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
public interface StudentInStudentService {
	
	StudentInDO get(Long id);
	
	List<StudentInDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentInDO studentInStudent);
	
	int update(StudentInDO studentInStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
