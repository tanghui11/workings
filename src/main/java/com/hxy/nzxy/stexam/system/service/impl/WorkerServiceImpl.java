package com.hxy.nzxy.stexam.system.service.impl;

import com.hxy.nzxy.stexam.system.dao.WorkerDao;
import com.hxy.nzxy.stexam.system.domain.WorkerDO;
import com.hxy.nzxy.stexam.system.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.system.dao.WorkerDao;
import com.hxy.nzxy.stexam.system.domain.WorkerDO;
import com.hxy.nzxy.stexam.system.service.WorkerService;



@Service
public class WorkerServiceImpl implements WorkerService {
	@Autowired
	private WorkerDao workerDao;

	@Override
	public WorkerDO get(String id){
		return workerDao.get(id);
	}

	@Override
	public WorkerDO getByMphone(String id){
		return workerDao.getByMphone(id);
	}
	
	@Override
	public List<WorkerDO> list(Map<String, Object> map){
		return workerDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return workerDao.count(map);
	}
	
	@Override
	public int save(WorkerDO worker){
		return workerDao.save(worker);
	}
	
	@Override
	public int update(WorkerDO worker){
		return workerDao.update(worker);
	}
	
	@Override
	public int remove(Long id){
		return workerDao.remove(id);
	}
}
