package com.hxy.nzxy.stexam.center.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.exam.dao.EducateLengthDao;
import com.hxy.nzxy.stexam.domain.EducateLengthDO;
import com.hxy.nzxy.stexam.center.exam.service.EducateLengthService;



@Service
public class EducateLengthServiceImpl implements EducateLengthService {
	@Autowired
	private EducateLengthDao educateLengthDao;
	
	@Override
	public EducateLengthDO get(Long id){
		return educateLengthDao.get(id);
	}
	
	@Override
	public List<EducateLengthDO> list(Map<String, Object> map){
		return educateLengthDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return educateLengthDao.count(map);
	}
	
	@Override
	public int save(EducateLengthDO educateLength){
		return educateLengthDao.save(educateLength);
	}
	
	@Override
	public int update(EducateLengthDO educateLength){
		return educateLengthDao.update(educateLength);
	}
	
	@Override
	public int remove(Long id){
		return educateLengthDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return educateLengthDao.batchRemove(ids);
	}
	
}
