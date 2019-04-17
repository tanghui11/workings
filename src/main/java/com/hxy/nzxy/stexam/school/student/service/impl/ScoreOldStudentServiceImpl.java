package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.ScoreOldStudentDao;
import com.hxy.nzxy.stexam.domain.ScoreOldDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreOldStudentService;



@Service
public class ScoreOldStudentServiceImpl implements ScoreOldStudentService {
	@Autowired
	private ScoreOldStudentDao scoreOldStudentDao;
	
	@Override
	public ScoreOldDO get(Long id){
		return scoreOldStudentDao.get(id);
	}
	
	@Override
	public List<ScoreOldDO> list(Map<String, Object> map){
		return scoreOldStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreOldStudentDao.count(map);
	}
	
	@Override
	public int save(ScoreOldDO scoreOldStudent){
		return scoreOldStudentDao.save(scoreOldStudent);
	}
	
	@Override
	public int update(ScoreOldDO scoreOldStudent){
		return scoreOldStudentDao.update(scoreOldStudent);
	}
	
	@Override
	public int remove(Long id){
		return scoreOldStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreOldStudentDao.batchRemove(ids);
	}
	
}
