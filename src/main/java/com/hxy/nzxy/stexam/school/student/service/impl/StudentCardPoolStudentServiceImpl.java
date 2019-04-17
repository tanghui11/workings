package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.StudentCardPoolStudentDao;
import com.hxy.nzxy.stexam.domain.StudentCardPoolDO;
import com.hxy.nzxy.stexam.school.student.service.StudentCardPoolStudentService;



@Service
public class StudentCardPoolStudentServiceImpl implements StudentCardPoolStudentService {
	@Autowired
	private StudentCardPoolStudentDao studentCardPoolStudentDao;
	
	@Override
	public StudentCardPoolDO get(String id){
		return studentCardPoolStudentDao.get(id);
	}
	
	@Override
	public List<StudentCardPoolDO> list(Map<String, Object> map){
		return studentCardPoolStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCardPoolStudentDao.count(map);
	}
	
	@Override
	public int save(StudentCardPoolDO studentCardPoolStudent){
		return studentCardPoolStudentDao.save(studentCardPoolStudent);
	}
	
	@Override
	public int update(StudentCardPoolDO studentCardPoolStudent){
		return studentCardPoolStudentDao.update(studentCardPoolStudent);
	}
	
	@Override
	public int remove(String id){
		return studentCardPoolStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return studentCardPoolStudentDao.batchRemove(ids);
	}
	
}
