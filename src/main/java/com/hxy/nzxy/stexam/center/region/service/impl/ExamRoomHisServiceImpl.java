package com.hxy.nzxy.stexam.center.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.region.dao.ExamRoomHisDao;
import com.hxy.nzxy.stexam.domain.ExamRoomHisDO;
import com.hxy.nzxy.stexam.center.region.service.ExamRoomHisService;



@Service
public class ExamRoomHisServiceImpl implements ExamRoomHisService {
	@Autowired
	private ExamRoomHisDao examRoomHisDao;
	
	@Override
	public ExamRoomHisDO get(Long id){
		return examRoomHisDao.get(id);
	}
	
	@Override
	public List<ExamRoomHisDO> list(Map<String, Object> map){
		return examRoomHisDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return examRoomHisDao.count(map);
	}
	
	@Override
	public int save(ExamRoomHisDO examRoomHis){
		return examRoomHisDao.save(examRoomHis);
	}
	
	@Override
	public int update(ExamRoomHisDO examRoomHis){
		return examRoomHisDao.update(examRoomHis);
	}
	
	@Override
	public int remove(Long id){
		return examRoomHisDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return examRoomHisDao.batchRemove(ids);
	}
	
}
