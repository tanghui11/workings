package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.CourseCopyDao;
import com.hxy.nzxy.stexam.center.plan.service.CourseCopyService;
import com.hxy.nzxy.stexam.domain.CourseCopyDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CourseCopyServiceImpl implements CourseCopyService {
	@Autowired
	private CourseCopyDao courseCopyDao;
	
	@Override
	public CourseCopyDO get(String id){
		return courseCopyDao.get(id);
	}
	
	@Override
	public List<CourseCopyDO> list(Map<String, Object> map){
		return courseCopyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseCopyDao.count(map);
	}
	
	@Override
	public int save(CourseCopyDO courseCopy){
		return courseCopyDao.save(courseCopy);
	}
	
	@Override
	public int update(CourseCopyDO courseCopy){
		return courseCopyDao.update(courseCopy);
	}
	
	@Override
	public int remove(String id){
		return courseCopyDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return courseCopyDao.batchRemove(ids);
	}
	
}
