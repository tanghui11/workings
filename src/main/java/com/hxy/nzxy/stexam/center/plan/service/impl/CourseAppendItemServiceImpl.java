package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.CourseAppendItemDao;
import com.hxy.nzxy.stexam.center.plan.service.CourseAppendItemService;
import com.hxy.nzxy.stexam.domain.CourseAppendItemDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CourseAppendItemServiceImpl implements CourseAppendItemService {
	@Autowired
	private CourseAppendItemDao courseAppendItemDao;
	
	@Override
	public CourseAppendItemDO get(Long id){
		return courseAppendItemDao.get(id);
	}
	
	@Override
	public List<CourseAppendItemDO> list(Map<String, Object> map){
		return courseAppendItemDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseAppendItemDao.count(map);
	}
	
	@Override
	public int save(CourseAppendItemDO courseAppendItem){
		return courseAppendItemDao.save(courseAppendItem);
	}
	
	@Override
	public int update(CourseAppendItemDO courseAppendItem){
		return courseAppendItemDao.update(courseAppendItem);
	}
	
	@Override
	public int remove(Long id){
		return courseAppendItemDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return courseAppendItemDao.batchRemove(ids);
	}
	
}
