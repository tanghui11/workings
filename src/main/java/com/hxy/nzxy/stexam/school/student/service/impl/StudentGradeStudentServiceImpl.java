package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.StudentGradeStudentDao;
import com.hxy.nzxy.stexam.domain.StudentGradeDO;
import com.hxy.nzxy.stexam.school.student.service.StudentGradeStudentService;



@Service
public class StudentGradeStudentServiceImpl implements StudentGradeStudentService {
	@Autowired
	private StudentGradeStudentDao studentGradeStudentDao;
	
	@Override
	public StudentGradeDO get(String id){
		return studentGradeStudentDao.get(id);
	}
	
	@Override
	public List<StudentGradeDO> list(Map<String, Object> map){
		return studentGradeStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentGradeStudentDao.count(map);
	}
	
	@Override
	public int save(StudentGradeDO studentGradeStudent){
		return studentGradeStudentDao.save(studentGradeStudent);
	}
	
	@Override
	public int update(StudentGradeDO studentGradeStudent){
		return studentGradeStudentDao.update(studentGradeStudent);
	}
	
	@Override
	public int remove(String id){
		return studentGradeStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return studentGradeStudentDao.batchRemove(ids);
	}
	
}
