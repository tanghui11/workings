package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.ScoreReplaceRegionDao;
import com.hxy.nzxy.stexam.domain.ScoreReplaceDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreReplaceRegionService;



@Service
public class ScoreReplaceRegionServiceImpl implements ScoreReplaceRegionService {
	@Autowired
	private ScoreReplaceRegionDao scoreReplaceRegionDao;
	
	@Override
	public ScoreReplaceDO get(Long id){
		return scoreReplaceRegionDao.get(id);
	}
	
	@Override
	public List<ScoreReplaceDO> list(Map<String, Object> map){
		return scoreReplaceRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreReplaceRegionDao.count(map);
	}
	
	@Override
	public int save(ScoreReplaceDO scoreReplaceRegion){
		return scoreReplaceRegionDao.save(scoreReplaceRegion);
	}
	
	@Override
	public int update(ScoreReplaceDO scoreReplaceRegion){
		return scoreReplaceRegionDao.update(scoreReplaceRegion);
	}
	
	@Override
	public int remove(Long id){
		return scoreReplaceRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreReplaceRegionDao.batchRemove(ids);
	}
	
}
