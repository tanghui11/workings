package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentHisRegionDao;
import com.hxy.nzxy.stexam.domain.StudentHisDO;
import com.hxy.nzxy.stexam.region.student.service.StudentHisRegionService;



@Service
public class StudentHisRegionServiceImpl implements StudentHisRegionService {
	@Autowired
	private StudentHisRegionDao studentHisRegionDao;
	
	@Override
	public StudentHisDO get(String id){
		return studentHisRegionDao.get(id);
	}
	
	@Override
	public List<StudentHisDO> list(Map<String, Object> map){
		return studentHisRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentHisRegionDao.count(map);
	}
	
	@Override
	public int save(StudentHisDO studentHisRegion){
		return studentHisRegionDao.save(studentHisRegion);
	}
	
	@Override
	public int update(StudentHisDO studentHisRegion){
		return studentHisRegionDao.update(studentHisRegion);
	}
	
	@Override
	public int remove(String id){
		return studentHisRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return studentHisRegionDao.batchRemove(ids);
	}
	
}
