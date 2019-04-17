package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.StudentSpecialityHisStudentDao;
import com.hxy.nzxy.stexam.domain.StudentSpecialityHisDO;
import com.hxy.nzxy.stexam.school.student.service.StudentSpecialityHisStudentService;



@Service
public class StudentSpecialityHisStudentServiceImpl implements StudentSpecialityHisStudentService {
	@Autowired
	private StudentSpecialityHisStudentDao studentSpecialityHisStudentDao;
	
	@Override
	public StudentSpecialityHisDO get(Long id){
		return studentSpecialityHisStudentDao.get(id);
	}
	
	@Override
	public List<StudentSpecialityHisDO> list(Map<String, Object> map){
		return studentSpecialityHisStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentSpecialityHisStudentDao.count(map);
	}
	
	@Override
	public int save(StudentSpecialityHisDO studentSpecialityHisStudent){
		return studentSpecialityHisStudentDao.save(studentSpecialityHisStudent);
	}
	
	@Override
	public int update(StudentSpecialityHisDO studentSpecialityHisStudent){
		return studentSpecialityHisStudentDao.update(studentSpecialityHisStudent);
	}
	
	@Override
	public int remove(Long id){
		return studentSpecialityHisStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentSpecialityHisStudentDao.batchRemove(ids);
	}
	
}
