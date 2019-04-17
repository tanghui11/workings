package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.domain.CourseReplaceDO;

import java.util.List;
import java.util.Map;

/**
 * 课程顶替
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
public interface CourseReplaceService {
	
	CourseReplaceDO get(Long id);
	
	List<CourseReplaceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseReplaceDO courseReplace);
	
	int update(CourseReplaceDO courseReplace);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
