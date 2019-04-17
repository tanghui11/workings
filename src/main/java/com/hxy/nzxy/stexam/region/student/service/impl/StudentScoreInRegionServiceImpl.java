package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentScoreInRegionDao;
import com.hxy.nzxy.stexam.domain.StudentScoreInDO;
import com.hxy.nzxy.stexam.region.student.service.StudentScoreInRegionService;



@Service
public class StudentScoreInRegionServiceImpl implements StudentScoreInRegionService {
	@Autowired
	private StudentScoreInRegionDao studentScoreInRegionDao;
	
	@Override
	public StudentScoreInDO get(Long id){
		return studentScoreInRegionDao.get(id);
	}
	
	@Override
	public List<StudentScoreInDO> list(Map<String, Object> map){
		return studentScoreInRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentScoreInRegionDao.count(map);
	}
	
	@Override
	public int save(StudentScoreInDO studentScoreInRegion){
		return studentScoreInRegionDao.save(studentScoreInRegion);
	}
	
	@Override
	public int update(StudentScoreInDO studentScoreInRegion){
		return studentScoreInRegionDao.update(studentScoreInRegion);
	}
	
	@Override
	public int remove(Long id){
		return studentScoreInRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentScoreInRegionDao.batchRemove(ids);
	}

	@Override
	public List<StudentScoreInDO> migrationList(Map<String, Object> map) {
		return studentScoreInRegionDao.migrationList(map);
	}

	@Override
	public int migrationCount(Map<String, Object> map) {
		return studentScoreInRegionDao.migrationCount(map);
	}

}
