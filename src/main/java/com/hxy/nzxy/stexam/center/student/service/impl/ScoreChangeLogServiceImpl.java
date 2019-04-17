package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.ScoreChangeLogDao;
import com.hxy.nzxy.stexam.domain.ScoreChangeLogDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreChangeLogService;



@Service
public class ScoreChangeLogServiceImpl implements ScoreChangeLogService {
	@Autowired
	private ScoreChangeLogDao scoreChangeLogDao;
	
	@Override
	public ScoreChangeLogDO get(String changeType){
		return scoreChangeLogDao.get(changeType);
	}
	
	@Override
	public List<ScoreChangeLogDO> list(Map<String, Object> map){
		return scoreChangeLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreChangeLogDao.count(map);
	}
	
	@Override
	public int save(ScoreChangeLogDO scoreChangeLog){
		return scoreChangeLogDao.save(scoreChangeLog);
	}
	
	@Override
	public int update(ScoreChangeLogDO scoreChangeLog){
		return scoreChangeLogDao.update(scoreChangeLog);
	}
	
	@Override
	public int remove(String changeType){
		return scoreChangeLogDao.remove(changeType);
	}
	
	@Override
	public int batchRemove(String[] changeTypes){
		return scoreChangeLogDao.batchRemove(changeTypes);
	}
	
}
