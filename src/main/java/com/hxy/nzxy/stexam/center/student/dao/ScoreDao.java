package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreDO;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.domain.SpecialityCourseDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 考生成绩表_历史表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
@Mapper
public interface ScoreDao {

	ScoreDO get(Long id);
	
	List<ScoreDO> list(Map<String, Object> map);

	List<ScoreDO> listTwo(Map<String, Object> map);

	List<ScoreDO> schoolScoreStudent(Map<String, Object> map);

	List<ScoreDO> listMerger(Map<String, Object> map);

	int countMerger(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(ScoreDO score);
	
	int update(ScoreDO score);
	
	int remove(Long id);

	int qxMerger(@Param("studentid") String studentid, @Param("courseid") String courseid);

	int updateSchoolScore(@Param("retio") float retio,@Param("studentid") Long id,@Param("courseid") String courseid);
	int noUpdateSchoolScore(@Param("studentid") Long id,@Param("courseid") String courseid);

	int batchRemove(Long[] ids);

	ScoreDO getRetio(@Param("schoolid") String schoolid);

	int setMerger(@Param("studentid") String studentid, @Param("courseid") String courseid, @Param("schoolGrade") float schoolGrade, @Param("num") float num);

	@Select("select max(grade) from stu_school_score where studentid = #{studentid} and courseid = #{courseid} and speciality_recordid = #{specialityRecordid}")
	float maxGrade(@Param("studentid") String studentid, @Param("courseid") String courseid, @Param("specialityRecordid") String specialityRecordid);

    int updateScoreSingle(ScoreDO score);

	List<ScoreDO> getScoreStudent(@Param("studentid") String studentid,@Param("specialityRecordid") String specialityRecordid,@Param("list") List<SpecialityCourseDO> list);
}
