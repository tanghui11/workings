package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.ScoreChangeLogStudentDao;
import com.hxy.nzxy.stexam.domain.ScoreChangeLogDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreChangeLogStudentService;



@Service
public class ScoreChangeLogStudentServiceImpl implements ScoreChangeLogStudentService {
	@Autowired
	private ScoreChangeLogStudentDao scoreChangeLogStudentDao;
	
	@Override
	public ScoreChangeLogDO get(String changeType){
		return scoreChangeLogStudentDao.get(changeType);
	}
	
	@Override
	public List<ScoreChangeLogDO> list(Map<String, Object> map){
		return scoreChangeLogStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreChangeLogStudentDao.count(map);
	}
	
	@Override
	public int save(ScoreChangeLogDO scoreChangeLogStudent){
		return scoreChangeLogStudentDao.save(scoreChangeLogStudent);
	}
	
	@Override
	public int update(ScoreChangeLogDO scoreChangeLogStudent){
		return scoreChangeLogStudentDao.update(scoreChangeLogStudent);
	}
	
	@Override
	public int remove(String changeType){
		return scoreChangeLogStudentDao.remove(changeType);
	}
	
	@Override
	public int batchRemove(String[] changeTypes){
		return scoreChangeLogStudentDao.batchRemove(changeTypes);
	}
	
}
