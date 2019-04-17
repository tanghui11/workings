package com.hxy.nzxy.stexam.region.student.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.PracticeSchoolDO;
import com.hxy.nzxy.stexam.domain.SpecialityCourseDO;

import java.util.List;
import java.util.Map;

/**
 * 实践成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
public interface PracticeSchoolRegionService {
	
	PracticeSchoolDO get(Long id);
	
	List<PracticeSchoolDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PracticeSchoolDO practiceSchoolRegion);
	
	int update(PracticeSchoolDO practiceSchoolRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<SpecialityCourseDO> listCourse(Query query);

	int countCourse(Query query);
}
