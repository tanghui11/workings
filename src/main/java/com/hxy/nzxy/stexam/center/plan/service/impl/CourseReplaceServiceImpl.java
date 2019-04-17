package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.CourseReplaceDao;
import com.hxy.nzxy.stexam.center.plan.service.CourseReplaceService;
import com.hxy.nzxy.stexam.domain.CourseReplaceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CourseReplaceServiceImpl implements CourseReplaceService {
	@Autowired
	private CourseReplaceDao courseReplaceDao;
	
	@Override
	public CourseReplaceDO get(Long id){
		return courseReplaceDao.get(id);
	}
	
	@Override
	public List<CourseReplaceDO> list(Map<String, Object> map){
		return courseReplaceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseReplaceDao.count(map);
	}
	
	@Override
	public int save(CourseReplaceDO courseReplace){
		return courseReplaceDao.save(courseReplace);
	}
	
	@Override
	public int update(CourseReplaceDO courseReplace){
		return courseReplaceDao.update(courseReplace);
	}
	
	@Override
	public int remove(Long id){
		return courseReplaceDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return courseReplaceDao.batchRemove(ids);
	}
	
}
