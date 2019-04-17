package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.StudentPreDao;
import com.hxy.nzxy.stexam.domain.StudentPreDO;
import com.hxy.nzxy.stexam.center.student.service.StudentPreService;



@Service
public class StudentPreServiceImpl implements StudentPreService {
	@Autowired
	private StudentPreDao studentPreDao;
	
	@Override
	public StudentPreDO get(Long id){
		return studentPreDao.get(id);
	}
	
	@Override
	public List<StudentPreDO> list(Map<String, Object> map){
		return studentPreDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentPreDao.count(map);
	}
	
	@Override
	public int save(StudentPreDO studentPre){
		return studentPreDao.save(studentPre);
	}
	
	@Override
	public int update(StudentPreDO studentPre){
		return studentPreDao.update(studentPre);
	}
	
	@Override
	public int remove(Long id){
		return studentPreDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentPreDao.batchRemove(ids);
	}
	
}
