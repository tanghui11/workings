package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.ScoreReplaceHisStudentDao;
import com.hxy.nzxy.stexam.domain.ScoreReplaceHisDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreReplaceHisStudentService;



@Service
public class ScoreReplaceHisStudentServiceImpl implements ScoreReplaceHisStudentService {
	@Autowired
	private ScoreReplaceHisStudentDao scoreReplaceHisStudentDao;
	
	@Override
	public ScoreReplaceHisDO get(Long id){
		return scoreReplaceHisStudentDao.get(id);
	}
	
	@Override
	public List<ScoreReplaceHisDO> list(Map<String, Object> map){
		return scoreReplaceHisStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreReplaceHisStudentDao.count(map);
	}
	
	@Override
	public int save(ScoreReplaceHisDO scoreReplaceHisStudent){
		return scoreReplaceHisStudentDao.save(scoreReplaceHisStudent);
	}
	
	@Override
	public int update(ScoreReplaceHisDO scoreReplaceHisStudent){
		return scoreReplaceHisStudentDao.update(scoreReplaceHisStudent);
	}
	
	@Override
	public int remove(Long id){
		return scoreReplaceHisStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreReplaceHisStudentDao.batchRemove(ids);
	}
	
}
