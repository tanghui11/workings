package com.hxy.nzxy.stexam.center.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.school.dao.SchoolExamCourseDao;
import com.hxy.nzxy.stexam.domain.SchoolExamCourseDO;
import com.hxy.nzxy.stexam.center.school.service.SchoolExamCourseService;



@Service
public class SchoolExamCourseServiceImpl implements SchoolExamCourseService {
	@Autowired
	private SchoolExamCourseDao schoolExamCourseDao;
	
	@Override
	public SchoolExamCourseDO get(Long id){
		return schoolExamCourseDao.get(id);
	}
	
	@Override
	public List<SchoolExamCourseDO> list(Map<String, Object> map){
		return schoolExamCourseDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return schoolExamCourseDao.count(map);
	}
	
	@Override
	public int save(SchoolExamCourseDO schoolExamCourse){
		return schoolExamCourseDao.save(schoolExamCourse);
	}
	
	@Override
	public int update(SchoolExamCourseDO schoolExamCourse){
		return schoolExamCourseDao.update(schoolExamCourse);
	}
	
	@Override
	public int remove(Long id){
		return schoolExamCourseDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return schoolExamCourseDao.batchRemove(ids);
	}
	
}
