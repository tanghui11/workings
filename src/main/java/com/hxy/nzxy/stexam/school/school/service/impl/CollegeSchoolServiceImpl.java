package com.hxy.nzxy.stexam.school.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.school.dao.CollegeSchoolDao;
import com.hxy.nzxy.stexam.domain.CollegeDO;
import com.hxy.nzxy.stexam.school.school.service.CollegeSchoolService;



@Service
public class CollegeSchoolServiceImpl implements CollegeSchoolService {
	@Autowired
	private CollegeSchoolDao collegeSchoolDao;
	
	@Override
	public CollegeDO get(String id){
		return collegeSchoolDao.get(id);
	}
	
	@Override
	public List<CollegeDO> list(Map<String, Object> map){

		return collegeSchoolDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return collegeSchoolDao.count(map);
	}
	
	@Override
	public int save(CollegeDO collegeSchool){
		return collegeSchoolDao.save(collegeSchool);
	}
	
	@Override
	public int update(CollegeDO collegeSchool){
		return collegeSchoolDao.update(collegeSchool);
	}
	
	@Override
	public int remove(String id){
		return collegeSchoolDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return collegeSchoolDao.batchRemove(ids);
	}

	@Override
	public List<CollegeDO> listCollege(Map<String, Object> query) {
		return collegeSchoolDao.listCollege(query);
	}

}
