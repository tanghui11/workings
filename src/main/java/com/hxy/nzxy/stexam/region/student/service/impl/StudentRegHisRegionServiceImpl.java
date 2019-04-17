package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentRegHisRegionDao;
import com.hxy.nzxy.stexam.domain.StudentRegHisDO;
import com.hxy.nzxy.stexam.region.student.service.StudentRegHisRegionService;



@Service
public class StudentRegHisRegionServiceImpl implements StudentRegHisRegionService {
	@Autowired
	private StudentRegHisRegionDao studentRegHisRegionDao;
	
	@Override
	public StudentRegHisDO get(Long id){
		return studentRegHisRegionDao.get(id);
	}
	
	@Override
	public List<StudentRegHisDO> list(Map<String, Object> map){
		return studentRegHisRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentRegHisRegionDao.count(map);
	}
	
	@Override
	public int save(StudentRegHisDO studentRegHisRegion){
		return studentRegHisRegionDao.save(studentRegHisRegion);
	}
	
	@Override
	public int update(StudentRegHisDO studentRegHisRegion){
		return studentRegHisRegionDao.update(studentRegHisRegion);
	}
	
	@Override
	public int remove(Long id){
		return studentRegHisRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentRegHisRegionDao.batchRemove(ids);
	}
	
}
