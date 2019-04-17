package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.ScoreInHisRegionDao;
import com.hxy.nzxy.stexam.domain.ScoreInHisDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreInHisRegionService;



@Service
public class ScoreInHisRegionServiceImpl implements ScoreInHisRegionService {
	@Autowired
	private ScoreInHisRegionDao scoreInHisRegionDao;
	
	@Override
	public ScoreInHisDO get(Long id){
		return scoreInHisRegionDao.get(id);
	}
	
	@Override
	public List<ScoreInHisDO> list(Map<String, Object> map){
		return scoreInHisRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreInHisRegionDao.count(map);
	}
	
	@Override
	public int save(ScoreInHisDO scoreInHisRegion){
		return scoreInHisRegionDao.save(scoreInHisRegion);
	}
	
	@Override
	public int update(ScoreInHisDO scoreInHisRegion){
		return scoreInHisRegionDao.update(scoreInHisRegion);
	}
	
	@Override
	public int remove(Long id){
		return scoreInHisRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreInHisRegionDao.batchRemove(ids);
	}
	
}
