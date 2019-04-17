package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentCardPoolRegionDao;
import com.hxy.nzxy.stexam.domain.StudentCardPoolDO;
import com.hxy.nzxy.stexam.region.student.service.StudentCardPoolRegionService;



@Service
public class StudentCardPoolRegionServiceImpl implements StudentCardPoolRegionService {
	@Autowired
	private StudentCardPoolRegionDao studentCardPoolRegionDao;
	
	@Override
	public StudentCardPoolDO get(String id){
		return studentCardPoolRegionDao.get(id);
	}
	
	@Override
	public List<StudentCardPoolDO> list(Map<String, Object> map){
		return studentCardPoolRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCardPoolRegionDao.count(map);
	}
	
	@Override
	public int save(StudentCardPoolDO studentCardPoolRegion){
		return studentCardPoolRegionDao.save(studentCardPoolRegion);
	}
	
	@Override
	public int update(StudentCardPoolDO studentCardPoolRegion){
		return studentCardPoolRegionDao.update(studentCardPoolRegion);
	}
	
	@Override
	public int remove(String id){
		return studentCardPoolRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return studentCardPoolRegionDao.batchRemove(ids);
	}
	
}
