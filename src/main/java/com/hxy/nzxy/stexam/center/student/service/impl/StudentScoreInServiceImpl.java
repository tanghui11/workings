package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.center.student.dao.StudentScoreInDao;
import com.hxy.nzxy.stexam.center.student.service.StudentScoreInService;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentScoreInDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class StudentScoreInServiceImpl implements StudentScoreInService {
	@Autowired
	private StudentScoreInDao studentScoreInDao;
	
	@Override
	public StudentScoreInDO get(Long id){
		return studentScoreInDao.get(id);
	}
	
	@Override
	public List<StudentScoreInDO> list(Map<String, Object> map){
		return studentScoreInDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentScoreInDao.count(map);
	}
	
	@Override
	public int save(StudentScoreInDO studentScoreIn){
		return studentScoreInDao.save(studentScoreIn);
	}
	
	@Override
	public int update(StudentScoreInDO studentScoreIn){
		return studentScoreInDao.update(studentScoreIn);
	}
	
	@Override
	public int remove(Long id){
		return studentScoreInDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentScoreInDao.batchRemove(ids);
	}

	@Override
	public List<StudentScoreInDO> migrationList(Query query) {
		return studentScoreInDao.migrationList(query);
	}

	@Override
	public int migrationCount(Query query) {
		return studentScoreInDao.migrationCount(query);
	}

}
