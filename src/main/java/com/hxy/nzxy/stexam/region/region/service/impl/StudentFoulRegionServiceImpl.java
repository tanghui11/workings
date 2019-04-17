package com.hxy.nzxy.stexam.region.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.region.dao.StudentFoulRegionDao;
import com.hxy.nzxy.stexam.domain.StudentFoulDO;
import com.hxy.nzxy.stexam.region.region.service.StudentFoulRegionService;



@Service
public class StudentFoulRegionServiceImpl implements StudentFoulRegionService {
	@Autowired
	private StudentFoulRegionDao studentFoulRegionDao;
	
	@Override
	public StudentFoulDO get(Long studentCourseid){
		return studentFoulRegionDao.get(studentCourseid);
	}
	
	@Override
	public List<StudentFoulDO> list(Map<String, Object> map){
		return studentFoulRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentFoulRegionDao.count(map);
	}
	
	@Override
	public int save(StudentFoulDO studentFoulRegion){
		return studentFoulRegionDao.save(studentFoulRegion);
	}
	
	@Override
	public int update(StudentFoulDO studentFoulRegion){
		return studentFoulRegionDao.update(studentFoulRegion);
	}
	
	@Override
	public int remove(Long studentCourseid){
		return studentFoulRegionDao.remove(studentCourseid);
	}
	
	@Override
	public int batchRemove(Long[] studentCourseids){
		return studentFoulRegionDao.batchRemove(studentCourseids);
	}
	
}
