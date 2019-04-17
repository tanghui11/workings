package com.hxy.nzxy.stexam.region.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.region.dao.SeatArrangeHisRegionDao;
import com.hxy.nzxy.stexam.domain.SeatArrangeHisDO;
import com.hxy.nzxy.stexam.region.region.service.SeatArrangeHisRegionService;



@Service
public class SeatArrangeHisRegionServiceImpl implements SeatArrangeHisRegionService {
	@Autowired
	private SeatArrangeHisRegionDao seatArrangeHisRegionDao;
	
	@Override
	public SeatArrangeHisDO get(Long id){
		return seatArrangeHisRegionDao.get(id);
	}
	
	@Override
	public List<SeatArrangeHisDO> list(Map<String, Object> map){
		return seatArrangeHisRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return seatArrangeHisRegionDao.count(map);
	}
	
	@Override
	public int save(SeatArrangeHisDO seatArrangeHisRegion){
		return seatArrangeHisRegionDao.save(seatArrangeHisRegion);
	}
	
	@Override
	public int update(SeatArrangeHisDO seatArrangeHisRegion){
		return seatArrangeHisRegionDao.update(seatArrangeHisRegion);
	}
	
	@Override
	public int remove(Long id){
		return seatArrangeHisRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return seatArrangeHisRegionDao.batchRemove(ids);
	}
	
}
