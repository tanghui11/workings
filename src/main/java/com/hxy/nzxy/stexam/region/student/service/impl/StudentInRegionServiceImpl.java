package com.hxy.nzxy.stexam.region.student.service.impl;

import com.hxy.nzxy.stexam.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentInRegionDao;
import com.hxy.nzxy.stexam.domain.StudentInDO;
import com.hxy.nzxy.stexam.region.student.service.StudentInRegionService;



@Service
public class StudentInRegionServiceImpl implements StudentInRegionService {
	@Autowired
	private StudentInRegionDao studentInRegionDao;
	
	@Override
	public StudentInDO get(Long id){
		return studentInRegionDao.get(id);
	}
	
	@Override
	public List<StudentInDO> list(Map<String, Object> map){
		return studentInRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentInRegionDao.count(map);
	}
	
	@Override
	public int save(StudentInDO studentInRegion){
		return studentInRegionDao.save(studentInRegion);
	}
	
	@Override
	public int update(StudentInDO studentInRegion){
		return studentInRegionDao.update(studentInRegion);
	}
	
	@Override
	public int remove(Long id){
		return studentInRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentInRegionDao.batchRemove(ids);
	}
	@Override
	public void updateMigration(StudentInDO studentInRegion) {
		studentInRegionDao.updateMigration(studentInRegion);
	}

	@Override
	public List<StudentInDO> migrationList(Map<String, Object> map) {
		return studentInRegionDao.migrationList(map);
	}

	@Override
	public int migrationCount(Map<String, Object> map) {
		return studentInRegionDao.migrationCount(map);
	}

}
