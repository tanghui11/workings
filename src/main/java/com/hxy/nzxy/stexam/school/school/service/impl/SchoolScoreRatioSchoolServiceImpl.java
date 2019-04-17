package com.hxy.nzxy.stexam.school.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.school.dao.SchoolScoreRatioSchoolDao;
import com.hxy.nzxy.stexam.domain.SchoolScoreRatioDO;
import com.hxy.nzxy.stexam.school.school.service.SchoolScoreRatioSchoolService;



@Service
public class SchoolScoreRatioSchoolServiceImpl implements SchoolScoreRatioSchoolService {
	@Autowired
	private SchoolScoreRatioSchoolDao schoolScoreRatioSchoolDao;
	
	@Override
	public SchoolScoreRatioDO get(Long id){
		return schoolScoreRatioSchoolDao.get(id);
	}
	
	@Override
	public List<SchoolScoreRatioDO> list(Map<String, Object> map){
		return schoolScoreRatioSchoolDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return schoolScoreRatioSchoolDao.count(map);
	}
	
	@Override
	public int save(SchoolScoreRatioDO schoolScoreRatioSchool){
		return schoolScoreRatioSchoolDao.save(schoolScoreRatioSchool);
	}
	
	@Override
	public int update(SchoolScoreRatioDO schoolScoreRatioSchool){
		return schoolScoreRatioSchoolDao.update(schoolScoreRatioSchool);
	}
	
	@Override
	public int remove(Long id){
		return schoolScoreRatioSchoolDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return schoolScoreRatioSchoolDao.batchRemove(ids);
	}
	
}
