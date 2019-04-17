package com.hxy.nzxy.stexam.center.plan.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.ExamCodeDao;
import com.hxy.nzxy.stexam.center.plan.service.ExamCodeService;
import com.hxy.nzxy.stexam.domain.ExamCodeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ExamCodeServiceImpl implements ExamCodeService {
	@Autowired
	private ExamCodeDao examCodeDao;
	
	@Override
	public ExamCodeDO get(String fixed){
		return examCodeDao.get(fixed);
	}
	
	@Override
	public List<ExamCodeDO> list(Map<String, Object> map){
		return examCodeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return examCodeDao.count(map);
	}
	
	@Override
	public int save(ExamCodeDO examCode){
		return examCodeDao.save(examCode);
	}
	
	@Override
	public int update(ExamCodeDO examCode){
		return examCodeDao.update(examCode);
	}
	
	@Override
	public int remove(String fixed){
		return examCodeDao.remove(fixed);
	}
	
	@Override
	public int batchRemove(String[] fixeds){
		return examCodeDao.batchRemove(fixeds);
	}
	
}
