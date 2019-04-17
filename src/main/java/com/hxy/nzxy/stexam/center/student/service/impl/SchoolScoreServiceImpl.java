package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.SchoolScoreDao;
import com.hxy.nzxy.stexam.domain.SchoolScoreDO;
import com.hxy.nzxy.stexam.center.student.service.SchoolScoreService;



@Service
public class SchoolScoreServiceImpl implements SchoolScoreService {
	@Autowired
	private SchoolScoreDao schoolScoreDao;
	
	@Override
	public SchoolScoreDO get(Long id){
		return schoolScoreDao.get(id);
	}
	
	@Override
	public List<SchoolScoreDO> list(Map<String, Object> map){
		return schoolScoreDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return schoolScoreDao.count(map);
	}
	
	@Override
	public int save(SchoolScoreDO schoolScore){
		return schoolScoreDao.save(schoolScore);
	}
	
	@Override
	public int update(SchoolScoreDO schoolScore){
		return schoolScoreDao.update(schoolScore);
	}
	
	@Override
	public int remove(Long id){
		return schoolScoreDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return schoolScoreDao.batchRemove(ids);
	}
	
}
