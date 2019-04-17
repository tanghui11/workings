package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.StudentCourseHisDO;

import java.util.List;
import java.util.Map;

/**
 * 考生报考表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
public interface StudentCourseHisService {
	
	StudentCourseHisDO get(Long id);
	
	List<StudentCourseHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentCourseHisDO studentCourseHis);
	
	int update(StudentCourseHisDO studentCourseHis);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
