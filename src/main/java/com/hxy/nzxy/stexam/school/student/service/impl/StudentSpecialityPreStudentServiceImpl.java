package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.StudentSpecialityPreStudentDao;
import com.hxy.nzxy.stexam.domain.StudentSpecialityPreDO;
import com.hxy.nzxy.stexam.school.student.service.StudentSpecialityPreStudentService;



@Service
public class StudentSpecialityPreStudentServiceImpl implements StudentSpecialityPreStudentService {
	@Autowired
	private StudentSpecialityPreStudentDao studentSpecialityPreStudentDao;
	
	@Override
	public StudentSpecialityPreDO get(Long id){
		return studentSpecialityPreStudentDao.get(id);
	}
	
	@Override
	public List<StudentSpecialityPreDO> list(Map<String, Object> map){
		return studentSpecialityPreStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentSpecialityPreStudentDao.count(map);
	}
	
	@Override
	public int save(StudentSpecialityPreDO studentSpecialityPreStudent){
		return studentSpecialityPreStudentDao.save(studentSpecialityPreStudent);
	}
	
	@Override
	public int update(StudentSpecialityPreDO studentSpecialityPreStudent){
		return studentSpecialityPreStudentDao.update(studentSpecialityPreStudent);
	}
	
	@Override
	public int remove(Long id){
		return studentSpecialityPreStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentSpecialityPreStudentDao.batchRemove(ids);
	}
	
}
