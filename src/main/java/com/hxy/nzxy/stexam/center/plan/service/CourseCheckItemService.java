package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.domain.CourseCheckItemDO;

import java.util.List;
import java.util.Map;

/**
 * 复选课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
public interface CourseCheckItemService {
	
	CourseCheckItemDO get(Long id);
	
	List<CourseCheckItemDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseCheckItemDO courseCheckItem);
	
	int update(CourseCheckItemDO courseCheckItem);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
