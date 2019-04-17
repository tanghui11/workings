package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.ScoreChangeLogRegionDao;
import com.hxy.nzxy.stexam.domain.ScoreChangeLogDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreChangeLogRegionService;



@Service
public class ScoreChangeLogRegionServiceImpl implements ScoreChangeLogRegionService {
	@Autowired
	private ScoreChangeLogRegionDao scoreChangeLogRegionDao;
	
	@Override
	public ScoreChangeLogDO get(String changeType){
		return scoreChangeLogRegionDao.get(changeType);
	}
	
	@Override
	public List<ScoreChangeLogDO> list(Map<String, Object> map){
		return scoreChangeLogRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreChangeLogRegionDao.count(map);
	}
	
	@Override
	public int save(ScoreChangeLogDO scoreChangeLogRegion){
		return scoreChangeLogRegionDao.save(scoreChangeLogRegion);
	}
	
	@Override
	public int update(ScoreChangeLogDO scoreChangeLogRegion){
		return scoreChangeLogRegionDao.update(scoreChangeLogRegion);
	}
	
	@Override
	public int remove(String changeType){
		return scoreChangeLogRegionDao.remove(changeType);
	}
	
	@Override
	public int batchRemove(String[] changeTypes){
		return scoreChangeLogRegionDao.batchRemove(changeTypes);
	}
	
}
