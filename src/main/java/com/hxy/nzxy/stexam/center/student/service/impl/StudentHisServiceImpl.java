package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.StudentHisDao;
import com.hxy.nzxy.stexam.domain.StudentHisDO;
import com.hxy.nzxy.stexam.center.student.service.StudentHisService;



@Service
public class StudentHisServiceImpl implements StudentHisService {
	@Autowired
	private StudentHisDao studentHisDao;
	
	@Override
	public StudentHisDO get(String id){
		return studentHisDao.get(id);
	}
	
	@Override
	public List<StudentHisDO> list(Map<String, Object> map){
		return studentHisDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentHisDao.count(map);
	}
	
	@Override
	public int save(StudentHisDO studentHis){
		return studentHisDao.save(studentHis);
	}
	
	@Override
	public int update(StudentHisDO studentHis){
		return studentHisDao.update(studentHis);
	}
	
	@Override
	public int remove(String id){
		return studentHisDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return studentHisDao.batchRemove(ids);
	}
	
}
