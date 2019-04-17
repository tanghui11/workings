package com.hxy.nzxy.stexam.center.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.sys.dao.SysOptionDao;
import com.hxy.nzxy.stexam.domain.SystemOptionDO;
import com.hxy.nzxy.stexam.center.sys.service.SysOptionService;



@Service
public class SysOptionServiceImpl implements SysOptionService {
	@Autowired
	private SysOptionDao systemOptionDao;
	
	@Override
	public SystemOptionDO get(Long id){
		return systemOptionDao.get(id);
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
	public int remove(Long id){
		return systemOptionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return systemOptionDao.batchRemove(ids);
	}
	
}
