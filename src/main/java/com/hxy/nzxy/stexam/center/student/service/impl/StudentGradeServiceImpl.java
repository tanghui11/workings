package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.StudentGradeDao;
import com.hxy.nzxy.stexam.domain.StudentGradeDO;
import com.hxy.nzxy.stexam.center.student.service.StudentGradeService;



@Service
public class StudentGradeServiceImpl implements StudentGradeService {
	@Autowired
	private StudentGradeDao studentGradeDao;
	
	@Override
	public StudentGradeDO get(String id){
		return studentGradeDao.get(id);
	}
	
	@Override
	public List<StudentGradeDO> list(Map<String, Object> map){
		return studentGradeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentGradeDao.count(map);
	}
	
	@Override
	public int save(StudentGradeDO studentGrade){
		return studentGradeDao.save(studentGrade);
	}
	
	@Override
	public int update(StudentGradeDO studentGrade){
		return studentGradeDao.update(studentGrade);
	}
	
	@Override
	public int remove(String id){
		return studentGradeDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return studentGradeDao.batchRemove(ids);
	}
	
}
