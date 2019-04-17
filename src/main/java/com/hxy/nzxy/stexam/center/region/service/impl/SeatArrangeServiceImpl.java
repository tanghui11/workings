package com.hxy.nzxy.stexam.center.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.region.dao.SeatArrangeDao;
import com.hxy.nzxy.stexam.domain.SeatArrangeDO;
import com.hxy.nzxy.stexam.center.region.service.SeatArrangeService;



@Service
public class SeatArrangeServiceImpl implements SeatArrangeService {
	@Autowired
	private SeatArrangeDao seatArrangeDao;
	
	@Override
	public SeatArrangeDO get(Long id){
		return seatArrangeDao.get(id);
	}
	
	@Override
	public List<SeatArrangeDO> list(Map<String, Object> map){
		return seatArrangeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return seatArrangeDao.count(map);
	}
	
	@Override
	public int save(SeatArrangeDO seatArrange){
		return seatArrangeDao.save(seatArrange);
	}
	
	@Override
	public int update(SeatArrangeDO seatArrange){
		return seatArrangeDao.update(seatArrange);
	}
	
	@Override
	public int remove(Long id){
		return seatArrangeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return seatArrangeDao.batchRemove(ids);
	}
	
}
