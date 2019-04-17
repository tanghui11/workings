package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.StudentCourseTempStudentDao;
import com.hxy.nzxy.stexam.domain.StudentCourseTempDO;
import com.hxy.nzxy.stexam.school.student.service.StudentCourseTempStudentService;



@Service
public class StudentCourseTempStudentServiceImpl implements StudentCourseTempStudentService {
	@Autowired
	private StudentCourseTempStudentDao studentCourseTempStudentDao;
	
	@Override
	public StudentCourseTempDO get(Long id){
		return studentCourseTempStudentDao.get(id);
	}
	
	@Override
	public List<StudentCourseTempDO> list(Map<String, Object> map){
		return studentCourseTempStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCourseTempStudentDao.count(map);
	}
	
	@Override
	public int save(StudentCourseTempDO studentCourseTempStudent){
		return studentCourseTempStudentDao.save(studentCourseTempStudent);
	}
	
	@Override
	public int update(StudentCourseTempDO studentCourseTempStudent){
		return studentCourseTempStudentDao.update(studentCourseTempStudent);
	}
	
	@Override
	public int remove(Long id){
		return studentCourseTempStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentCourseTempStudentDao.batchRemove(ids);
	}
	
}
