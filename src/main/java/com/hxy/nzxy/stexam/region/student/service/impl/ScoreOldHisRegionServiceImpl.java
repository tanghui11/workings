package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.ScoreOldHisRegionDao;
import com.hxy.nzxy.stexam.domain.ScoreOldHisDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreOldHisRegionService;



@Service
public class ScoreOldHisRegionServiceImpl implements ScoreOldHisRegionService {
	@Autowired
	private ScoreOldHisRegionDao scoreOldHisRegionDao;
	
	@Override
	public ScoreOldHisDO get(Long id){
		return scoreOldHisRegionDao.get(id);
	}
	
	@Override
	public List<ScoreOldHisDO> list(Map<String, Object> map){
		return scoreOldHisRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreOldHisRegionDao.count(map);
	}
	
	@Override
	public int save(ScoreOldHisDO scoreOldHisRegion){
		return scoreOldHisRegionDao.save(scoreOldHisRegion);
	}
	
	@Override
	public int update(ScoreOldHisDO scoreOldHisRegion){
		return scoreOldHisRegionDao.update(scoreOldHisRegion);
	}
	
	@Override
	public int remove(Long id){
		return scoreOldHisRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreOldHisRegionDao.batchRemove(ids);
	}
	
}
