package com.hxy.nzxy.stexam.school.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.school.dao.TeachSiteSchoolDao;
import com.hxy.nzxy.stexam.domain.TeachSiteDO;
import com.hxy.nzxy.stexam.school.school.service.TeachSiteSchoolService;



@Service
public class TeachSiteSchoolServiceImpl implements TeachSiteSchoolService {
	@Autowired
	private TeachSiteSchoolDao teachSiteSchoolDao;
	
	@Override
	public TeachSiteDO get(Long id){
		return teachSiteSchoolDao.get(id);
	}
	
	@Override
	public List<TeachSiteDO> list(Map<String, Object> map){
		return teachSiteSchoolDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return teachSiteSchoolDao.count(map);
	}
	
	@Override
	public int save(TeachSiteDO teachSiteSchool){
		return teachSiteSchoolDao.save(teachSiteSchool);
	}
	
	@Override
	public int update(TeachSiteDO teachSiteSchool){
		return teachSiteSchoolDao.update(teachSiteSchool);
	}
	
	@Override
	public int remove(Long id){
		return teachSiteSchoolDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return teachSiteSchoolDao.batchRemove(ids);
	}
	
}
