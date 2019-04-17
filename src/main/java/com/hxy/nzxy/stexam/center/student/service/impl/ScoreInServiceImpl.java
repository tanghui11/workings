package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.center.student.dao.ScoreInDao;
import com.hxy.nzxy.stexam.center.student.service.ScoreInService;
import com.hxy.nzxy.stexam.domain.ScoreInDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class ScoreInServiceImpl implements ScoreInService {
	@Autowired
	private ScoreInDao scoreInDao;
	
	@Override
	public ScoreInDO get(Long id){
		return scoreInDao.get(id);
	}
	
	@Override
	public List<ScoreInDO> list(Map<String, Object> map){
		return scoreInDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreInDao.count(map);
	}
	
	@Override
	public int save(ScoreInDO scoreIn){
		return scoreInDao.save(scoreIn);
	}
	
	@Override
	public int update(ScoreInDO scoreIn){
		return scoreInDao.update(scoreIn);
	}
	
	@Override
	public int audit(Long id){
		return scoreInDao.audit(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreInDao.batchRemove(ids);
	}
	
}
