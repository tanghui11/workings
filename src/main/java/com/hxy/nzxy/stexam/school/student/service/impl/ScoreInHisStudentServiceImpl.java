package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.ScoreInHisStudentDao;
import com.hxy.nzxy.stexam.domain.ScoreInHisDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreInHisStudentService;



@Service
public class ScoreInHisStudentServiceImpl implements ScoreInHisStudentService {
	@Autowired
	private ScoreInHisStudentDao scoreInHisStudentDao;
	
	@Override
	public ScoreInHisDO get(Long id){
		return scoreInHisStudentDao.get(id);
	}
	
	@Override
	public List<ScoreInHisDO> list(Map<String, Object> map){
		return scoreInHisStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreInHisStudentDao.count(map);
	}
	
	@Override
	public int save(ScoreInHisDO scoreInHisStudent){
		return scoreInHisStudentDao.save(scoreInHisStudent);
	}
	
	@Override
	public int update(ScoreInHisDO scoreInHisStudent){
		return scoreInHisStudentDao.update(scoreInHisStudent);
	}
	
	@Override
	public int remove(Long id){
		return scoreInHisStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreInHisStudentDao.batchRemove(ids);
	}
	
}
