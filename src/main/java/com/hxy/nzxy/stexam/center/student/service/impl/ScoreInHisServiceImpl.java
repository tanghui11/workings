package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.ScoreInHisDao;
import com.hxy.nzxy.stexam.domain.ScoreInHisDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreInHisService;



@Service
public class ScoreInHisServiceImpl implements ScoreInHisService {
	@Autowired
	private ScoreInHisDao scoreInHisDao;
	
	@Override
	public ScoreInHisDO get(Long id){
		return scoreInHisDao.get(id);
	}
	
	@Override
	public List<ScoreInHisDO> list(Map<String, Object> map){
		return scoreInHisDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreInHisDao.count(map);
	}
	
	@Override
	public int save(ScoreInHisDO scoreInHis){
		return scoreInHisDao.save(scoreInHis);
	}
	
	@Override
	public int update(ScoreInHisDO scoreInHis){
		return scoreInHisDao.update(scoreInHis);
	}
	
	@Override
	public int remove(Long id){
		return scoreInHisDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreInHisDao.batchRemove(ids);
	}
	
}
