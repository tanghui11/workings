package com.hxy.nzxy.stexam.region.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.region.dao.SeatArrangeRegionDao;
import com.hxy.nzxy.stexam.domain.SeatArrangeDO;
import com.hxy.nzxy.stexam.region.region.service.SeatArrangeRegionService;



@Service
public class SeatArrangeRegionServiceImpl implements SeatArrangeRegionService {
	@Autowired
	private SeatArrangeRegionDao seatArrangeRegionDao;
	
	@Override
	public SeatArrangeDO get(Long id){
		return seatArrangeRegionDao.get(id);
	}
	
	@Override
	public List<SeatArrangeDO> list(Map<String, Object> map){
		return seatArrangeRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return seatArrangeRegionDao.count(map);
	}
	
	@Override
	public int save(SeatArrangeDO seatArrangeRegion){
		return seatArrangeRegionDao.save(seatArrangeRegion);
	}
	
	@Override
	public int update(SeatArrangeDO seatArrangeRegion){
		return seatArrangeRegionDao.update(seatArrangeRegion);
	}
	
	@Override
	public int remove(Long id){
		return seatArrangeRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return seatArrangeRegionDao.batchRemove(ids);
	}
	
}
