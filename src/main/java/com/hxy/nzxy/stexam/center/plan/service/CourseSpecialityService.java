package com.hxy.nzxy.stexam.center.plan.service;

import com.hxy.nzxy.stexam.domain.CourseSpecialityDO;

import java.util.List;
import java.util.Map;

/**
 * 须加考课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
public interface CourseSpecialityService {
	
	CourseSpecialityDO get(Long id);
	
	List<CourseSpecialityDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CourseSpecialityDO courseSpeciality);
	int selectIn(CourseSpecialityDO courseSpeciality);

	int update(CourseSpecialityDO courseSpeciality);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
