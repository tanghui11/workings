package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.ScoreSchoolHisDao;
import com.hxy.nzxy.stexam.domain.ScoreSchoolHisDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreSchoolHisService;



@Service
public class ScoreSchoolHisServiceImpl implements ScoreSchoolHisService {
	@Autowired
	private ScoreSchoolHisDao scoreSchoolHisDao;
	
	@Override
	public ScoreSchoolHisDO get(Long id){
		return scoreSchoolHisDao.get(id);
	}
	
	@Override
	public List<ScoreSchoolHisDO> list(Map<String, Object> map){
		return scoreSchoolHisDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreSchoolHisDao.count(map);
	}
	
	@Override
	public int save(ScoreSchoolHisDO scoreSchoolHis){
		return scoreSchoolHisDao.save(scoreSchoolHis);
	}
	
	@Override
	public int update(ScoreSchoolHisDO scoreSchoolHis){
		return scoreSchoolHisDao.update(scoreSchoolHis);
	}
	
	@Override
	public int remove(Long id){
		return scoreSchoolHisDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreSchoolHisDao.batchRemove(ids);
	}
	
}
