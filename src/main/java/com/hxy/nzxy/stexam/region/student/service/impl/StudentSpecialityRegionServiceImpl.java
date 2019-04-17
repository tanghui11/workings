package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentSpecialityRegionDao;
import com.hxy.nzxy.stexam.domain.StudentSpecialityDO;
import com.hxy.nzxy.stexam.region.student.service.StudentSpecialityRegionService;



@Service
public class StudentSpecialityRegionServiceImpl implements StudentSpecialityRegionService {
	@Autowired
	private StudentSpecialityRegionDao studentSpecialityRegionDao;
	
	@Override
	public StudentSpecialityDO get(Long id){
		return studentSpecialityRegionDao.get(id);
	}
	
	@Override
	public List<StudentSpecialityDO> list(Map<String, Object> map){
		return studentSpecialityRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentSpecialityRegionDao.count(map);
	}
	
	@Override
	public int save(StudentSpecialityDO studentSpecialityRegion){
		return studentSpecialityRegionDao.save(studentSpecialityRegion);
	}
	
	@Override
	public int update(StudentSpecialityDO studentSpecialityRegion){
		return studentSpecialityRegionDao.update(studentSpecialityRegion);
	}
	
	@Override
	public int remove(Long id){
		return studentSpecialityRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentSpecialityRegionDao.batchRemove(ids);
	}
	
}
