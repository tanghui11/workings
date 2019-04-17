package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.ScoreSchoolHisDO;

import java.util.List;
import java.util.Map;

/**
 * 校考成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
public interface ScoreSchoolHisStudentService {
	
	ScoreSchoolHisDO get(Long id);
	
	List<ScoreSchoolHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreSchoolHisDO scoreSchoolHisStudent);
	
	int update(ScoreSchoolHisDO scoreSchoolHisStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
