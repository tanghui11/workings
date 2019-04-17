package com.hxy.nzxy.stexam.center.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.exam.dao.FoulPunishDao;
import com.hxy.nzxy.stexam.domain.FoulPunishDO;
import com.hxy.nzxy.stexam.center.exam.service.FoulPunishService;



@Service
public class FoulPunishServiceImpl implements FoulPunishService {
	@Autowired
	private FoulPunishDao foulPunishDao;
	
	@Override
	public FoulPunishDO get(Long id){
		return foulPunishDao.get(id);
	}
	
	@Override
	public List<FoulPunishDO> list(Map<String, Object> map){
		return foulPunishDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return foulPunishDao.count(map);
	}
	
	@Override
	public int save(FoulPunishDO foulPunish){
		return foulPunishDao.save(foulPunish);
	}
	
	@Override
	public int update(FoulPunishDO foulPunish){
		return foulPunishDao.update(foulPunish);
	}
	
	@Override
	public int remove(Long id){
		return foulPunishDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return foulPunishDao.batchRemove(ids);
	}
	
}
