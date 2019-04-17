package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.SchoolScoreDO;

import java.util.List;
import java.util.Map;

/**
 * 平时成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
public interface SchoolScoreStudentService {
	
	SchoolScoreDO get(Long id);
	
	List<SchoolScoreDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolScoreDO schoolScoreStudent);
	
	int update(SchoolScoreDO schoolScoreStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	String getSpecialityRegcordid(String specialityid,String schoolid,String collegeid,String direction);

//	int inPutGrade(String studentid,String courseid,String grade,String specialityRecordid);
	int inPutGrade(Map<String,String> map);

	int whetherAreadyIn(Map<String,String> map);
}
