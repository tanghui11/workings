package com.hxy.nzxy.stexam.region.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.region.dao.ExamRoomRegionDao;
import com.hxy.nzxy.stexam.domain.ExamRoomDO;
import com.hxy.nzxy.stexam.region.region.service.ExamRoomRegionService;



@Service
public class ExamRoomRegionServiceImpl implements ExamRoomRegionService {
	@Autowired
	private ExamRoomRegionDao examRoomRegionDao;
	
	@Override
	public ExamRoomDO get(Long id){
		return examRoomRegionDao.get(id);
	}
	
	@Override
	public List<ExamRoomDO> list(Map<String, Object> map){
		return examRoomRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return examRoomRegionDao.count(map);
	}
	
	@Override
	public int save(ExamRoomDO examRoomRegion){
		return examRoomRegionDao.save(examRoomRegion);
	}
	
	@Override
	public int update(ExamRoomDO examRoomRegion){
		return examRoomRegionDao.update(examRoomRegion);
	}
	
	@Override
	public int remove(Long id){
		return examRoomRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return examRoomRegionDao.batchRemove(ids);
	}

	@Override
	public List<ExamRoomDO> getSite(Map<String, Object> map) {
		return examRoomRegionDao.getSite(map);
	}

	@Override
	public int batchSave(List list) {
		return examRoomRegionDao.batchSave(list);
	}

}
