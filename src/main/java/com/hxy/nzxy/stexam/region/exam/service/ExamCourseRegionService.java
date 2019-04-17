package com.hxy.nzxy.stexam.region.exam.service;

import com.hxy.nzxy.stexam.domain.ExamCourseDO;

import java.util.List;
import java.util.Map;

/**
 * 开考课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:22
 */
public interface ExamCourseRegionService {
	
	ExamCourseDO get(Long id);
	
	List<ExamCourseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamCourseDO examCourseRegion);
	
	int update(ExamCourseDO examCourseRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
