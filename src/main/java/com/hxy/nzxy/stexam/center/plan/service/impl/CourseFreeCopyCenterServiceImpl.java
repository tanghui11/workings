package com.hxy.nzxy.stexam.center.plan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.plan.dao.CourseFreeCopyCenterDao;
import com.hxy.nzxy.stexam.domain.CourseFreeCopyDO;
import com.hxy.nzxy.stexam.center.plan.service.CourseFreeCopyCenterService;



@Service
public class CourseFreeCopyCenterServiceImpl implements CourseFreeCopyCenterService {
	@Autowired
	private CourseFreeCopyCenterDao courseFreeCopyCenterDao;
	
	@Override
	public CourseFreeCopyDO get(String id){
		return courseFreeCopyCenterDao.get(id);
	}
	
	@Override
	public List<CourseFreeCopyDO> list(Map<String, Object> map){
		return courseFreeCopyCenterDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseFreeCopyCenterDao.count(map);
	}
	
	@Override
	public int save(CourseFreeCopyDO courseFreeCopyCenter){
		return courseFreeCopyCenterDao.save(courseFreeCopyCenter);
	}
	
	@Override
	public int update(CourseFreeCopyDO courseFreeCopyCenter){
		return courseFreeCopyCenterDao.update(courseFreeCopyCenter);
	}
	
	@Override
	public int remove(String id){
		return courseFreeCopyCenterDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return courseFreeCopyCenterDao.batchRemove(ids);
	}
	
}
