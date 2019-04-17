package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.ScoreInRegionDao;
import com.hxy.nzxy.stexam.domain.ScoreInDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreInRegionService;



@Service
public class ScoreInRegionServiceImpl implements ScoreInRegionService {
	@Autowired
	private ScoreInRegionDao scoreInRegionDao;
	
	@Override
	public ScoreInDO get(Long id){
		return scoreInRegionDao.get(id);
	}
	
	@Override
	public List<ScoreInDO> list(Map<String, Object> map){
		return scoreInRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreInRegionDao.count(map);
	}
	
	@Override
	public int save(ScoreInDO scoreInRegion){
		return scoreInRegionDao.save(scoreInRegion);
	}
	
	@Override
	public int update(ScoreInDO scoreInRegion){
		return scoreInRegionDao.update(scoreInRegion);
	}
	
	@Override
	public int remove(Long id){
		return scoreInRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreInRegionDao.batchRemove(ids);
	}
	
}
