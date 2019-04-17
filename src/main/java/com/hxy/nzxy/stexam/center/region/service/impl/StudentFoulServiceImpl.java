package com.hxy.nzxy.stexam.center.region.service.impl;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentWGDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.region.dao.StudentFoulDao;
import com.hxy.nzxy.stexam.domain.StudentFoulDO;
import com.hxy.nzxy.stexam.center.region.service.StudentFoulService;



@Service
public class StudentFoulServiceImpl implements StudentFoulService {
	@Autowired
	private StudentFoulDao studentFoulDao;
	
	@Override
	public StudentFoulDO get(Long studentCourseid){
		return studentFoulDao.get(studentCourseid);
	}
	
	@Override
	public List<StudentFoulDO> list(Map<String, Object> map){
		return studentFoulDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentFoulDao.count(map);
	}
	
	@Override
	public int save(StudentFoulDO studentFoul){
		return studentFoulDao.save(studentFoul);
	}
	
	@Override
	public int update(StudentFoulDO studentFoul){
		return studentFoulDao.update(studentFoul);
	}
	
	@Override
	public int remove(Long studentCourseid){
		return studentFoulDao.remove(studentCourseid);
	}
	
	@Override
	public int batchRemove(Long[] studentCourseids){
		return studentFoulDao.batchRemove(studentCourseids);
	}

	@Override
	public List<StudentWGDO> listStudent(Query query) {
		return studentFoulDao.listStudent(query);
	}

	@Override
	public int countStudent(Query query) {
		return studentFoulDao.countStudent(query);
	}

}
