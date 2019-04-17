package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.ScoreReplaceHisRegionDao;
import com.hxy.nzxy.stexam.domain.ScoreReplaceHisDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreReplaceHisRegionService;



@Service
public class ScoreReplaceHisRegionServiceImpl implements ScoreReplaceHisRegionService {
	@Autowired
	private ScoreReplaceHisRegionDao scoreReplaceHisRegionDao;
	
	@Override
	public ScoreReplaceHisDO get(Long id){
		return scoreReplaceHisRegionDao.get(id);
	}
	
	@Override
	public List<ScoreReplaceHisDO> list(Map<String, Object> map){
		return scoreReplaceHisRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreReplaceHisRegionDao.count(map);
	}
	
	@Override
	public int save(ScoreReplaceHisDO scoreReplaceHisRegion){
		return scoreReplaceHisRegionDao.save(scoreReplaceHisRegion);
	}
	
	@Override
	public int update(ScoreReplaceHisDO scoreReplaceHisRegion){
		return scoreReplaceHisRegionDao.update(scoreReplaceHisRegion);
	}
	
	@Override
	public int remove(Long id){
		return scoreReplaceHisRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreReplaceHisRegionDao.batchRemove(ids);
	}
	
}
