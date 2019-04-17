package com.hxy.nzxy.stexam.region.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.region.dao.RoomArrangeHisRegionDao;
import com.hxy.nzxy.stexam.domain.RoomArrangeHisDO;
import com.hxy.nzxy.stexam.region.region.service.RoomArrangeHisRegionService;



@Service
public class RoomArrangeHisRegionServiceImpl implements RoomArrangeHisRegionService {
	@Autowired
	private RoomArrangeHisRegionDao roomArrangeHisRegionDao;
	
	@Override
	public RoomArrangeHisDO get(Long id){
		return roomArrangeHisRegionDao.get(id);
	}
	
	@Override
	public List<RoomArrangeHisDO> list(Map<String, Object> map){
		return roomArrangeHisRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return roomArrangeHisRegionDao.count(map);
	}
	
	@Override
	public int save(RoomArrangeHisDO roomArrangeHisRegion){
		return roomArrangeHisRegionDao.save(roomArrangeHisRegion);
	}
	
	@Override
	public int update(RoomArrangeHisDO roomArrangeHisRegion){
		return roomArrangeHisRegionDao.update(roomArrangeHisRegion);
	}
	
	@Override
	public int remove(Long id){
		return roomArrangeHisRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return roomArrangeHisRegionDao.batchRemove(ids);
	}
	
}
