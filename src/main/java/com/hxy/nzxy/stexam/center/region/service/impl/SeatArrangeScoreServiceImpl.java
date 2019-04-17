package com.hxy.nzxy.stexam.center.region.service.impl;

import com.hxy.nzxy.stexam.center.region.dao.SeatArrangeDao;
import com.hxy.nzxy.stexam.center.region.dao.SeatArrangeScoreDao;
import com.hxy.nzxy.stexam.center.region.service.SeatArrangeScoreService;
import com.hxy.nzxy.stexam.center.region.service.SeatArrangeService;
import com.hxy.nzxy.stexam.domain.SeatArrangeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SeatArrangeScoreServiceImpl implements SeatArrangeScoreService {
	@Autowired
	private SeatArrangeScoreDao seatArrangeScoreDao;
	
	@Override
	public SeatArrangeDO get(Long id){
		return seatArrangeScoreDao.get(id);
	}
	
	@Override
	public List<SeatArrangeDO> list(Map<String, Object> map){
		return seatArrangeScoreDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return seatArrangeScoreDao.count(map);
	}
	
	@Override
	public int save(SeatArrangeDO seatArrange){
		return seatArrangeScoreDao.save(seatArrange);
	}
	
	@Override
	public int update(SeatArrangeDO seatArrange){
		return seatArrangeScoreDao.update(seatArrange);
	}
	
	@Override
	public int remove(Long id){
		return seatArrangeScoreDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return seatArrangeScoreDao.batchRemove(ids);
	}

	@Override
	public List<Map<String, Object>> listSingle(Map<String, Object> params) {
		return seatArrangeScoreDao.listSingle(params);
	}

}
