package com.hxy.nzxy.stexam.center.region.service.impl;

import com.hxy.nzxy.stexam.domain.StudentOutSpecialityDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.region.dao.StudentOutDao;
import com.hxy.nzxy.stexam.domain.StudentOutDO;
import com.hxy.nzxy.stexam.center.region.service.StudentOutService;



@Service
public class StudentOutServiceImpl implements StudentOutService {
	@Autowired
	private StudentOutDao studentOutDao;
	
	@Override
	public StudentOutDO get(Long id){
		return studentOutDao.get(id);
	}
	
	@Override
	public List<StudentOutDO> list(Map<String, Object> map){
		return studentOutDao.list(map);
	}

	@Override
	public List<StudentOutSpecialityDO> studentOutSpecialityList(Map<String, Object> map) {
		return studentOutDao.studentOutSpecialityList(map) ;
	}

	@Override
	public int count(Map<String, Object> map){
		return studentOutDao.count(map);
	}

	@Override
	public int studentOutSpecialityCount(Map<String, Object> map) {
		return studentOutDao.studentOutSpecialityCount(map);
	}

	@Override
	public int save(StudentOutDO studentOut){
		return studentOutDao.save(studentOut);
	}
	
	@Override
	public int update(StudentOutDO studentOut){
		return studentOutDao.update(studentOut);
	}
	
	@Override
	public int remove(Long id){
		return studentOutDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentOutDao.batchRemove(ids);
	}

	@Override
	public int out(Long id) {
		return studentOutDao.out(id);
	}

}
