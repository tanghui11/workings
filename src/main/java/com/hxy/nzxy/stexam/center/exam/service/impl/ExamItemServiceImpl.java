package com.hxy.nzxy.stexam.center.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.exam.dao.ExamItemDao;
import com.hxy.nzxy.stexam.domain.ExamItemDO;
import com.hxy.nzxy.stexam.center.exam.service.ExamItemService;



@Service
public class ExamItemServiceImpl implements ExamItemService {
	@Autowired
	private ExamItemDao examItemDao;
	
	@Override
	public ExamItemDO get(Long id){
		return examItemDao.get(id);
	}
	
	@Override
	public List<ExamItemDO> list(Map<String, Object> map){
		return examItemDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return examItemDao.count(map);
	}
	
	@Override
	public int save(ExamItemDO examItem){
		return examItemDao.save(examItem);
	}
	
	@Override
	public int update(ExamItemDO examItem){
		return examItemDao.update(examItem);
	}
	
	@Override
	public int remove(Long id){
		return examItemDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return examItemDao.batchRemove(ids);
	}
	
}
