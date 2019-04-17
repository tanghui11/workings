package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentGradeRegionDao;
import com.hxy.nzxy.stexam.domain.StudentGradeDO;
import com.hxy.nzxy.stexam.region.student.service.StudentGradeRegionService;



@Service
public class StudentGradeRegionServiceImpl implements StudentGradeRegionService {
	@Autowired
	private StudentGradeRegionDao studentGradeRegionDao;
	
	@Override
	public StudentGradeDO get(String id){
		return studentGradeRegionDao.get(id);
	}
	
	@Override
	public List<StudentGradeDO> list(Map<String, Object> map){
		return studentGradeRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentGradeRegionDao.count(map);
	}
	
	@Override
	public int save(StudentGradeDO studentGradeRegion){
		return studentGradeRegionDao.save(studentGradeRegion);
	}
	
	@Override
	public int update(StudentGradeDO studentGradeRegion){
		return studentGradeRegionDao.update(studentGradeRegion);
	}
	
	@Override
	public int remove(String id){
		return studentGradeRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return studentGradeRegionDao.batchRemove(ids);
	}
	
}
