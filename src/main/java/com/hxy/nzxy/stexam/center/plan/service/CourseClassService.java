package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.domain.CourseClassDO;

import java.util.List;
import java.util.Map;

/**
 * 课程管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
public interface CourseClassService {
	
	CourseClassDO get(String type);
	
	List<CourseClassDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseClassDO courseClass);
	
	int update(CourseClassDO courseClass);
	
	int remove(String type);
	
	int batchRemove(String[] types);
}
