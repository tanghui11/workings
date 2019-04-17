package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.StudentRegHisDao;
import com.hxy.nzxy.stexam.domain.StudentRegHisDO;
import com.hxy.nzxy.stexam.center.student.service.StudentRegHisService;



@Service
public class StudentRegHisServiceImpl implements StudentRegHisService {
	@Autowired
	private StudentRegHisDao studentRegHisDao;
	
	@Override
	public StudentRegHisDO get(Long id){
		return studentRegHisDao.get(id);
	}
	
	@Override
	public List<StudentRegHisDO> list(Map<String, Object> map){
		return studentRegHisDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentRegHisDao.count(map);
	}
	
	@Override
	public int save(StudentRegHisDO studentRegHis){
		return studentRegHisDao.save(studentRegHis);
	}
	
	@Override
	public int update(StudentRegHisDO studentRegHis){
		return studentRegHisDao.update(studentRegHis);
	}
	
	@Override
	public int remove(Long id){
		return studentRegHisDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentRegHisDao.batchRemove(ids);
	}
	
}
