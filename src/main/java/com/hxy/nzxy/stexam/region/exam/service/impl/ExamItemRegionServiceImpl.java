package com.hxy.nzxy.stexam.region.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.exam.dao.ExamItemRegionDao;
import com.hxy.nzxy.stexam.domain.ExamItemDO;
import com.hxy.nzxy.stexam.region.exam.service.ExamItemRegionService;



@Service
public class ExamItemRegionServiceImpl implements ExamItemRegionService {
	@Autowired
	private ExamItemRegionDao examItemRegionDao;
	
	@Override
	public ExamItemDO get(Long id){
		return examItemRegionDao.get(id);
	}
	
	@Override
	public List<ExamItemDO> list(Map<String, Object> map){
		return examItemRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return examItemRegionDao.count(map);
	}
	
	@Override
	public int save(ExamItemDO examItemRegion){
		return examItemRegionDao.save(examItemRegion);
	}
	
	@Override
	public int update(ExamItemDO examItemRegion){
		return examItemRegionDao.update(examItemRegion);
	}
	
	@Override
	public int remove(Long id){
		return examItemRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return examItemRegionDao.batchRemove(ids);
	}
	
}
