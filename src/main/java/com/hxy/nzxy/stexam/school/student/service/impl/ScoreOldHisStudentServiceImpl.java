package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.ScoreOldHisStudentDao;
import com.hxy.nzxy.stexam.domain.ScoreOldHisDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreOldHisStudentService;



@Service
public class ScoreOldHisStudentServiceImpl implements ScoreOldHisStudentService {
	@Autowired
	private ScoreOldHisStudentDao scoreOldHisStudentDao;
	
	@Override
	public ScoreOldHisDO get(Long id){
		return scoreOldHisStudentDao.get(id);
	}
	
	@Override
	public List<ScoreOldHisDO> list(Map<String, Object> map){
		return scoreOldHisStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreOldHisStudentDao.count(map);
	}
	
	@Override
	public int save(ScoreOldHisDO scoreOldHisStudent){
		return scoreOldHisStudentDao.save(scoreOldHisStudent);
	}
	
	@Override
	public int update(ScoreOldHisDO scoreOldHisStudent){
		return scoreOldHisStudentDao.update(scoreOldHisStudent);
	}
	
	@Override
	public int remove(Long id){
		return scoreOldHisStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreOldHisStudentDao.batchRemove(ids);
	}
	
}
