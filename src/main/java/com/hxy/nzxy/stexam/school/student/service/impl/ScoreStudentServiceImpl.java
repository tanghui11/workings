package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.ScoreStudentDao;
import com.hxy.nzxy.stexam.domain.ScoreDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreStudentService;



@Service
public class ScoreStudentServiceImpl implements ScoreStudentService {
	@Autowired
	private ScoreStudentDao scoreStudentDao;
	
	@Override
	public ScoreDO get(Long id){
		return scoreStudentDao.get(id);
	}
	
	@Override
	public List<ScoreDO> list(Map<String, Object> map){
		return scoreStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreStudentDao.count(map);
	}
	
	@Override
	public int save(ScoreDO scoreStudent){
		return scoreStudentDao.save(scoreStudent);
	}
	
	@Override
	public int update(ScoreDO scoreStudent){
		return scoreStudentDao.update(scoreStudent);
	}
	
	@Override
	public int remove(Long id){
		return scoreStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreStudentDao.batchRemove(ids);
	}
	
}
