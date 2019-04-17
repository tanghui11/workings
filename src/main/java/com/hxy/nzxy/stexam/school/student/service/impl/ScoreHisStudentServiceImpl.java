package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.ScoreHisStudentDao;
import com.hxy.nzxy.stexam.domain.ScoreHisDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreHisStudentService;



@Service
public class ScoreHisStudentServiceImpl implements ScoreHisStudentService {
	@Autowired
	private ScoreHisStudentDao scoreHisStudentDao;
	
	@Override
	public ScoreHisDO get(Long id){
		return scoreHisStudentDao.get(id);
	}
	
	@Override
	public List<ScoreHisDO> list(Map<String, Object> map){
		return scoreHisStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreHisStudentDao.count(map);
	}
	
	@Override
	public int save(ScoreHisDO scoreHisStudent){
		return scoreHisStudentDao.save(scoreHisStudent);
	}
	
	@Override
	public int update(ScoreHisDO scoreHisStudent){
		return scoreHisStudentDao.update(scoreHisStudent);
	}
	
	@Override
	public int remove(Long id){
		return scoreHisStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreHisStudentDao.batchRemove(ids);
	}
	
}
