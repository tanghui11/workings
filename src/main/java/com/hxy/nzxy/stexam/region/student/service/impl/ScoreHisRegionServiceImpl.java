package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.ScoreHisRegionDao;
import com.hxy.nzxy.stexam.domain.ScoreHisDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreHisRegionService;



@Service
public class ScoreHisRegionServiceImpl implements ScoreHisRegionService {
	@Autowired
	private ScoreHisRegionDao scoreHisRegionDao;
	
	@Override
	public ScoreHisDO get(Long id){
		return scoreHisRegionDao.get(id);
	}
	
	@Override
	public List<ScoreHisDO> list(Map<String, Object> map){
		return scoreHisRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreHisRegionDao.count(map);
	}
	
	@Override
	public int save(ScoreHisDO scoreHisRegion){
		return scoreHisRegionDao.save(scoreHisRegion);
	}
	
	@Override
	public int update(ScoreHisDO scoreHisRegion){
		return scoreHisRegionDao.update(scoreHisRegion);
	}
	
	@Override
	public int remove(Long id){
		return scoreHisRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreHisRegionDao.batchRemove(ids);
	}
	
}
