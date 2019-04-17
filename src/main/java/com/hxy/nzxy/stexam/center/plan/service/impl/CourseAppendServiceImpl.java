package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.CourseAppendDao;
import com.hxy.nzxy.stexam.center.plan.service.CourseAppendService;
import com.hxy.nzxy.stexam.domain.CourseAppendDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CourseAppendServiceImpl implements CourseAppendService {
	@Autowired
	private CourseAppendDao courseAppendDao;
	
	@Override
	public CourseAppendDO get(Long id){
		return courseAppendDao.get(id);
	}
	
	@Override
	public List<CourseAppendDO> list(Map<String, Object> map){
		return courseAppendDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseAppendDao.count(map);
	}
	
	@Override
	public int save(CourseAppendDO courseAppend){
		return courseAppendDao.save(courseAppend);
	}
	
	@Override
	public int update(CourseAppendDO courseAppend){
		return courseAppendDao.update(courseAppend);
	}
	
	@Override
	public int remove(Long id){
		return courseAppendDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return courseAppendDao.batchRemove(ids);
	}

}
