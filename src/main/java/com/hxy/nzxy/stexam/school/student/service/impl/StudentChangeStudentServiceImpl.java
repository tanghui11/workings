package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.StudentChangeStudentDao;
import com.hxy.nzxy.stexam.domain.StudentChangeDO;
import com.hxy.nzxy.stexam.school.student.service.StudentChangeStudentService;



@Service
public class StudentChangeStudentServiceImpl implements StudentChangeStudentService {
	@Autowired
	private StudentChangeStudentDao studentChangeStudentDao;
	
	@Override
	public StudentChangeDO get(Long id){
		return studentChangeStudentDao.get(id);
	}
	
	@Override
	public List<StudentChangeDO> list(Map<String, Object> map){
		return studentChangeStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentChangeStudentDao.count(map);
	}
	
	@Override
	public int save(StudentChangeDO studentChangeStudent){
		return studentChangeStudentDao.save(studentChangeStudent);
	}
	
	@Override
	public int update(StudentChangeDO studentChangeStudent){
		return studentChangeStudentDao.update(studentChangeStudent);
	}
	
	@Override
	public int remove(Long id){
		return studentChangeStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentChangeStudentDao.batchRemove(ids);
	}
	
}
