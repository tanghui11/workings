package com.hxy.nzxy.stexam.system.service.impl;

import com.hxy.nzxy.stexam.system.dao.SystemOptionDao;
import com.hxy.nzxy.stexam.system.domain.SystemOptionDO;
import com.hxy.nzxy.stexam.system.service.SystemOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.system.dao.SystemOptionDao;
import com.hxy.nzxy.stexam.system.domain.SystemOptionDO;
import com.hxy.nzxy.stexam.system.service.SystemOptionService;



@Service
public class SystemOptionServiceImpl implements SystemOptionService {
	@Autowired
	private SystemOptionDao systemOptionDao;
	
	@Override
	public SystemOptionDO get(Map<String, Object> map){
		return systemOptionDao.get(map);
	}
	
	@Override
	public List<SystemOptionDO> list(Map<String, Object> map){
		return systemOptionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return systemOptionDao.count(map);
	}
	
	@Override
	public int save(SystemOptionDO systemOption){
		return systemOptionDao.save(systemOption);
	}
	
	@Override
	public int update(SystemOptionDO systemOption){
		return systemOptionDao.update(systemOption);
	}
	
	@Override
	public int remove(Map<String, Object> map){
		return systemOptionDao.remove(map);
	}
	
}
