package com.hxy.nzxy.stexam.center.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.region.dao.SeatArrangeHisDao;
import com.hxy.nzxy.stexam.domain.SeatArrangeHisDO;
import com.hxy.nzxy.stexam.center.region.service.SeatArrangeHisService;



@Service
public class SeatArrangeHisServiceImpl implements SeatArrangeHisService {
	@Autowired
	private SeatArrangeHisDao seatArrangeHisDao;
	
	@Override
	public SeatArrangeHisDO get(Long id){
		return seatArrangeHisDao.get(id);
	}
	
	@Override
	public List<SeatArrangeHisDO> list(Map<String, Object> map){
		return seatArrangeHisDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return seatArrangeHisDao.count(map);
	}
	
	@Override
	public int save(SeatArrangeHisDO seatArrangeHis){
		return seatArrangeHisDao.save(seatArrangeHis);
	}
	
	@Override
	public int update(SeatArrangeHisDO seatArrangeHis){
		return seatArrangeHisDao.update(seatArrangeHis);
	}
	
	@Override
	public int remove(Long id){
		return seatArrangeHisDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return seatArrangeHisDao.batchRemove(ids);
	}
	
}
