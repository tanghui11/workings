package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.StudentRegHisStudentDao;
import com.hxy.nzxy.stexam.domain.StudentRegHisDO;
import com.hxy.nzxy.stexam.school.student.service.StudentRegHisStudentService;



@Service
public class StudentRegHisStudentServiceImpl implements StudentRegHisStudentService {
	@Autowired
	private StudentRegHisStudentDao studentRegHisStudentDao;
	
	@Override
	public StudentRegHisDO get(Long id){
		return studentRegHisStudentDao.get(id);
	}
	
	@Override
	public List<StudentRegHisDO> list(Map<String, Object> map){
		return studentRegHisStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentRegHisStudentDao.count(map);
	}
	
	@Override
	public int save(StudentRegHisDO studentRegHisStudent){
		return studentRegHisStudentDao.save(studentRegHisStudent);
	}
	
	@Override
	public int update(StudentRegHisDO studentRegHisStudent){
		return studentRegHisStudentDao.update(studentRegHisStudent);
	}
	
	@Override
	public int remove(Long id){
		return studentRegHisStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentRegHisStudentDao.batchRemove(ids);
	}
	
}
