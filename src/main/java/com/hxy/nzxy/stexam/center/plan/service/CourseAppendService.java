package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.domain.CourseAppendDO;

import java.util.List;
import java.util.Map;

/**
 * 专业加考
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
public interface CourseAppendService {
	
	CourseAppendDO get(Long id);
	
	List<CourseAppendDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseAppendDO courseAppend);
	
	int update(CourseAppendDO courseAppend);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

}
