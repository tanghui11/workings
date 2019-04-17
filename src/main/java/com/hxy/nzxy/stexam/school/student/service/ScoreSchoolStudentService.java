package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ScoreSchoolDO;

import java.util.List;
import java.util.Map;

/**
 * 校考成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
public interface ScoreSchoolStudentService {
	
	ScoreSchoolDO get(Long id);
	
	List<ScoreSchoolDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreSchoolDO scoreSchoolStudent);
	
	int update(ScoreSchoolDO scoreSchoolStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

    List<ScoreSchoolDO> schoolCourseList(Query query);

	int schoolCourseListcount(Query query);

	List<ScoreSchoolDO> studentScoreList(Query query);

	int studentScoreListcount(Query query);
}
