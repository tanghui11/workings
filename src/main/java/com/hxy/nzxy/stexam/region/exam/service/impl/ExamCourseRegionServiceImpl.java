package com.hxy.nzxy.stexam.region.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.exam.dao.ExamCourseRegionDao;
import com.hxy.nzxy.stexam.domain.ExamCourseDO;
import com.hxy.nzxy.stexam.region.exam.service.ExamCourseRegionService;



@Service
public class ExamCourseRegionServiceImpl implements ExamCourseRegionService {
	@Autowired
	private ExamCourseRegionDao examCourseRegionDao;
	
	@Override
	public ExamCourseDO get(Long id){
		return examCourseRegionDao.get(id);
	}
	
	@Override
	public List<ExamCourseDO> list(Map<String, Object> map){
		return examCourseRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return examCourseRegionDao.count(map);
	}
	
	@Override
	public int save(ExamCourseDO examCourseRegion){
		return examCourseRegionDao.save(examCourseRegion);
	}
	
	@Override
	public int update(ExamCourseDO examCourseRegion){
		return examCourseRegionDao.update(examCourseRegion);
	}
	
	@Override
	public int remove(Long id){
		return examCourseRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return examCourseRegionDao.batchRemove(ids);
	}
	
}
