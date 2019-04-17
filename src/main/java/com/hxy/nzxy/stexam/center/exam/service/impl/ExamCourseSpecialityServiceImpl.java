package com.hxy.nzxy.stexam.center.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.exam.dao.ExamCourseSpecialityDao;
import com.hxy.nzxy.stexam.domain.ExamCourseSpecialityDO;
import com.hxy.nzxy.stexam.center.exam.service.ExamCourseSpecialityService;



@Service
public class ExamCourseSpecialityServiceImpl implements ExamCourseSpecialityService {
	@Autowired
	private ExamCourseSpecialityDao examCourseSpecialityDao;
	
	@Override
	public ExamCourseSpecialityDO get(Long id){
		return examCourseSpecialityDao.get(id);
	}
	
	@Override
	public List<ExamCourseSpecialityDO> list(Map<String, Object> map){
		return examCourseSpecialityDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return examCourseSpecialityDao.count(map);
	}
	
	@Override
	public int save(ExamCourseSpecialityDO examCourseSpeciality){
		return examCourseSpecialityDao.save(examCourseSpeciality);
	}
	
	@Override
	public int update(ExamCourseSpecialityDO examCourseSpeciality){
		return examCourseSpecialityDao.update(examCourseSpeciality);
	}
	
	@Override
	public int remove(Long id){
		return examCourseSpecialityDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return examCourseSpecialityDao.batchRemove(ids);
	}
	
}
