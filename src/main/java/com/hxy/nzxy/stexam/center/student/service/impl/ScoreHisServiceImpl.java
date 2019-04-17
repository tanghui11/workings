package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.ScoreHisDao;
import com.hxy.nzxy.stexam.domain.ScoreHisDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreHisService;



@Service
public class ScoreHisServiceImpl implements ScoreHisService {
	@Autowired
	private ScoreHisDao scoreHisDao;
	
	@Override
	public ScoreHisDO get(Long id){
		return scoreHisDao.get(id);
	}
	
	@Override
	public List<ScoreHisDO> list(Map<String, Object> map){
		return scoreHisDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreHisDao.count(map);
	}
	
	@Override
	public int save(ScoreHisDO scoreHis){
		return scoreHisDao.save(scoreHis);
	}
	
	@Override
	public int update(ScoreHisDO scoreHis){
		return scoreHisDao.update(scoreHis);
	}
	
	@Override
	public int remove(Long id){
		return scoreHisDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreHisDao.batchRemove(ids);
	}
	
}
