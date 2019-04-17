package com.hxy.nzxy.stexam.school.student.dao;

import com.hxy.nzxy.stexam.domain.SchoolScoreDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 平时成绩
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
@Mapper
public interface SchoolScoreStudentDao {

	SchoolScoreDO get(Long id);
	
	List<SchoolScoreDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SchoolScoreDO schoolScoreStudent);
	
	int update(SchoolScoreDO schoolScoreStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	String getSpecialityRegcordid(@Param("specialityid") String specialityid, @Param("schoolid") String schoolid, @Param("collegeid") String collegeid, @Param("direction") String direction);

//	int inPutGrade(@Param("studentid") String studentid,@Param("courseid") String courseid,@Param("grade") String grade,@Param("specialityRecordid") String specialityRecordid);
	int inPutGrade(Map<String,String> map);
	int whetherAreadyIn(Map<String,String> map);
}
