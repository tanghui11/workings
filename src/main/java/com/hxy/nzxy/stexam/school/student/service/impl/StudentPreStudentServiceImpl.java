package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.StudentPreStudentDao;
import com.hxy.nzxy.stexam.domain.StudentPreDO;
import com.hxy.nzxy.stexam.school.student.service.StudentPreStudentService;



@Service
public class StudentPreStudentServiceImpl implements StudentPreStudentService {
	@Autowired
	private StudentPreStudentDao studentPreStudentDao;
	
	@Override
	public StudentPreDO get(Long id){
		return studentPreStudentDao.get(id);
	}
	
	@Override
	public List<StudentPreDO> list(Map<String, Object> map){
		return studentPreStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentPreStudentDao.count(map);
	}
	
	@Override
	public int save(StudentPreDO studentPreStudent){
		return studentPreStudentDao.save(studentPreStudent);
	}
	
	@Override
	public int update(StudentPreDO studentPreStudent){
		return studentPreStudentDao.update(studentPreStudent);
	}
	
	@Override
	public int remove(Long id){
		return studentPreStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentPreStudentDao.batchRemove(ids);
	}
	
}
