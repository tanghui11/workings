package com.hxy.nzxy.stexam.system.service.impl;

import com.hxy.nzxy.stexam.system.dao.AppDao;
import com.hxy.nzxy.stexam.system.domain.AppDO;
import com.hxy.nzxy.stexam.system.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.system.dao.AppDao;
import com.hxy.nzxy.stexam.system.domain.AppDO;
import com.hxy.nzxy.stexam.system.service.AppService;



@Service
public class AppServiceImpl implements AppService {
	@Autowired
	private AppDao appDao;
	
	@Override
	public AppDO get(String id){
		return appDao.get(id);
	}
	
	@Override
	public List<AppDO> list(Map<String, Object> map){
		return appDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return appDao.count(map);
	}
	
	@Override
	public int save(AppDO app){
		return appDao.save(app);
	}
	
	@Override
	public int update(AppDO app){
		return appDao.update(app);
	}
	
	@Override
	public int remove(String id){
		return appDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return appDao.batchRemove(ids);
	}
	
}
