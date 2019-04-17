package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.StudentTempStudentDao;
import com.hxy.nzxy.stexam.domain.StudentTempDO;
import com.hxy.nzxy.stexam.school.student.service.StudentTempStudentService;



@Service
public class StudentTempStudentServiceImpl implements StudentTempStudentService {
	@Autowired
	private StudentTempStudentDao studentTempStudentDao;
	
	@Override
	public StudentTempDO get(String type){
		return studentTempStudentDao.get(type);
	}
	
	@Override
	public List<StudentTempDO> list(Map<String, Object> map){
		return studentTempStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentTempStudentDao.count(map);
	}
	
	@Override
	public int save(StudentTempDO studentTempStudent){
		return studentTempStudentDao.save(studentTempStudent);
	}
	
	@Override
	public int update(StudentTempDO studentTempStudent){
		return studentTempStudentDao.update(studentTempStudent);
	}
	
	@Override
	public int remove(String type){
		return studentTempStudentDao.remove(type);
	}
	
	@Override
	public int batchRemove(String[] types){
		return studentTempStudentDao.batchRemove(types);
	}
	
}
