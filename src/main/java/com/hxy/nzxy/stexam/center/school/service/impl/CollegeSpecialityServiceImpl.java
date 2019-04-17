package com.hxy.nzxy.stexam.center.school.service.impl;

import com.hxy.nzxy.stexam.domain.CollegeSpecialityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.school.dao.CollegeSpecialityDao;
import com.hxy.nzxy.stexam.domain.CollegeSpecialityDO;
import com.hxy.nzxy.stexam.center.school.service.CollegeSpecialityService;



@Service
public class CollegeSpecialityServiceImpl implements CollegeSpecialityService {
	@Autowired
	private CollegeSpecialityDao collegeSpecialityDao;
	
	@Override
	public CollegeSpecialityDO get(Long id){
		return collegeSpecialityDao.get(id);
	}
	
	@Override
	public List<CollegeSpecialityDO> list(Map<String, Object> map){
		return collegeSpecialityDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return collegeSpecialityDao.count(map);
	}
	
	@Override
	public int save(CollegeSpecialityDO collegeSpeciality){
		return collegeSpecialityDao.save(collegeSpeciality);
	}
	
	@Override
	public int update(CollegeSpecialityDO collegeSpeciality){
		return collegeSpecialityDao.update(collegeSpeciality);
	}
	
	@Override
	public int remove(Long id){
		return collegeSpecialityDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return collegeSpecialityDao.batchRemove(ids);
	}

	@Override
	public List<CollegeSpecialityVO> listCollegeSpeciality(Map<String, Object> params) {
		return collegeSpecialityDao.listCollegeSpeciality(params);
	}

}
