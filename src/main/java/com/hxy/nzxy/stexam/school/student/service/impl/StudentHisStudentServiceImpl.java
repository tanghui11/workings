package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.StudentHisStudentDao;
import com.hxy.nzxy.stexam.domain.StudentHisDO;
import com.hxy.nzxy.stexam.school.student.service.StudentHisStudentService;



@Service
public class StudentHisStudentServiceImpl implements StudentHisStudentService {
	@Autowired
	private StudentHisStudentDao studentHisStudentDao;
	
	@Override
	public StudentHisDO get(String id){
		return studentHisStudentDao.get(id);
	}
	
	@Override
	public List<StudentHisDO> list(Map<String, Object> map){
		return studentHisStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentHisStudentDao.count(map);
	}
	
	@Override
	public int save(StudentHisDO studentHisStudent){
		return studentHisStudentDao.save(studentHisStudent);
	}
	
	@Override
	public int update(StudentHisDO studentHisStudent){
		return studentHisStudentDao.update(studentHisStudent);
	}
	
	@Override
	public int remove(String id){
		return studentHisStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return studentHisStudentDao.batchRemove(ids);
	}
	
}
