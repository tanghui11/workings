package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.PracticeSchoolHisDao;
import com.hxy.nzxy.stexam.domain.PracticeSchoolHisDO;
import com.hxy.nzxy.stexam.center.student.service.PracticeSchoolHisService;



@Service
public class PracticeSchoolHisServiceImpl implements PracticeSchoolHisService {
	@Autowired
	private PracticeSchoolHisDao practiceSchoolHisDao;
	
	@Override
	public PracticeSchoolHisDO get(Long id){
		return practiceSchoolHisDao.get(id);
	}
	
	@Override
	public List<PracticeSchoolHisDO> list(Map<String, Object> map){
		return practiceSchoolHisDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return practiceSchoolHisDao.count(map);
	}
	
	@Override
	public int save(PracticeSchoolHisDO practiceSchoolHis){
		return practiceSchoolHisDao.save(practiceSchoolHis);
	}
	
	@Override
	public int update(PracticeSchoolHisDO practiceSchoolHis){
		return practiceSchoolHisDao.update(practiceSchoolHis);
	}
	
	@Override
	public int remove(Long id){
		return practiceSchoolHisDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return practiceSchoolHisDao.batchRemove(ids);
	}
	
}
