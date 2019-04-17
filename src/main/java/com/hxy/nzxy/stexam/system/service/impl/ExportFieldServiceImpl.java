package com.hxy.nzxy.stexam.system.service.impl;

import com.hxy.nzxy.stexam.system.dao.ExportFieldDao;
import com.hxy.nzxy.stexam.system.domain.ExportFieldDO;
import com.hxy.nzxy.stexam.system.service.ExportFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.system.dao.ExportFieldDao;
import com.hxy.nzxy.stexam.system.domain.ExportFieldDO;
import com.hxy.nzxy.stexam.system.service.ExportFieldService;



@Service
public class ExportFieldServiceImpl implements ExportFieldService {
	@Autowired
	private ExportFieldDao exportFieldDao;
	
	@Override
	public ExportFieldDO get(Long id){
		return exportFieldDao.get(id);
	}
	
	@Override
	public List<ExportFieldDO> list(Map<String, Object> map){
		return exportFieldDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return exportFieldDao.count(map);
	}
	
	@Override
	public int save(ExportFieldDO exportField){
		return exportFieldDao.save(exportField);
	}
	
	@Override
	public int update(ExportFieldDO exportField){
		return exportFieldDao.update(exportField);
	}
	
	@Override
	public int remove(String id){
		return exportFieldDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return exportFieldDao.batchRemove(ids);
	}
	
}
