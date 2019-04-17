package com.hxy.nzxy.stexam.center.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.region.dao.RoomArrangeHisDao;
import com.hxy.nzxy.stexam.domain.RoomArrangeHisDO;
import com.hxy.nzxy.stexam.center.region.service.RoomArrangeHisService;



@Service
public class RoomArrangeHisServiceImpl implements RoomArrangeHisService {
	@Autowired
	private RoomArrangeHisDao roomArrangeHisDao;
	
	@Override
	public RoomArrangeHisDO get(Long id){
		return roomArrangeHisDao.get(id);
	}
	
	@Override
	public List<RoomArrangeHisDO> list(Map<String, Object> map){
		return roomArrangeHisDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return roomArrangeHisDao.count(map);
	}
	
	@Override
	public int save(RoomArrangeHisDO roomArrangeHis){
		return roomArrangeHisDao.save(roomArrangeHis);
	}
	
	@Override
	public int update(RoomArrangeHisDO roomArrangeHis){
		return roomArrangeHisDao.update(roomArrangeHis);
	}
	
	@Override
	public int remove(Long id){
		return roomArrangeHisDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return roomArrangeHisDao.batchRemove(ids);
	}
	
}
