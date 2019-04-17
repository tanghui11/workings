package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.ScoreOldDao;
import com.hxy.nzxy.stexam.domain.ScoreOldDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreOldService;



@Service
public class ScoreOldServiceImpl implements ScoreOldService {
	@Autowired
	private ScoreOldDao scoreOldDao;
	
	@Override
	public ScoreOldDO get(Long id){
		return scoreOldDao.get(id);
	}
	
	@Override
	public List<ScoreOldDO> list(Map<String, Object> map){
		return scoreOldDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreOldDao.count(map);
	}
	
	@Override
	public int save(ScoreOldDO scoreOld){
		return scoreOldDao.save(scoreOld);
	}
	
	@Override
	public int update(ScoreOldDO scoreOld){
		return scoreOldDao.update(scoreOld);
	}

	@Override
	public int updateScore(Map<String,Object> map){
		return scoreOldDao.updateScore(map);
	}

	@Override
	public int remove(Long id){
		return scoreOldDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreOldDao.batchRemove(ids);
	}
	
}
