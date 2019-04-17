package com.hxy.nzxy.stexam.school.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.school.dao.ScoreSchoolSetSchoolDao;
import com.hxy.nzxy.stexam.domain.ScoreSchoolSetDO;
import com.hxy.nzxy.stexam.school.school.service.ScoreSchoolSetSchoolService;



@Service
public class ScoreSchoolSetSchoolServiceImpl implements ScoreSchoolSetSchoolService {
	@Autowired
	private ScoreSchoolSetSchoolDao scoreSchoolSetSchoolDao;
	
	@Override
	public ScoreSchoolSetDO get(Long id){
		return scoreSchoolSetSchoolDao.get(id);
	}
	
	@Override
	public List<ScoreSchoolSetDO> list(Map<String, Object> map){
		return scoreSchoolSetSchoolDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreSchoolSetSchoolDao.count(map);
	}
	
	@Override
	public int save(ScoreSchoolSetDO scoreSchoolSetSchool){
		return scoreSchoolSetSchoolDao.save(scoreSchoolSetSchool);
	}
	
	@Override
	public int update(ScoreSchoolSetDO scoreSchoolSetSchool){
		return scoreSchoolSetSchoolDao.update(scoreSchoolSetSchool);
	}
	
	@Override
	public int remove(Long id){
		return scoreSchoolSetSchoolDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreSchoolSetSchoolDao.batchRemove(ids);
	}
	
}
