package com.hxy.nzxy.stexam.center.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.school.dao.SchoolScoreRatioDao;
import com.hxy.nzxy.stexam.domain.SchoolScoreRatioDO;
import com.hxy.nzxy.stexam.center.school.service.SchoolScoreRatioService;



@Service
public class SchoolScoreRatioServiceImpl implements SchoolScoreRatioService {
	@Autowired
	private SchoolScoreRatioDao schoolScoreRatioDao;
	
	@Override
	public SchoolScoreRatioDO get(Long id){
		return schoolScoreRatioDao.get(id);
	}
	
	@Override
	public List<SchoolScoreRatioDO> list(Map<String, Object> map){
		return schoolScoreRatioDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return schoolScoreRatioDao.count(map);
	}
	
	@Override
	public int save(SchoolScoreRatioDO schoolScoreRatio){
		return schoolScoreRatioDao.save(schoolScoreRatio);
	}
	
	@Override
	public int update(SchoolScoreRatioDO schoolScoreRatio){
		return schoolScoreRatioDao.update(schoolScoreRatio);
	}
	
	@Override
	public int remove(Long id){
		return schoolScoreRatioDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return schoolScoreRatioDao.batchRemove(ids);
	}
	
}
