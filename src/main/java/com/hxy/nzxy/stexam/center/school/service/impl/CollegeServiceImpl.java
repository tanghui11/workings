package com.hxy.nzxy.stexam.center.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.school.dao.CollegeDao;
import com.hxy.nzxy.stexam.domain.CollegeDO;
import com.hxy.nzxy.stexam.center.school.service.CollegeService;



@Service
public class CollegeServiceImpl implements CollegeService {
	@Autowired
	private CollegeDao collegeDao;
	
	@Override
	public CollegeDO get(String id){
		return collegeDao.get(id);
	}
	
	@Override
	public List<CollegeDO> list(Map<String, Object> map){
		return collegeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return collegeDao.count(map);
	}
	
	@Override
	public int save(CollegeDO college){
		return collegeDao.save(college);
	}
	
	@Override
	public int update(CollegeDO college){
		return collegeDao.update(college);
	}
	
	@Override
	public int remove(String id){
		return collegeDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return collegeDao.batchRemove(ids);
	}

	@Override
	public List<CollegeDO> seachCollege(Map<String, Object> params) {
		return collegeDao.seachCollege(params);
	}

}
