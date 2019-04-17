package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.ScoreDO;
import com.hxy.nzxy.stexam.domain.SpecialityCourseDO;

import java.util.List;
import java.util.Map;

/**
 * 考生成绩表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
public interface ScoreService {
	
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

	int qxMerger(String studentid,String courseid);

	int updateSchoolScore(float retio,Long id, String courseid);

	int noUpdateSchoolScore(Long id, String courseid);

	int setMerger(String studentid, String courseid, float schoolGrade,float num);

	int batchRemove(Long[] ids);

	ScoreDO getRetio(String schoolid);

	float maxGrade(String studentid, String courseid, String specialityRecordid);

	int updateScoreSingle(ScoreDO score);

	List<ScoreDO> getScoreStudent(String studentid, String specialityRecordid, List<SpecialityCourseDO> list);
}
