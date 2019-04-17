package com.hxy.nzxy.stexam.region.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.region.dao.ExamSiteSubmitHisRegionDao;
import com.hxy.nzxy.stexam.domain.ExamSiteSubmitHisDO;
import com.hxy.nzxy.stexam.region.region.service.ExamSiteSubmitHisRegionService;



@Service
public class ExamSiteSubmitHisRegionServiceImpl implements ExamSiteSubmitHisRegionService {
	@Autowired
	private ExamSiteSubmitHisRegionDao examSiteSubmitHisRegionDao;
	
	@Override
	public ExamSiteSubmitHisDO get(Long id){
		return examSiteSubmitHisRegionDao.get(id);
	}
	
	@Override
	public List<ExamSiteSubmitHisDO> list(Map<String, Object> map){
		return examSiteSubmitHisRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return examSiteSubmitHisRegionDao.count(map);
	}
	
	@Override
	public int save(ExamSiteSubmitHisDO examSiteSubmitHisRegion){
		return examSiteSubmitHisRegionDao.save(examSiteSubmitHisRegion);
	}
	
	@Override
	public int update(ExamSiteSubmitHisDO examSiteSubmitHisRegion){
		return examSiteSubmitHisRegionDao.update(examSiteSubmitHisRegion);
	}
	
	@Override
	public int remove(Long id){
		return examSiteSubmitHisRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return examSiteSubmitHisRegionDao.batchRemove(ids);
	}
	
}
