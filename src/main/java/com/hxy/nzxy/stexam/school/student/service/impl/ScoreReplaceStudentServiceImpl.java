package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.ScoreReplaceStudentDao;
import com.hxy.nzxy.stexam.domain.ScoreReplaceDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreReplaceStudentService;



@Service
public class ScoreReplaceStudentServiceImpl implements ScoreReplaceStudentService {
	@Autowired
	private ScoreReplaceStudentDao scoreReplaceStudentDao;
	
	@Override
	public ScoreReplaceDO get(Long id){
		return scoreReplaceStudentDao.get(id);
	}
	
	@Override
	public List<ScoreReplaceDO> list(Map<String, Object> map){
		return scoreReplaceStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreReplaceStudentDao.count(map);
	}
	
	@Override
	public int save(ScoreReplaceDO scoreReplaceStudent){
		return scoreReplaceStudentDao.save(scoreReplaceStudent);
	}
	
	@Override
	public int update(ScoreReplaceDO scoreReplaceStudent){
		return scoreReplaceStudentDao.update(scoreReplaceStudent);
	}
	
	@Override
	public int remove(Long id){
		return scoreReplaceStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreReplaceStudentDao.batchRemove(ids);
	}
	
}
