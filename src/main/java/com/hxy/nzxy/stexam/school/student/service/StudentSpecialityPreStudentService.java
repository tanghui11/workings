package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.StudentSpecialityPreDO;

import java.util.List;
import java.util.Map;

/**
 * 考生报考专业信息_预报名
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:45
 */
public interface StudentSpecialityPreStudentService {
	
	StudentSpecialityPreDO get(Long id);
	
	List<StudentSpecialityPreDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentSpecialityPreDO studentSpecialityPreStudent);
	
	int update(StudentSpecialityPreDO studentSpecialityPreStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
