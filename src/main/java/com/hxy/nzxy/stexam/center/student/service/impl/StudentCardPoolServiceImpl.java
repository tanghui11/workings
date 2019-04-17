package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.StudentCardPoolDao;
import com.hxy.nzxy.stexam.domain.StudentCardPoolDO;
import com.hxy.nzxy.stexam.center.student.service.StudentCardPoolService;



@Service
public class StudentCardPoolServiceImpl implements StudentCardPoolService {
	@Autowired
	private StudentCardPoolDao studentCardPoolDao;
	
	@Override
	public StudentCardPoolDO get(String id){
		return studentCardPoolDao.get(id);
	}
	
	@Override
	public List<StudentCardPoolDO> list(Map<String, Object> map){
		return studentCardPoolDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCardPoolDao.count(map);
	}
	
	@Override
	public int save(StudentCardPoolDO studentCardPool){
		return studentCardPoolDao.save(studentCardPool);
	}
	
	@Override
	public int update(StudentCardPoolDO studentCardPool){
		return studentCardPoolDao.update(studentCardPool);
	}
	
	@Override
	public int remove(String id){
		return studentCardPoolDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return studentCardPoolDao.batchRemove(ids);
	}
	
}
