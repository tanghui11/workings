package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.StudentLogStudentDao;
import com.hxy.nzxy.stexam.domain.StudentLogDO;
import com.hxy.nzxy.stexam.school.student.service.StudentLogStudentService;



@Service
public class StudentLogStudentServiceImpl implements StudentLogStudentService {
	@Autowired
	private StudentLogStudentDao studentLogStudentDao;
	
	@Override
	public StudentLogDO get(String changeType){
		return studentLogStudentDao.get(changeType);
	}
	
	@Override
	public List<StudentLogDO> list(Map<String, Object> map){
		return studentLogStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentLogStudentDao.count(map);
	}
	
	@Override
	public int save(StudentLogDO studentLogStudent){
		return studentLogStudentDao.save(studentLogStudent);
	}
	
	@Override
	public int update(StudentLogDO studentLogStudent){
		return studentLogStudentDao.update(studentLogStudent);
	}
	
	@Override
	public int remove(String changeType){
		return studentLogStudentDao.remove(changeType);
	}
	
	@Override
	public int batchRemove(String[] changeTypes){
		return studentLogStudentDao.batchRemove(changeTypes);
	}
	
}
