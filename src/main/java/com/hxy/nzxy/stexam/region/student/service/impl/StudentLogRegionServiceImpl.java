package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentLogRegionDao;
import com.hxy.nzxy.stexam.domain.StudentLogDO;
import com.hxy.nzxy.stexam.region.student.service.StudentLogRegionService;



@Service
public class StudentLogRegionServiceImpl implements StudentLogRegionService {
	@Autowired
	private StudentLogRegionDao studentLogRegionDao;
	
	@Override
	public StudentLogDO get(String changeType){
		return studentLogRegionDao.get(changeType);
	}
	
	@Override
	public List<StudentLogDO> list(Map<String, Object> map){
		return studentLogRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentLogRegionDao.count(map);
	}
	
	@Override
	public int save(StudentLogDO studentLogRegion){
		return studentLogRegionDao.save(studentLogRegion);
	}
	
	@Override
	public int update(StudentLogDO studentLogRegion){
		return studentLogRegionDao.update(studentLogRegion);
	}
	
	@Override
	public int remove(String changeType){
		return studentLogRegionDao.remove(changeType);
	}
	
	@Override
	public int batchRemove(String[] changeTypes){
		return studentLogRegionDao.batchRemove(changeTypes);
	}
	
}
