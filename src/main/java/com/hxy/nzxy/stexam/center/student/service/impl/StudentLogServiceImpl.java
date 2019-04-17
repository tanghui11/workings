package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.StudentLogDao;
import com.hxy.nzxy.stexam.domain.StudentLogDO;
import com.hxy.nzxy.stexam.center.student.service.StudentLogService;



@Service
public class StudentLogServiceImpl implements StudentLogService {
	@Autowired
	private StudentLogDao studentLogDao;
	
	@Override
	public StudentLogDO get(String changeType){
		return studentLogDao.get(changeType);
	}
	
	@Override
	public List<StudentLogDO> list(Map<String, Object> map){
		return studentLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentLogDao.count(map);
	}
	
	@Override
	public int save(StudentLogDO studentLog){
		return studentLogDao.save(studentLog);
	}
	
	@Override
	public int update(StudentLogDO studentLog){
		return studentLogDao.update(studentLog);
	}
	
	@Override
	public int remove(String changeType){
		return studentLogDao.remove(changeType);
	}
	
	@Override
	public int batchRemove(String[] changeTypes){
		return studentLogDao.batchRemove(changeTypes);
	}
	
}
