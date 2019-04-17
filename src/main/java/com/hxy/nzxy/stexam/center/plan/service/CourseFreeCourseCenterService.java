package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.domain.CourseFreeCourseDO;

import java.util.List;
import java.util.Map;

/**
 * 课程免考规则课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-16 10:13:51
 */
public interface CourseFreeCourseCenterService {
	
	CourseFreeCourseDO get(String id);
	
	List<CourseFreeCourseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseFreeCourseDO courseFreeCourseCenter);
	
	int update(CourseFreeCourseDO courseFreeCourseCenter);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
