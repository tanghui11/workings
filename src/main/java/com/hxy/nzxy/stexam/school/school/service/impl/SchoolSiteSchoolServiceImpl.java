package com.hxy.nzxy.stexam.school.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.school.dao.SchoolSiteSchoolDao;
import com.hxy.nzxy.stexam.domain.SchoolSiteDO;
import com.hxy.nzxy.stexam.school.school.service.SchoolSiteSchoolService;



@Service
public class SchoolSiteSchoolServiceImpl implements SchoolSiteSchoolService {
	@Autowired
	private SchoolSiteSchoolDao schoolSiteSchoolDao;
	
	@Override
	public SchoolSiteDO get(Long id){
		return schoolSiteSchoolDao.get(id);
	}
	
	@Override
	public List<SchoolSiteDO> list(Map<String, Object> map){
		return schoolSiteSchoolDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return schoolSiteSchoolDao.count(map);
	}
	
	@Override
	public int save(SchoolSiteDO schoolSiteSchool){
		return schoolSiteSchoolDao.save(schoolSiteSchool);
	}
	
	@Override
	public int update(SchoolSiteDO schoolSiteSchool){
		return schoolSiteSchoolDao.update(schoolSiteSchool);
	}
	
	@Override
	public int remove(Long id){
		return schoolSiteSchoolDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return schoolSiteSchoolDao.batchRemove(ids);
	}
	
}
