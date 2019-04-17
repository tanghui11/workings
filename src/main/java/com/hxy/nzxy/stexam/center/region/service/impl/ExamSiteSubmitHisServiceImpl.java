package com.hxy.nzxy.stexam.center.region.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.region.dao.ExamSiteSubmitHisDao;
import com.hxy.nzxy.stexam.domain.ExamSiteSubmitHisDO;
import com.hxy.nzxy.stexam.center.region.service.ExamSiteSubmitHisService;



@Service
public class ExamSiteSubmitHisServiceImpl implements ExamSiteSubmitHisService {
	@Autowired
	private ExamSiteSubmitHisDao examSiteSubmitHisDao;
	
	@Override
	public ExamSiteSubmitHisDO get(Long id){
		return examSiteSubmitHisDao.get(id);
	}
	
	@Override
	public List<ExamSiteSubmitHisDO> list(Map<String, Object> map){
		return examSiteSubmitHisDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return examSiteSubmitHisDao.count(map);
	}
	
	@Override
	public int save(ExamSiteSubmitHisDO examSiteSubmitHis){
		return examSiteSubmitHisDao.save(examSiteSubmitHis);
	}
	
	@Override
	public int update(ExamSiteSubmitHisDO examSiteSubmitHis){
		return examSiteSubmitHisDao.update(examSiteSubmitHis);
	}
	
	@Override
	public int remove(Long id){
		return examSiteSubmitHisDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return examSiteSubmitHisDao.batchRemove(ids);
	}
	
}
