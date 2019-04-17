package com.hxy.nzxy.stexam.school.school.service.impl;

import com.hxy.nzxy.stexam.system.domain.DeptWorkerDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.school.dao.SchoolSchoolDao;
import com.hxy.nzxy.stexam.domain.SchoolDO;
import com.hxy.nzxy.stexam.school.school.service.SchoolSchoolService;



@Service
public class SchoolSchoolServiceImpl implements SchoolSchoolService {
	@Autowired
	private SchoolSchoolDao schoolSchoolDao;
	
	@Override
	public SchoolDO get(Long id){
		return schoolSchoolDao.get(id);
	}
	
	@Override
	public List<SchoolDO> list(Map<String, Object> map){
		return schoolSchoolDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return schoolSchoolDao.count(map);
	}
	
	@Override
	public int save(SchoolDO schoolSchool){
		return schoolSchoolDao.save(schoolSchool);
	}
	
	@Override
	public int update(SchoolDO schoolSchool){
		return schoolSchoolDao.update(schoolSchool);
	}
	
	@Override
	public int remove(Long id){
		return schoolSchoolDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return schoolSchoolDao.batchRemove(ids);
	}

	@Override
	public SchoolDO getDept(String workerid) {
		return schoolSchoolDao.getDept(workerid);
	}

}
