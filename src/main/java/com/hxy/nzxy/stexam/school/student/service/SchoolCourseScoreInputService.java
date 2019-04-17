package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.ScoreSchoolDO;
import com.hxy.nzxy.stexam.domain.SpecialityCourseDO;
import com.hxy.nzxy.stexam.school.student.domain.SchoolCourseScoreInputDO;

import java.util.List;
import java.util.Map;

/**
 * 校考课程成绩录入
 * 
 * @author ypp
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
public interface SchoolCourseScoreInputService {

	SchoolCourseScoreInputDO get(Long id);

	List<SchoolCourseScoreInputDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(SchoolCourseScoreInputDO schoolScoreStudent);

	int update(SchoolCourseScoreInputDO schoolScoreStudent);

	int remove(Long id);

	int batchRemove(Long[] ids);

	String getSpecialityRegcordid(String specialityid, String schoolid, String collegeid, String direction);

//	int inPutGrade(String studentid,String courseid,String grade,String specialityRecordid);
	int inPutGrade(Map<String, String> map);

	int whetherAreadyIn(Map<String, String> map);

    List<SpecialityCourseDO> listSchoolCourse(Map<String,Object> params);

}
