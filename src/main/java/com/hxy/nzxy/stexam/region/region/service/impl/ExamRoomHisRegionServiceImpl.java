package com.hxy.nzxy.stexam.region.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.region.dao.ExamRoomHisRegionDao;
import com.hxy.nzxy.stexam.domain.ExamRoomHisDO;
import com.hxy.nzxy.stexam.region.region.service.ExamRoomHisRegionService;



@Service
public class ExamRoomHisRegionServiceImpl implements ExamRoomHisRegionService {
	@Autowired
	private ExamRoomHisRegionDao examRoomHisRegionDao;
	
	@Override
	public ExamRoomHisDO get(Long id){
		return examRoomHisRegionDao.get(id);
	}
	
	@Override
	public List<ExamRoomHisDO> list(Map<String, Object> map){
		return examRoomHisRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return examRoomHisRegionDao.count(map);
	}
	
	@Override
	public int save(ExamRoomHisDO examRoomHisRegion){
		return examRoomHisRegionDao.save(examRoomHisRegion);
	}
	
	@Override
	public int update(ExamRoomHisDO examRoomHisRegion){
		return examRoomHisRegionDao.update(examRoomHisRegion);
	}
	
	@Override
	public int remove(Long id){
		return examRoomHisRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return examRoomHisRegionDao.batchRemove(ids);
	}
	
}
