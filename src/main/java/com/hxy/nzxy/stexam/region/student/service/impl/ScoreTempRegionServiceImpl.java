package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.ScoreTempRegionDao;
import com.hxy.nzxy.stexam.domain.ScoreTempDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreTempRegionService;



@Service
public class ScoreTempRegionServiceImpl implements ScoreTempRegionService {
	@Autowired
	private ScoreTempRegionDao scoreTempRegionDao;
	
	@Override
	public ScoreTempDO get(Long seatArrangeid){
		return scoreTempRegionDao.get(seatArrangeid);
	}
	
	@Override
	public List<ScoreTempDO> list(Map<String, Object> map){
		return scoreTempRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreTempRegionDao.count(map);
	}
	
	@Override
	public int save(ScoreTempDO scoreTempRegion){
		return scoreTempRegionDao.save(scoreTempRegion);
	}
	
	@Override
	public int update(ScoreTempDO scoreTempRegion){
		return scoreTempRegionDao.update(scoreTempRegion);
	}
	
	@Override
	public int remove(Long seatArrangeid){
		return scoreTempRegionDao.remove(seatArrangeid);
	}
	
	@Override
	public int batchRemove(Long[] seatArrangeids){
		return scoreTempRegionDao.batchRemove(seatArrangeids);
	}
	
}
