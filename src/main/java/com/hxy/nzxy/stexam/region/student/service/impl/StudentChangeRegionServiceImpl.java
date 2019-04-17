package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentChangeRegionDao;
import com.hxy.nzxy.stexam.domain.StudentChangeDO;
import com.hxy.nzxy.stexam.region.student.service.StudentChangeRegionService;



@Service
public class StudentChangeRegionServiceImpl implements StudentChangeRegionService {
	@Autowired
	private StudentChangeRegionDao studentChangeRegionDao;
	
	@Override
	public StudentChangeDO get(Long id){
		return studentChangeRegionDao.get(id);
	}
	
	@Override
	public List<StudentChangeDO> list(Map<String, Object> map){
		return studentChangeRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentChangeRegionDao.count(map);
	}
	
	@Override
	public int save(StudentChangeDO studentChangeRegion){
		return studentChangeRegionDao.save(studentChangeRegion);
	}
	
	@Override
	public int update(StudentChangeDO studentChangeRegion){
		return studentChangeRegionDao.update(studentChangeRegion);
	}
	
	@Override
	public int remove(Long id){
		return studentChangeRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentChangeRegionDao.batchRemove(ids);
	}
	
}
