package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.StudentCourseHisStudentDao;
import com.hxy.nzxy.stexam.domain.StudentCourseHisDO;
import com.hxy.nzxy.stexam.school.student.service.StudentCourseHisStudentService;



@Service
public class StudentCourseHisStudentServiceImpl implements StudentCourseHisStudentService {
	@Autowired
	private StudentCourseHisStudentDao studentCourseHisStudentDao;
	
	@Override
	public StudentCourseHisDO get(Long id){
		return studentCourseHisStudentDao.get(id);
	}
	
	@Override
	public List<StudentCourseHisDO> list(Map<String, Object> map){
		return studentCourseHisStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCourseHisStudentDao.count(map);
	}
	
	@Override
	public int save(StudentCourseHisDO studentCourseHisStudent){
		return studentCourseHisStudentDao.save(studentCourseHisStudent);
	}
	
	@Override
	public int update(StudentCourseHisDO studentCourseHisStudent){
		return studentCourseHisStudentDao.update(studentCourseHisStudent);
	}
	
	@Override
	public int remove(Long id){
		return studentCourseHisStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentCourseHisStudentDao.batchRemove(ids);
	}
	
}
