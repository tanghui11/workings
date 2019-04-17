package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.PracticeSchoolHisDO;

import java.util.List;
import java.util.Map;

/**
 * 实践成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
public interface PracticeSchoolHisStudentService {
	
	PracticeSchoolHisDO get(Long id);
	
	List<PracticeSchoolHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PracticeSchoolHisDO practiceSchoolHisStudent);
	
	int update(PracticeSchoolHisDO practiceSchoolHisStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
