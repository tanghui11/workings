package com.hxy.nzxy.stexam.region.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.exam.dao.TaskRegionDao;
import com.hxy.nzxy.stexam.domain.TaskDO;
import com.hxy.nzxy.stexam.region.exam.service.TaskRegionService;



@Service
public class TaskRegionServiceImpl implements TaskRegionService {
	@Autowired
	private TaskRegionDao taskRegionDao;
	
	@Override
	public TaskDO get(Long id){
		return taskRegionDao.get(id);
	}
	
	@Override
	public List<TaskDO> list(Map<String, Object> map){
		return taskRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return taskRegionDao.count(map);
	}
	
	@Override
	public int save(TaskDO taskRegion){
		return taskRegionDao.save(taskRegion);
	}
	
	@Override
	public int update(TaskDO taskRegion){
		return taskRegionDao.update(taskRegion);
	}
	
	@Override
	public int remove(Long id){
		return taskRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return taskRegionDao.batchRemove(ids);
	}
	
}
