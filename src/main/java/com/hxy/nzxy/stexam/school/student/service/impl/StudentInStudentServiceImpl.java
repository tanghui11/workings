package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.StudentInStudentDao;
import com.hxy.nzxy.stexam.domain.StudentInDO;
import com.hxy.nzxy.stexam.school.student.service.StudentInStudentService;



@Service
public class StudentInStudentServiceImpl implements StudentInStudentService {
	@Autowired
	private StudentInStudentDao studentInStudentDao;
	
	@Override
	public StudentInDO get(Long id){
		return studentInStudentDao.get(id);
	}
	
	@Override
	public List<StudentInDO> list(Map<String, Object> map){
		return studentInStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentInStudentDao.count(map);
	}
	
	@Override
	public int save(StudentInDO studentInStudent){
		return studentInStudentDao.save(studentInStudent);
	}
	
	@Override
	public int update(StudentInDO studentInStudent){
		return studentInStudentDao.update(studentInStudent);
	}
	
	@Override
	public int remove(Long id){
		return studentInStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentInStudentDao.batchRemove(ids);
	}
	
}
