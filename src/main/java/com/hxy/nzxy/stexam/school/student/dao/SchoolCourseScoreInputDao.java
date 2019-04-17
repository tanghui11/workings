package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreSchoolDO;
import com.hxy.nzxy.stexam.domain.SpecialityCourseDO;
import com.hxy.nzxy.stexam.school.student.domain.SchoolCourseScoreInputDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 校考课程成绩录入
 * @author ypp
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
@Mapper
public interface SchoolCourseScoreInputDao {

	SchoolCourseScoreInputDO get(Long id);

	List<SchoolCourseScoreInputDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(SchoolCourseScoreInputDO schoolScoreStudent);

	int update(SchoolCourseScoreInputDO schoolScoreStudent);

	int remove(Long id);

	int batchRemove(Long[] ids);

	String getSpecialityRegcordid(@Param("specialityid") String specialityid, @Param("schoolid") String schoolid, @Param("collegeid") String collegeid, @Param("direction") String direction);

//	int inPutGrade(@Param("studentid") String studentid,@Param("courseid") String courseid,@Param("grade") String grade,@Param("specialityRecordid") String specialityRecordid);
	int inPutGrade(Map<String, String> map);
	int whetherAreadyIn(Map<String, String> map);

    List<SpecialityCourseDO> listSchoolCourse(Map<String,Object> params);

}
