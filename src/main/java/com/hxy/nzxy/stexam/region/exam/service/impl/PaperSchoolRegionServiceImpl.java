package com.hxy.nzxy.stexam.region.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.exam.dao.PaperSchoolRegionDao;
import com.hxy.nzxy.stexam.domain.PaperSchoolDO;
import com.hxy.nzxy.stexam.region.exam.service.PaperSchoolRegionService;



@Service
public class PaperSchoolRegionServiceImpl implements PaperSchoolRegionService {
	@Autowired
	private PaperSchoolRegionDao paperSchoolRegionDao;
	
	@Override
	public PaperSchoolDO get(Long id){
		return paperSchoolRegionDao.get(id);
	}
	
	@Override
	public List<PaperSchoolDO> list(Map<String, Object> map){
		return paperSchoolRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return paperSchoolRegionDao.count(map);
	}
	
	@Override
	public int save(PaperSchoolDO paperSchoolRegion){
		return paperSchoolRegionDao.save(paperSchoolRegion);
	}
	
	@Override
	public int update(PaperSchoolDO paperSchoolRegion){
		return paperSchoolRegionDao.update(paperSchoolRegion);
	}
	
	@Override
	public int remove(Long id){
		return paperSchoolRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return paperSchoolRegionDao.batchRemove(ids);
	}
	
}
