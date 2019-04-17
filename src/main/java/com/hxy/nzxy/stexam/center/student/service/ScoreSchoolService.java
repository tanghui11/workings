package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ScoreSchoolDO;

import java.util.List;
import java.util.Map;

/**
 * 校考成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
public interface ScoreSchoolService {
	
	ScoreSchoolDO get(Long id);
	
	List<ScoreSchoolDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreSchoolDO scoreSchool);
	
	int update(ScoreSchoolDO scoreSchool);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<ScoreSchoolDO> studentScoreList(Query query);

	int studentScoreListcount(Query query);

	List<ScoreSchoolDO> schoolCourseList(Query query);

	int schoolCourseListcount(Query query);
}
