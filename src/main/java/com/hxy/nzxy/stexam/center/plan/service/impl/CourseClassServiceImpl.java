package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.CourseClassDao;
import com.hxy.nzxy.stexam.center.plan.service.CourseClassService;
import com.hxy.nzxy.stexam.domain.CourseClassDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CourseClassServiceImpl implements CourseClassService {
	@Autowired
	private CourseClassDao courseClassDao;
	
	@Override
	public CourseClassDO get(String type){
		return courseClassDao.get(type);
	}
	
	@Override
	public List<CourseClassDO> list(Map<String, Object> map){
		return courseClassDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseClassDao.count(map);
	}
	
	@Override
	public int save(CourseClassDO courseClass){
		return courseClassDao.save(courseClass);
	}
	
	@Override
	public int update(CourseClassDO courseClass){
		return courseClassDao.update(courseClass);
	}
	
	@Override
	public int remove(String type){
		return courseClassDao.remove(type);
	}
	
	@Override
	public int batchRemove(String[] types){
		return courseClassDao.batchRemove(types);
	}
	
}
