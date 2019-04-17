package com.hxy.nzxy.stexam.center.school.dao;

import com.hxy.nzxy.stexam.domain.SchoolExamCourseDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 学院考试课程选择
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
@Mapper
public interface SchoolExamCourseDao {

	SchoolExamCourseDO get(Long id);
	
	List<SchoolExamCourseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolExamCourseDO schoolExamCourse);
	
	int update(SchoolExamCourseDO schoolExamCourse);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
