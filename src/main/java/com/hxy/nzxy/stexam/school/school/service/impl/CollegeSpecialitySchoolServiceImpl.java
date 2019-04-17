package com.hxy.nzxy.stexam.school.school.service.impl;

import com.hxy.nzxy.stexam.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.school.dao.CollegeSpecialitySchoolDao;
import com.hxy.nzxy.stexam.domain.CollegeSpecialityDO;
import com.hxy.nzxy.stexam.school.school.service.CollegeSpecialitySchoolService;



@Service
public class CollegeSpecialitySchoolServiceImpl implements CollegeSpecialitySchoolService {
	@Autowired
	private CollegeSpecialitySchoolDao collegeSpecialitySchoolDao;
	
	@Override
	public CollegeSpecialityDO get(Long id){
		return collegeSpecialitySchoolDao.get(id);
	}
	
	@Override
	public List<CollegeSpecialityDO> list(Map<String, Object> map){
		return collegeSpecialitySchoolDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return collegeSpecialitySchoolDao.count(map);
	}
	
	@Override
	public int save(CollegeSpecialityDO collegeSpecialitySchool){
		return collegeSpecialitySchoolDao.save(collegeSpecialitySchool);
	}
	
	@Override
	public int update(CollegeSpecialityDO collegeSpecialitySchool){
		return collegeSpecialitySchoolDao.update(collegeSpecialitySchool);
	}
	
	@Override
	public int remove(Long id){
		return collegeSpecialitySchoolDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return collegeSpecialitySchoolDao.batchRemove(ids);
	}

	@Override
	public List<CollegeSpecialityDO> ylist(Query query) {
		return collegeSpecialitySchoolDao.ylist(query);
	}

	@Override
	public int ycount(Query query) {
		return collegeSpecialitySchoolDao.ycount(query);
	}

	@Override
	public int dcount(Query query) {
		return collegeSpecialitySchoolDao.dcount(query);
	}

	@Override
	public List<CollegeSpecialityDO> dist(Query query) {
		return collegeSpecialitySchoolDao.dlist(query);
	}

}
