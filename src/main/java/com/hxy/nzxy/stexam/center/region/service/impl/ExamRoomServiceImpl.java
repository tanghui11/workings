package com.hxy.nzxy.stexam.center.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.region.dao.ExamRoomDao;
import com.hxy.nzxy.stexam.domain.ExamRoomDO;
import com.hxy.nzxy.stexam.center.region.service.ExamRoomService;



@Service
public class ExamRoomServiceImpl implements ExamRoomService {
	@Autowired
	private ExamRoomDao examRoomDao;
	
	@Override
	public ExamRoomDO get(Long id){
		return examRoomDao.get(id);
	}
	
	@Override
	public List<ExamRoomDO> list(Map<String, Object> map){
		return examRoomDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return examRoomDao.count(map);
	}
	
	@Override
	public int save(ExamRoomDO examRoom){
		return examRoomDao.save(examRoom);
	}
	
	@Override
	public int update(ExamRoomDO examRoom){
		return examRoomDao.update(examRoom);
	}
	
	@Override
	public int remove(Long id){
		return examRoomDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return examRoomDao.batchRemove(ids);
	}
	
}
