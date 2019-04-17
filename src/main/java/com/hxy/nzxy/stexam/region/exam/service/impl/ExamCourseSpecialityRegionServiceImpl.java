package com.hxy.nzxy.stexam.region.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.exam.dao.ExamCourseSpecialityRegionDao;
import com.hxy.nzxy.stexam.domain.ExamCourseSpecialityDO;
import com.hxy.nzxy.stexam.region.exam.service.ExamCourseSpecialityRegionService;



@Service
public class ExamCourseSpecialityRegionServiceImpl implements ExamCourseSpecialityRegionService {
	@Autowired
	private ExamCourseSpecialityRegionDao examCourseSpecialityRegionDao;
	
	@Override
	public ExamCourseSpecialityDO get(Long id){
		return examCourseSpecialityRegionDao.get(id);
	}
	
	@Override
	public List<ExamCourseSpecialityDO> list(Map<String, Object> map){
		return examCourseSpecialityRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return examCourseSpecialityRegionDao.count(map);
	}
	
	@Override
	public int save(ExamCourseSpecialityDO examCourseSpecialityRegion){
		return examCourseSpecialityRegionDao.save(examCourseSpecialityRegion);
	}
	
	@Override
	public int update(ExamCourseSpecialityDO examCourseSpecialityRegion){
		return examCourseSpecialityRegionDao.update(examCourseSpecialityRegion);
	}
	
	@Override
	public int remove(Long id){
		return examCourseSpecialityRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return examCourseSpecialityRegionDao.batchRemove(ids);
	}
	
}
