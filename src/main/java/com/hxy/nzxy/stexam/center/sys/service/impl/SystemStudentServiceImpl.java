package com.hxy.nzxy.stexam.center.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.sys.dao.SystemStudentDao;
import com.hxy.nzxy.stexam.domain.SystemStudentDO;
import com.hxy.nzxy.stexam.center.sys.service.SystemStudentService;



@Service
public class SystemStudentServiceImpl implements SystemStudentService {
	@Autowired
	private SystemStudentDao systemStudentDao;
	
	@Override
	public SystemStudentDO get(Long id){
		return systemStudentDao.get(id);
	}
	
	@Override
	public List<SystemStudentDO> list(Map<String, Object> map){
		return systemStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return systemStudentDao.count(map);
	}
	
	@Override
	public int save(SystemStudentDO systemStudent){
		return systemStudentDao.save(systemStudent);
	}
	
	@Override
	public int update(SystemStudentDO systemStudent){
		return systemStudentDao.update(systemStudent);
	}
	
	@Override
	public int remove(Long id){
		return systemStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return systemStudentDao.batchRemove(ids);
	}
	
}
