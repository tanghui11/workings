package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentSpecialityHisRegionDao;
import com.hxy.nzxy.stexam.domain.StudentSpecialityHisDO;
import com.hxy.nzxy.stexam.region.student.service.StudentSpecialityHisRegionService;



@Service
public class StudentSpecialityHisRegionServiceImpl implements StudentSpecialityHisRegionService {
	@Autowired
	private StudentSpecialityHisRegionDao studentSpecialityHisRegionDao;
	
	@Override
	public StudentSpecialityHisDO get(Long id){
		return studentSpecialityHisRegionDao.get(id);
	}
	
	@Override
	public List<StudentSpecialityHisDO> list(Map<String, Object> map){
		return studentSpecialityHisRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentSpecialityHisRegionDao.count(map);
	}
	
	@Override
	public int save(StudentSpecialityHisDO studentSpecialityHisRegion){
		return studentSpecialityHisRegionDao.save(studentSpecialityHisRegion);
	}
	
	@Override
	public int update(StudentSpecialityHisDO studentSpecialityHisRegion){
		return studentSpecialityHisRegionDao.update(studentSpecialityHisRegion);
	}
	
	@Override
	public int remove(Long id){
		return studentSpecialityHisRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentSpecialityHisRegionDao.batchRemove(ids);
	}
	
}
