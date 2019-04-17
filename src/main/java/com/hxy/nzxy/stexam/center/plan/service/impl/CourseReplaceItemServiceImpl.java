package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.CourseReplaceItemDao;
import com.hxy.nzxy.stexam.center.plan.service.CourseReplaceItemService;
import com.hxy.nzxy.stexam.domain.CourseReplaceItemDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CourseReplaceItemServiceImpl implements CourseReplaceItemService {
	@Autowired
	private CourseReplaceItemDao courseReplaceItemDao;
	
	@Override
	public CourseReplaceItemDO get(Long id){
		return courseReplaceItemDao.get(id);
	}
	
	@Override
	public List<CourseReplaceItemDO> list(Map<String, Object> map){
		return courseReplaceItemDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseReplaceItemDao.count(map);
	}
	
	@Override
	public int save(CourseReplaceItemDO courseReplaceItem){
		return courseReplaceItemDao.save(courseReplaceItem);
	}

	@Override
	public CourseReplaceItemDO selectInDB(CourseReplaceItemDO courseReplaceItem){
		return courseReplaceItemDao.selectInDB(courseReplaceItem);
	}

	@Override
	public int update(CourseReplaceItemDO courseReplaceItem){
		return courseReplaceItemDao.update(courseReplaceItem);
	}
	
	@Override
	public int remove(Long id){
		return courseReplaceItemDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return courseReplaceItemDao.batchRemove(ids);
	}
	
}
