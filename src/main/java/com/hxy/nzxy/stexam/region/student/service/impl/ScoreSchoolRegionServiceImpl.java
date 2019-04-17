package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.ScoreSchoolRegionDao;
import com.hxy.nzxy.stexam.domain.ScoreSchoolDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreSchoolRegionService;



@Service
public class ScoreSchoolRegionServiceImpl implements ScoreSchoolRegionService {
	@Autowired
	private ScoreSchoolRegionDao scoreSchoolRegionDao;
	
	@Override
	public ScoreSchoolDO get(Long id){
		return scoreSchoolRegionDao.get(id);
	}
	
	@Override
	public List<ScoreSchoolDO> list(Map<String, Object> map){
		return scoreSchoolRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreSchoolRegionDao.count(map);
	}
	
	@Override
	public int save(ScoreSchoolDO scoreSchoolRegion){
		return scoreSchoolRegionDao.save(scoreSchoolRegion);
	}
	
	@Override
	public int update(ScoreSchoolDO scoreSchoolRegion){
		return scoreSchoolRegionDao.update(scoreSchoolRegion);
	}
	
	@Override
	public int remove(Long id){
		return scoreSchoolRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreSchoolRegionDao.batchRemove(ids);
	}
	
}
