package com.hxy.nzxy.stexam.center.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.region.dao.ExamSiteSubmitDao;
import com.hxy.nzxy.stexam.domain.ExamSiteSubmitDO;
import com.hxy.nzxy.stexam.center.region.service.ExamSiteSubmitService;



@Service
public class ExamSiteSubmitServiceImpl implements ExamSiteSubmitService {
	@Autowired
	private ExamSiteSubmitDao examSiteSubmitDao;
	
	@Override
	public ExamSiteSubmitDO get(Long id){
		return examSiteSubmitDao.get(id);
	}
	
	@Override
	public List<ExamSiteSubmitDO> list(Map<String, Object> map){
		return examSiteSubmitDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return examSiteSubmitDao.count(map);
	}
	
	@Override
	public int save(ExamSiteSubmitDO examSiteSubmit){
		return examSiteSubmitDao.save(examSiteSubmit);
	}
	
	@Override
	public int update(ExamSiteSubmitDO examSiteSubmit){
		return examSiteSubmitDao.update(examSiteSubmit);
	}
	
	@Override
	public int remove(Long id){
		return examSiteSubmitDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return examSiteSubmitDao.batchRemove(ids);
	}
	
}
