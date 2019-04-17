package com.hxy.nzxy.stexam.school.school.service;

import com.hxy.nzxy.stexam.domain.SchoolExamCourseDO;

import java.util.List;
import java.util.Map;

/**
 * 学院考试课程选择
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:41
 */
public interface SchoolExamCourseSchoolService {
	
	SchoolExamCourseDO get(Long id);
	
	List<SchoolExamCourseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolExamCourseDO schoolExamCourseSchool);
	
	int update(SchoolExamCourseDO schoolExamCourseSchool);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
