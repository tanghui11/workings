package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.StudentSpecialityHisDO;

import java.util.List;
import java.util.Map;

/**
 * 考生报考专业信息表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:45
 */
public interface StudentSpecialityHisStudentService {
	
	StudentSpecialityHisDO get(Long id);
	
	List<StudentSpecialityHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentSpecialityHisDO studentSpecialityHisStudent);
	
	int update(StudentSpecialityHisDO studentSpecialityHisStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
