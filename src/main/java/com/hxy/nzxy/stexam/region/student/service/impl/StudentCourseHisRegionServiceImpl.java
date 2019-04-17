package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentCourseHisRegionDao;
import com.hxy.nzxy.stexam.domain.StudentCourseHisDO;
import com.hxy.nzxy.stexam.region.student.service.StudentCourseHisRegionService;



@Service
public class StudentCourseHisRegionServiceImpl implements StudentCourseHisRegionService {
	@Autowired
	private StudentCourseHisRegionDao studentCourseHisRegionDao;
	
	@Override
	public StudentCourseHisDO get(Long id){
		return studentCourseHisRegionDao.get(id);
	}
	
	@Override
	public List<StudentCourseHisDO> list(Map<String, Object> map){
		return studentCourseHisRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCourseHisRegionDao.count(map);
	}
	
	@Override
	public int save(StudentCourseHisDO studentCourseHisRegion){
		return studentCourseHisRegionDao.save(studentCourseHisRegion);
	}
	
	@Override
	public int update(StudentCourseHisDO studentCourseHisRegion){
		return studentCourseHisRegionDao.update(studentCourseHisRegion);
	}
	
	@Override
	public int remove(Long id){
		return studentCourseHisRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentCourseHisRegionDao.batchRemove(ids);
	}
	
}
