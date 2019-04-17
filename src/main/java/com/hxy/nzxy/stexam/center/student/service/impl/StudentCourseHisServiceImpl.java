package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.StudentCourseHisDao;
import com.hxy.nzxy.stexam.domain.StudentCourseHisDO;
import com.hxy.nzxy.stexam.center.student.service.StudentCourseHisService;



@Service
public class StudentCourseHisServiceImpl implements StudentCourseHisService {
	@Autowired
	private StudentCourseHisDao studentCourseHisDao;
	
	@Override
	public StudentCourseHisDO get(Long id){
		return studentCourseHisDao.get(id);
	}
	
	@Override
	public List<StudentCourseHisDO> list(Map<String, Object> map){
		return studentCourseHisDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCourseHisDao.count(map);
	}
	
	@Override
	public int save(StudentCourseHisDO studentCourseHis){
		return studentCourseHisDao.save(studentCourseHis);
	}
	
	@Override
	public int update(StudentCourseHisDO studentCourseHis){
		return studentCourseHisDao.update(studentCourseHis);
	}
	
	@Override
	public int remove(Long id){
		return studentCourseHisDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentCourseHisDao.batchRemove(ids);
	}
	
}
