package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.StudentScoreInStudentDao;
import com.hxy.nzxy.stexam.domain.StudentScoreInDO;
import com.hxy.nzxy.stexam.school.student.service.StudentScoreInStudentService;



@Service
public class StudentScoreInStudentServiceImpl implements StudentScoreInStudentService {
	@Autowired
	private StudentScoreInStudentDao studentScoreInStudentDao;
	
	@Override
	public StudentScoreInDO get(Long id){
		return studentScoreInStudentDao.get(id);
	}
	
	@Override
	public List<StudentScoreInDO> list(Map<String, Object> map){
		return studentScoreInStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentScoreInStudentDao.count(map);
	}
	
	@Override
	public int save(StudentScoreInDO studentScoreInStudent){
		return studentScoreInStudentDao.save(studentScoreInStudent);
	}
	
	@Override
	public int update(StudentScoreInDO studentScoreInStudent){
		return studentScoreInStudentDao.update(studentScoreInStudent);
	}
	
	@Override
	public int remove(Long id){
		return studentScoreInStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentScoreInStudentDao.batchRemove(ids);
	}
	
}
