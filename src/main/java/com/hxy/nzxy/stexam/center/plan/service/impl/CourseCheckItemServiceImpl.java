package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.CourseCheckItemDao;
import com.hxy.nzxy.stexam.center.plan.service.CourseCheckItemService;
import com.hxy.nzxy.stexam.domain.CourseCheckItemDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CourseCheckItemServiceImpl implements CourseCheckItemService {
	@Autowired
	private CourseCheckItemDao courseCheckItemDao;
	
	@Override
	public CourseCheckItemDO get(Long id){
		return courseCheckItemDao.get(id);
	}
	
	@Override
	public List<CourseCheckItemDO> list(Map<String, Object> map){
		return courseCheckItemDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseCheckItemDao.count(map);
	}
	
	@Override
	public int save(CourseCheckItemDO courseCheckItem){
		return courseCheckItemDao.save(courseCheckItem);
	}
	
	@Override
	public int update(CourseCheckItemDO courseCheckItem){
		return courseCheckItemDao.update(courseCheckItem);
	}
	
	@Override
	public int remove(Long id){
		return courseCheckItemDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return courseCheckItemDao.batchRemove(ids);
	}
	
}
