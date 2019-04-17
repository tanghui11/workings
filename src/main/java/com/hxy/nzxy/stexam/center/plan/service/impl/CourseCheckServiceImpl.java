package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.CourseCheckDao;
import com.hxy.nzxy.stexam.center.plan.service.CourseCheckService;
import com.hxy.nzxy.stexam.domain.CourseCheckDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CourseCheckServiceImpl implements CourseCheckService {
	@Autowired
	private CourseCheckDao courseCheckDao;
	
	@Override
	public CourseCheckDO get(Long id){
		return courseCheckDao.get(id);
	}
	
	@Override
	public List<CourseCheckDO> list(Map<String, Object> map){
		return courseCheckDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseCheckDao.count(map);
	}
	
	@Override
	public int save(CourseCheckDO courseCheck){
		return courseCheckDao.save(courseCheck);
	}
	
	@Override
	public int update(CourseCheckDO courseCheck){
		return courseCheckDao.update(courseCheck);
	}
	
	@Override
	public int remove(Long id){
		return courseCheckDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return courseCheckDao.batchRemove(ids);
	}
	
}
