package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.ScoreSchoolHisRegionDao;
import com.hxy.nzxy.stexam.domain.ScoreSchoolHisDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreSchoolHisRegionService;



@Service
public class ScoreSchoolHisRegionServiceImpl implements ScoreSchoolHisRegionService {
	@Autowired
	private ScoreSchoolHisRegionDao scoreSchoolHisRegionDao;
	
	@Override
	public ScoreSchoolHisDO get(Long id){
		return scoreSchoolHisRegionDao.get(id);
	}
	
	@Override
	public List<ScoreSchoolHisDO> list(Map<String, Object> map){
		return scoreSchoolHisRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreSchoolHisRegionDao.count(map);
	}
	
	@Override
	public int save(ScoreSchoolHisDO scoreSchoolHisRegion){
		return scoreSchoolHisRegionDao.save(scoreSchoolHisRegion);
	}
	
	@Override
	public int update(ScoreSchoolHisDO scoreSchoolHisRegion){
		return scoreSchoolHisRegionDao.update(scoreSchoolHisRegion);
	}
	
	@Override
	public int remove(Long id){
		return scoreSchoolHisRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreSchoolHisRegionDao.batchRemove(ids);
	}
	
}
