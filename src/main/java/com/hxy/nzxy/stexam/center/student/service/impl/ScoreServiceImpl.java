package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.domain.SpecialityCourseDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.ScoreDao;
import com.hxy.nzxy.stexam.domain.ScoreDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreService;



@Service
public class ScoreServiceImpl implements ScoreService {
	@Autowired
	private ScoreDao scoreDao;
	
	@Override
	public ScoreDO get(Long id){
		return scoreDao.get(id);
	}
	
	@Override
	public List<ScoreDO> list(Map<String, Object> map){
		return scoreDao.list(map);
	}

	@Override
	public List<ScoreDO> listTwo(Map<String, Object> map){
		return scoreDao.listTwo(map);
	}
	@Override
	public List<ScoreDO> schoolScoreStudent(Map<String, Object> map){
		return scoreDao.schoolScoreStudent(map);
	}
	@Override
	public List<ScoreDO> listMerger(Map<String, Object> map){
		return scoreDao.listMerger(map);
	}

	@Override
	public int countMerger(Map<String, Object> map){
		return scoreDao.countMerger(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreDao.count(map);
	}
	
	@Override
	public int save(ScoreDO score){
		return scoreDao.save(score);
	}
	
	@Override
	public int update(ScoreDO score){
		return scoreDao.update(score);
	}
	
	@Override
	public int remove(Long id){
		return scoreDao.remove(id);
	}

	@Override
	public int qxMerger(String studentid,String courseid){
		return scoreDao.qxMerger(studentid, courseid);
	}

	@Override
	public int setMerger(String studentid, String courseid, float schoolGrade,float num){
		return scoreDao.setMerger(studentid, courseid, schoolGrade, num);
	}
	@Override
	public int updateSchoolScore(float retio,Long id, String courseid){
		return scoreDao.updateSchoolScore(retio,id,courseid);
	}

	@Override
	public int noUpdateSchoolScore(Long id, String courseid){
		return scoreDao.noUpdateSchoolScore(id,courseid);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreDao.batchRemove(ids);
	}

	@Override
	public ScoreDO getRetio(String schoolid){
		return scoreDao.getRetio(schoolid);
	}

	@Override
	public float maxGrade(String studentid, String courseid, String specialityRecordid){
		return scoreDao.maxGrade(studentid, courseid, specialityRecordid);
	}

	@Override
	public int updateScoreSingle(ScoreDO score) {
		return scoreDao.updateScoreSingle(score);
	}

	@Override
	public List<ScoreDO> getScoreStudent(String studentid, String specialityRecordid, List<SpecialityCourseDO> list) {
		return scoreDao.getScoreStudent(studentid,specialityRecordid,list);
	}

}
