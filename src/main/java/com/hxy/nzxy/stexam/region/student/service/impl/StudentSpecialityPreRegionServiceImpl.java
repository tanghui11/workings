package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentSpecialityPreRegionDao;
import com.hxy.nzxy.stexam.domain.StudentSpecialityPreDO;
import com.hxy.nzxy.stexam.region.student.service.StudentSpecialityPreRegionService;



@Service
public class StudentSpecialityPreRegionServiceImpl implements StudentSpecialityPreRegionService {
	@Autowired
	private StudentSpecialityPreRegionDao studentSpecialityPreRegionDao;
	
	@Override
	public StudentSpecialityPreDO get(Long id){
		return studentSpecialityPreRegionDao.get(id);
	}
	
	@Override
	public List<StudentSpecialityPreDO> list(Map<String, Object> map){
		return studentSpecialityPreRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentSpecialityPreRegionDao.count(map);
	}
	
	@Override
	public int save(StudentSpecialityPreDO studentSpecialityPreRegion){
		return studentSpecialityPreRegionDao.save(studentSpecialityPreRegion);
	}
	
	@Override
	public int update(StudentSpecialityPreDO studentSpecialityPreRegion){
		return studentSpecialityPreRegionDao.update(studentSpecialityPreRegion);
	}
	
	@Override
	public int remove(Long id){
		return studentSpecialityPreRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentSpecialityPreRegionDao.batchRemove(ids);
	}
	
}
