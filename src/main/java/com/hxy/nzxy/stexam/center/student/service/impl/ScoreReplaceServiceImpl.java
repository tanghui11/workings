package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.ScoreReplaceDao;
import com.hxy.nzxy.stexam.domain.ScoreReplaceDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreReplaceService;



@Service
public class ScoreReplaceServiceImpl implements ScoreReplaceService {
	@Autowired
	private ScoreReplaceDao scoreReplaceDao;
	
	@Override
	public ScoreReplaceDO get(Long id){
		return scoreReplaceDao.get(id);
	}
	
	@Override
	public List<ScoreReplaceDO> list(Map<String, Object> map){
		return scoreReplaceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreReplaceDao.count(map);
	}
	
	@Override
	public int save(ScoreReplaceDO scoreReplace){
		return scoreReplaceDao.save(scoreReplace);
	}
	
	@Override
	public int update(ScoreReplaceDO scoreReplace){
		return scoreReplaceDao.update(scoreReplace);
	}

	@Override
	public int updateStatus(Map<String, Object> params){
		return scoreReplaceDao.updateStatus(params);
	}

	@Override
	public int remove(Long id){
		return scoreReplaceDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreReplaceDao.batchRemove(ids);
	}
	
}
