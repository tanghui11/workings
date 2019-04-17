package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.ScoreOldRegionDao;
import com.hxy.nzxy.stexam.domain.ScoreOldDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreOldRegionService;



@Service
public class ScoreOldRegionServiceImpl implements ScoreOldRegionService {
	@Autowired
	private ScoreOldRegionDao scoreOldRegionDao;
	
	@Override
	public ScoreOldDO get(Long id){
		return scoreOldRegionDao.get(id);
	}
	
	@Override
	public List<ScoreOldDO> list(Map<String, Object> map){
		return scoreOldRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreOldRegionDao.count(map);
	}
	
	@Override
	public int save(ScoreOldDO scoreOldRegion){
		return scoreOldRegionDao.save(scoreOldRegion);
	}
	
	@Override
	public int update(ScoreOldDO scoreOldRegion){
		return scoreOldRegionDao.update(scoreOldRegion);
	}
	
	@Override
	public int remove(Long id){
		return scoreOldRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreOldRegionDao.batchRemove(ids);
	}
	
}
