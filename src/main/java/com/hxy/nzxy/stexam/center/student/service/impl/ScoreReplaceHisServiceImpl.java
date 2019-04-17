package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.ScoreReplaceHisDao;
import com.hxy.nzxy.stexam.domain.ScoreReplaceHisDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreReplaceHisService;



@Service
public class ScoreReplaceHisServiceImpl implements ScoreReplaceHisService {
	@Autowired
	private ScoreReplaceHisDao scoreReplaceHisDao;
	
	@Override
	public ScoreReplaceHisDO get(Long id){
		return scoreReplaceHisDao.get(id);
	}
	
	@Override
	public List<ScoreReplaceHisDO> list(Map<String, Object> map){
		return scoreReplaceHisDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreReplaceHisDao.count(map);
	}
	
	@Override
	public int save(ScoreReplaceHisDO scoreReplaceHis){
		return scoreReplaceHisDao.save(scoreReplaceHis);
	}
	
	@Override
	public int update(ScoreReplaceHisDO scoreReplaceHis){
		return scoreReplaceHisDao.update(scoreReplaceHis);
	}
	
	@Override
	public int remove(Long id){
		return scoreReplaceHisDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreReplaceHisDao.batchRemove(ids);
	}
	
}
