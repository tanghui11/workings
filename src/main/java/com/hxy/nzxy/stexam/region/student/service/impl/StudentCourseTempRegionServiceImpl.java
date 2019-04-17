package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentCourseTempRegionDao;
import com.hxy.nzxy.stexam.domain.StudentCourseTempDO;
import com.hxy.nzxy.stexam.region.student.service.StudentCourseTempRegionService;



@Service
public class StudentCourseTempRegionServiceImpl implements StudentCourseTempRegionService {
	@Autowired
	private StudentCourseTempRegionDao studentCourseTempRegionDao;
	
	@Override
	public StudentCourseTempDO get(Long id){
		return studentCourseTempRegionDao.get(id);
	}
	
	@Override
	public List<StudentCourseTempDO> list(Map<String, Object> map){
		return studentCourseTempRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCourseTempRegionDao.count(map);
	}
	
	@Override
	public int save(StudentCourseTempDO studentCourseTempRegion){
		return studentCourseTempRegionDao.save(studentCourseTempRegion);
	}
	
	@Override
	public int update(StudentCourseTempDO studentCourseTempRegion){
		return studentCourseTempRegionDao.update(studentCourseTempRegion);
	}
	
	@Override
	public int remove(Long id){
		return studentCourseTempRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentCourseTempRegionDao.batchRemove(ids);
	}
	
}
