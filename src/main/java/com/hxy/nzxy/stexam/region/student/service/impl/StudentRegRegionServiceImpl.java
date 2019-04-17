package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentRegRegionDao;
import com.hxy.nzxy.stexam.domain.StudentRegDO;
import com.hxy.nzxy.stexam.region.student.service.StudentRegRegionService;



@Service
public class StudentRegRegionServiceImpl implements StudentRegRegionService {
	@Autowired
	private StudentRegRegionDao studentRegRegionDao;
	
	@Override
	public StudentRegDO get(Long id){
		return studentRegRegionDao.get(id);
	}
	
	@Override
	public List<StudentRegDO> list(Map<String, Object> map){
		return studentRegRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentRegRegionDao.count(map);
	}
	
	@Override
	public int save(StudentRegDO studentRegRegion){
		return studentRegRegionDao.save(studentRegRegion);
	}
	
	@Override
	public int update(StudentRegDO studentRegRegion){
		return studentRegRegionDao.update(studentRegRegion);
	}
	
	@Override
	public int remove(Long id){
		return studentRegRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentRegRegionDao.batchRemove(ids);
	}
	
}
