package com.hxy.nzxy.stexam.center.plan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.plan.dao.CourseFreeCourseCenterDao;
import com.hxy.nzxy.stexam.domain.CourseFreeCourseDO;
import com.hxy.nzxy.stexam.center.plan.service.CourseFreeCourseCenterService;



@Service
public class CourseFreeCourseCenterServiceImpl implements CourseFreeCourseCenterService {
	@Autowired
	private CourseFreeCourseCenterDao courseFreeCourseCenterDao;
	
	@Override
	public CourseFreeCourseDO get(String id){
		return courseFreeCourseCenterDao.get(id);
	}
	
	@Override
	public List<CourseFreeCourseDO> list(Map<String, Object> map){
		return courseFreeCourseCenterDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseFreeCourseCenterDao.count(map);
	}
	
	@Override
	public int save(CourseFreeCourseDO courseFreeCourseCenter){
		return courseFreeCourseCenterDao.save(courseFreeCourseCenter);
	}
	
	@Override
	public int update(CourseFreeCourseDO courseFreeCourseCenter){
		return courseFreeCourseCenterDao.update(courseFreeCourseCenter);
	}
	
	@Override
	public int remove(String id){
		return courseFreeCourseCenterDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return courseFreeCourseCenterDao.batchRemove(ids);
	}
	
}
