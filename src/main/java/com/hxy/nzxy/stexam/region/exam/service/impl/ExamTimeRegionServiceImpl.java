package com.hxy.nzxy.stexam.region.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.exam.dao.ExamTimeRegionDao;
import com.hxy.nzxy.stexam.domain.ExamTimeDO;
import com.hxy.nzxy.stexam.region.exam.service.ExamTimeRegionService;



@Service
public class ExamTimeRegionServiceImpl implements ExamTimeRegionService {
	@Autowired
	private ExamTimeRegionDao examTimeRegionDao;
	
	@Override
	public ExamTimeDO get(Long id){
		return examTimeRegionDao.get(id);
	}
	
	@Override
	public List<ExamTimeDO> list(Map<String, Object> map){
		return examTimeRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return examTimeRegionDao.count(map);
	}
	
	@Override
	public int save(ExamTimeDO examTimeRegion){
		return examTimeRegionDao.save(examTimeRegion);
	}
	
	@Override
	public int update(ExamTimeDO examTimeRegion){
		return examTimeRegionDao.update(examTimeRegion);
	}
	
	@Override
	public int remove(Long id){
		return examTimeRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return examTimeRegionDao.batchRemove(ids);
	}
	
}
