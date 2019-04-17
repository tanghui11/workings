package com.hxy.nzxy.stexam.region.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.region.dao.StudentOutRegionDao;
import com.hxy.nzxy.stexam.domain.StudentOutDO;
import com.hxy.nzxy.stexam.region.region.service.StudentOutRegionService;



@Service
public class StudentOutRegionServiceImpl implements StudentOutRegionService {
	@Autowired
	private StudentOutRegionDao studentOutRegionDao;
	
	@Override
	public StudentOutDO get(Long id){
		return studentOutRegionDao.get(id);
	}
	
	@Override
	public List<StudentOutDO> list(Map<String, Object> map){
		return studentOutRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentOutRegionDao.count(map);
	}
	
	@Override
	public int save(StudentOutDO studentOutRegion){
		return studentOutRegionDao.save(studentOutRegion);
	}
	
	@Override
	public int update(StudentOutDO studentOutRegion){
		return studentOutRegionDao.update(studentOutRegion);
	}
	
	@Override
	public int remove(Long id){
		return studentOutRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentOutRegionDao.batchRemove(ids);
	}
	
}
