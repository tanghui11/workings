package com.hxy.nzxy.stexam.region.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.exam.dao.EducateLengthRegionDao;
import com.hxy.nzxy.stexam.domain.EducateLengthDO;
import com.hxy.nzxy.stexam.region.exam.service.EducateLengthRegionService;



@Service
public class EducateLengthRegionServiceImpl implements EducateLengthRegionService {
	@Autowired
	private EducateLengthRegionDao educateLengthRegionDao;
	
	@Override
	public EducateLengthDO get(Long id){
		return educateLengthRegionDao.get(id);
	}
	
	@Override
	public List<EducateLengthDO> list(Map<String, Object> map){
		return educateLengthRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return educateLengthRegionDao.count(map);
	}
	
	@Override
	public int save(EducateLengthDO educateLengthRegion){
		return educateLengthRegionDao.save(educateLengthRegion);
	}
	
	@Override
	public int update(EducateLengthDO educateLengthRegion){
		return educateLengthRegionDao.update(educateLengthRegion);
	}
	
	@Override
	public int remove(Long id){
		return educateLengthRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return educateLengthRegionDao.batchRemove(ids);
	}
	
}
