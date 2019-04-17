package com.hxy.nzxy.stexam.school.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.school.dao.SchoolExamCourseSchoolDao;
import com.hxy.nzxy.stexam.domain.SchoolExamCourseDO;
import com.hxy.nzxy.stexam.school.school.service.SchoolExamCourseSchoolService;



@Service
public class SchoolExamCourseSchoolServiceImpl implements SchoolExamCourseSchoolService {
	@Autowired
	private SchoolExamCourseSchoolDao schoolExamCourseSchoolDao;
	
	@Override
	public SchoolExamCourseDO get(Long id){
		return schoolExamCourseSchoolDao.get(id);
	}
	
	@Override
	public List<SchoolExamCourseDO> list(Map<String, Object> map){
		return schoolExamCourseSchoolDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return schoolExamCourseSchoolDao.count(map);
	}
	
	@Override
	public int save(SchoolExamCourseDO schoolExamCourseSchool){
		return schoolExamCourseSchoolDao.save(schoolExamCourseSchool);
	}
	
	@Override
	public int update(SchoolExamCourseDO schoolExamCourseSchool){
		return schoolExamCourseSchoolDao.update(schoolExamCourseSchool);
	}
	
	@Override
	public int remove(Long id){
		return schoolExamCourseSchoolDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return schoolExamCourseSchoolDao.batchRemove(ids);
	}
	
}
