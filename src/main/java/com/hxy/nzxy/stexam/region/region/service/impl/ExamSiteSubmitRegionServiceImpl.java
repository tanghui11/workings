package com.hxy.nzxy.stexam.region.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.region.dao.ExamSiteSubmitRegionDao;
import com.hxy.nzxy.stexam.domain.ExamSiteSubmitDO;
import com.hxy.nzxy.stexam.region.region.service.ExamSiteSubmitRegionService;



@Service
public class ExamSiteSubmitRegionServiceImpl implements ExamSiteSubmitRegionService {
	@Autowired
	private ExamSiteSubmitRegionDao examSiteSubmitRegionDao;
	
	@Override
	public ExamSiteSubmitDO get(Long id){
		return examSiteSubmitRegionDao.get(id);
	}
	
	@Override
	public List<ExamSiteSubmitDO> list(Map<String, Object> map){
		return examSiteSubmitRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return examSiteSubmitRegionDao.count(map);
	}
	
	@Override
	public int save(ExamSiteSubmitDO examSiteSubmitRegion){
		return examSiteSubmitRegionDao.save(examSiteSubmitRegion);
	}
	
	@Override
	public int update(ExamSiteSubmitDO examSiteSubmitRegion){
		return examSiteSubmitRegionDao.update(examSiteSubmitRegion);
	}
	
	@Override
	public int remove(Long id){
		return examSiteSubmitRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return examSiteSubmitRegionDao.batchRemove(ids);
	}
	
}
