package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentPreRegionDao;
import com.hxy.nzxy.stexam.domain.StudentPreDO;
import com.hxy.nzxy.stexam.region.student.service.StudentPreRegionService;



@Service
public class StudentPreRegionServiceImpl implements StudentPreRegionService {
	@Autowired
	private StudentPreRegionDao studentPreRegionDao;
	
	@Override
	public StudentPreDO get(Long id){
		return studentPreRegionDao.get(id);
	}
	
	@Override
	public List<StudentPreDO> list(Map<String, Object> map){
		return studentPreRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentPreRegionDao.count(map);
	}
	
	@Override
	public int save(StudentPreDO studentPreRegion){
		return studentPreRegionDao.save(studentPreRegion);
	}
	
	@Override
	public int update(StudentPreDO studentPreRegion){
		return studentPreRegionDao.update(studentPreRegion);
	}
	
	@Override
	public int remove(Long id){
		return studentPreRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentPreRegionDao.batchRemove(ids);
	}
	
}
