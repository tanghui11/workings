package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.PracticeSchoolDO;

import java.util.List;
import java.util.Map;

/**
 * 实践成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
public interface PracticeSchoolStudentService {
	
	PracticeSchoolDO get(Long id);
	
	List<PracticeSchoolDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PracticeSchoolDO practiceSchoolStudent);
	
	int update(PracticeSchoolDO practiceSchoolStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
