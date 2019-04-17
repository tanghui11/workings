package com.hxy.nzxy.stexam.center.exam.dao;

import com.hxy.nzxy.stexam.domain.ExamCourseSpecialityDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 课程限报专业
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
@Mapper
public interface ExamCourseSpecialityDao {

	ExamCourseSpecialityDO get(Long id);
	
	List<ExamCourseSpecialityDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamCourseSpecialityDO examCourseSpeciality);
	
	int update(ExamCourseSpecialityDO examCourseSpeciality);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
