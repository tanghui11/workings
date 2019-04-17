package com.hxy.nzxy.stexam.region.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.exam.dao.SecretOfficeRegionDao;
import com.hxy.nzxy.stexam.domain.SecretOfficeDO;
import com.hxy.nzxy.stexam.region.exam.service.SecretOfficeRegionService;



@Service
public class SecretOfficeRegionServiceImpl implements SecretOfficeRegionService {
	@Autowired
	private SecretOfficeRegionDao secretOfficeRegionDao;
	
	@Override
	public SecretOfficeDO get(Long id){
		return secretOfficeRegionDao.get(id);
	}
	
	@Override
	public List<SecretOfficeDO> list(Map<String, Object> map){
		return secretOfficeRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return secretOfficeRegionDao.count(map);
	}
	
	@Override
	public int save(SecretOfficeDO secretOfficeRegion){
		return secretOfficeRegionDao.save(secretOfficeRegion);
	}
	
	@Override
	public int update(SecretOfficeDO secretOfficeRegion){
		return secretOfficeRegionDao.update(secretOfficeRegion);
	}
	
	@Override
	public int remove(Long id){
		return secretOfficeRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return secretOfficeRegionDao.batchRemove(ids);
	}
	
}
