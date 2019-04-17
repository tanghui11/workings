package com.hxy.nzxy.stexam.region.exam.dao;

import com.hxy.nzxy.stexam.domain.ExamCourseSpecialityDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 课程限报专业
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:22
 */
@Mapper
public interface ExamCourseSpecialityRegionDao {

	ExamCourseSpecialityDO get(Long id);
	
	List<ExamCourseSpecialityDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamCourseSpecialityDO examCourseSpecialityRegion);
	
	int update(ExamCourseSpecialityDO examCourseSpecialityRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
