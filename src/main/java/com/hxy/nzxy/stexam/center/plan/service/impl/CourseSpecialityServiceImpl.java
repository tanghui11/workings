package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.CourseSpecialityDao;
import com.hxy.nzxy.stexam.center.plan.service.CourseSpecialityService;
import com.hxy.nzxy.stexam.domain.CourseSpecialityDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CourseSpecialityServiceImpl implements CourseSpecialityService {
	@Autowired
	private CourseSpecialityDao courseSpecialityDao;
	
	@Override
	public CourseSpecialityDO get(Long id){
		return courseSpecialityDao.get(id);
	}
	
	@Override
	public List<CourseSpecialityDO> list(Map<String, Object> map){
		return courseSpecialityDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseSpecialityDao.count(map);
	}
	
	@Override
	public int save(CourseSpecialityDO courseSpeciality){
		return courseSpecialityDao.save(courseSpeciality);
	}

	@Override
	public int selectIn(CourseSpecialityDO courseSpeciality){
		return courseSpecialityDao.selectIn(courseSpeciality);
	}

	@Override
	public int update(CourseSpecialityDO courseSpeciality){
		return courseSpecialityDao.update(courseSpeciality);
	}
	
	@Override
	public int remove(Long id){
		return courseSpecialityDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return courseSpecialityDao.batchRemove(ids);
	}
	
}
