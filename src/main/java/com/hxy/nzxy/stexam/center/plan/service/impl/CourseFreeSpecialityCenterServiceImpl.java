package com.hxy.nzxy.stexam.center.plan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.plan.dao.CourseFreeSpecialityCenterDao;
import com.hxy.nzxy.stexam.domain.CourseFreeSpecialityDO;
import com.hxy.nzxy.stexam.center.plan.service.CourseFreeSpecialityCenterService;



@Service
public class CourseFreeSpecialityCenterServiceImpl implements CourseFreeSpecialityCenterService {
	@Autowired
	private CourseFreeSpecialityCenterDao courseFreeSpecialityCenterDao;
	
	@Override
	public CourseFreeSpecialityDO get(String id){
		return courseFreeSpecialityCenterDao.get(id);
	}
	
	@Override
	public List<CourseFreeSpecialityDO> list(Map<String, Object> map){
		return courseFreeSpecialityCenterDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseFreeSpecialityCenterDao.count(map);
	}
	
	@Override
	public int save(CourseFreeSpecialityDO courseFreeSpecialityCenter){
		return courseFreeSpecialityCenterDao.save(courseFreeSpecialityCenter);
	}
	
	@Override
	public int update(CourseFreeSpecialityDO courseFreeSpecialityCenter){
		return courseFreeSpecialityCenterDao.update(courseFreeSpecialityCenter);
	}
	
	@Override
	public int remove(String id){
		return courseFreeSpecialityCenterDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return courseFreeSpecialityCenterDao.batchRemove(ids);
	}
	
}
