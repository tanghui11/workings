package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.ScoreImportRegionDao;
import com.hxy.nzxy.stexam.domain.ScoreImportDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreImportRegionService;



@Service
public class ScoreImportRegionServiceImpl implements ScoreImportRegionService {
	@Autowired
	private ScoreImportRegionDao scoreImportRegionDao;
	
	@Override
	public ScoreImportDO get(String kemuid){
		return scoreImportRegionDao.get(kemuid);
	}
	
	@Override
	public List<ScoreImportDO> list(Map<String, Object> map){
		return scoreImportRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreImportRegionDao.count(map);
	}
	
	@Override
	public int save(ScoreImportDO scoreImportRegion){
		return scoreImportRegionDao.save(scoreImportRegion);
	}
	
	@Override
	public int update(ScoreImportDO scoreImportRegion){
		return scoreImportRegionDao.update(scoreImportRegion);
	}
	
	@Override
	public int remove(String kemuid){
		return scoreImportRegionDao.remove(kemuid);
	}
	
	@Override
	public int batchRemove(String[] kemuids){
		return scoreImportRegionDao.batchRemove(kemuids);
	}
	
}
