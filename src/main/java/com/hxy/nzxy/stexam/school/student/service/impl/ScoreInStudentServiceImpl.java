package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.ScoreInStudentDao;
import com.hxy.nzxy.stexam.domain.ScoreInDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreInStudentService;



@Service
public class ScoreInStudentServiceImpl implements ScoreInStudentService {
	@Autowired
	private ScoreInStudentDao scoreInStudentDao;
	
	@Override
	public ScoreInDO get(Long id){
		return scoreInStudentDao.get(id);
	}
	
	@Override
	public List<ScoreInDO> list(Map<String, Object> map){
		return scoreInStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreInStudentDao.count(map);
	}
	
	@Override
	public int save(ScoreInDO scoreInStudent){
		return scoreInStudentDao.save(scoreInStudent);
	}
	
	@Override
	public int update(ScoreInDO scoreInStudent){
		return scoreInStudentDao.update(scoreInStudent);
	}
	
	@Override
	public int remove(Long id){
		return scoreInStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreInStudentDao.batchRemove(ids);
	}
	
}
