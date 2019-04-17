package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.ScoreRegionDao;
import com.hxy.nzxy.stexam.domain.ScoreDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreRegionService;



@Service
public class ScoreRegionServiceImpl implements ScoreRegionService {
	@Autowired
	private ScoreRegionDao scoreRegionDao;
	
	@Override
	public ScoreDO get(Long id){
		return scoreRegionDao.get(id);
	}
	
	@Override
	public List<ScoreDO> list(Map<String, Object> map){
		return scoreRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreRegionDao.count(map);
	}
	
	@Override
	public int save(ScoreDO scoreRegion){
		return scoreRegionDao.save(scoreRegion);
	}
	
	@Override
	public int update(ScoreDO scoreRegion){
		return scoreRegionDao.update(scoreRegion);
	}
	
	@Override
	public int remove(Long id){
		return scoreRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreRegionDao.batchRemove(ids);
	}
	
}
