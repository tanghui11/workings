package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.ScoreImportStudentDao;
import com.hxy.nzxy.stexam.domain.ScoreImportDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreImportStudentService;



@Service
public class ScoreImportStudentServiceImpl implements ScoreImportStudentService {
	@Autowired
	private ScoreImportStudentDao scoreImportStudentDao;
	
	@Override
	public ScoreImportDO get(String kemuid){
		return scoreImportStudentDao.get(kemuid);
	}
	
	@Override
	public List<ScoreImportDO> list(Map<String, Object> map){
		return scoreImportStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreImportStudentDao.count(map);
	}
	
	@Override
	public int save(ScoreImportDO scoreImportStudent){
		return scoreImportStudentDao.save(scoreImportStudent);
	}
	
	@Override
	public int update(ScoreImportDO scoreImportStudent){
		return scoreImportStudentDao.update(scoreImportStudent);
	}
	
	@Override
	public int remove(String kemuid){
		return scoreImportStudentDao.remove(kemuid);
	}
	
	@Override
	public int batchRemove(String[] kemuids){
		return scoreImportStudentDao.batchRemove(kemuids);
	}
	
}
