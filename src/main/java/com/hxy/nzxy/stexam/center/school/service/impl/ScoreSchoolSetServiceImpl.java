package com.hxy.nzxy.stexam.center.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.school.dao.ScoreSchoolSetDao;
import com.hxy.nzxy.stexam.domain.ScoreSchoolSetDO;
import com.hxy.nzxy.stexam.center.school.service.ScoreSchoolSetService;



@Service
public class ScoreSchoolSetServiceImpl implements ScoreSchoolSetService {
	@Autowired
	private ScoreSchoolSetDao scoreSchoolSetDao;
	
	@Override
	public ScoreSchoolSetDO get(Long id){
		return scoreSchoolSetDao.get(id);
	}
	
	@Override
	public List<ScoreSchoolSetDO> list(Map<String, Object> map){
		return scoreSchoolSetDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreSchoolSetDao.count(map);
	}
	
	@Override
	public int save(ScoreSchoolSetDO scoreSchoolSet){
		return scoreSchoolSetDao.save(scoreSchoolSet);
	}
	
	@Override
	public int update(ScoreSchoolSetDO scoreSchoolSet){
		return scoreSchoolSetDao.update(scoreSchoolSet);
	}
	
	@Override
	public int remove(Long id){
		return scoreSchoolSetDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreSchoolSetDao.batchRemove(ids);
	}
	
}
