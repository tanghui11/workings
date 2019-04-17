package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentTempRegionDao;
import com.hxy.nzxy.stexam.domain.StudentTempDO;
import com.hxy.nzxy.stexam.region.student.service.StudentTempRegionService;



@Service
public class StudentTempRegionServiceImpl implements StudentTempRegionService {
	@Autowired
	private StudentTempRegionDao studentTempRegionDao;
	
	@Override
	public StudentTempDO get(String type){
		return studentTempRegionDao.get(type);
	}
	
	@Override
	public List<StudentTempDO> list(Map<String, Object> map){
		return studentTempRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentTempRegionDao.count(map);
	}
	
	@Override
	public int save(StudentTempDO studentTempRegion){
		return studentTempRegionDao.save(studentTempRegion);
	}
	
	@Override
	public int update(StudentTempDO studentTempRegion){
		return studentTempRegionDao.update(studentTempRegion);
	}
	
	@Override
	public int remove(String type){
		return studentTempRegionDao.remove(type);
	}
	
	@Override
	public int batchRemove(String[] types){
		return studentTempRegionDao.batchRemove(types);
	}
	
}
