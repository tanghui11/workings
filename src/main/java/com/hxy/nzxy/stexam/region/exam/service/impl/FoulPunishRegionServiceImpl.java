package com.hxy.nzxy.stexam.region.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.exam.dao.FoulPunishRegionDao;
import com.hxy.nzxy.stexam.domain.FoulPunishDO;
import com.hxy.nzxy.stexam.region.exam.service.FoulPunishRegionService;



@Service
public class FoulPunishRegionServiceImpl implements FoulPunishRegionService {
	@Autowired
	private FoulPunishRegionDao foulPunishRegionDao;
	
	@Override
	public FoulPunishDO get(Long id){
		return foulPunishRegionDao.get(id);
	}
	
	@Override
	public List<FoulPunishDO> list(Map<String, Object> map){
		return foulPunishRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return foulPunishRegionDao.count(map);
	}
	
	@Override
	public int save(FoulPunishDO foulPunishRegion){
		return foulPunishRegionDao.save(foulPunishRegion);
	}
	
	@Override
	public int update(FoulPunishDO foulPunishRegion){
		return foulPunishRegionDao.update(foulPunishRegion);
	}
	
	@Override
	public int remove(Long id){
		return foulPunishRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return foulPunishRegionDao.batchRemove(ids);
	}
	
}
