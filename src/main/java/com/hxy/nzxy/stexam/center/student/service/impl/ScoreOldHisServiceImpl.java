package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.ScoreOldHisDao;
import com.hxy.nzxy.stexam.domain.ScoreOldHisDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreOldHisService;



@Service
public class ScoreOldHisServiceImpl implements ScoreOldHisService {
	@Autowired
	private ScoreOldHisDao scoreOldHisDao;
	
	@Override
	public ScoreOldHisDO get(Long id){
		return scoreOldHisDao.get(id);
	}
	
	@Override
	public List<ScoreOldHisDO> list(Map<String, Object> map){
		return scoreOldHisDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreOldHisDao.count(map);
	}
	
	@Override
	public int save(ScoreOldHisDO scoreOldHis){
		return scoreOldHisDao.save(scoreOldHis);
	}
	
	@Override
	public int update(ScoreOldHisDO scoreOldHis){
		return scoreOldHisDao.update(scoreOldHis);
	}
	
	@Override
	public int remove(Long id){
		return scoreOldHisDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreOldHisDao.batchRemove(ids);
	}
	
}
