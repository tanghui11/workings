package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.ScoreTempStudentDao;
import com.hxy.nzxy.stexam.domain.ScoreTempDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreTempStudentService;



@Service
public class ScoreTempStudentServiceImpl implements ScoreTempStudentService {
	@Autowired
	private ScoreTempStudentDao scoreTempStudentDao;
	
	@Override
	public ScoreTempDO get(Long seatArrangeid){
		return scoreTempStudentDao.get(seatArrangeid);
	}
	
	@Override
	public List<ScoreTempDO> list(Map<String, Object> map){
		return scoreTempStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreTempStudentDao.count(map);
	}
	
	@Override
	public int save(ScoreTempDO scoreTempStudent){
		return scoreTempStudentDao.save(scoreTempStudent);
	}
	
	@Override
	public int update(ScoreTempDO scoreTempStudent){
		return scoreTempStudentDao.update(scoreTempStudent);
	}
	
	@Override
	public int remove(Long seatArrangeid){
		return scoreTempStudentDao.remove(seatArrangeid);
	}
	
	@Override
	public int batchRemove(Long[] seatArrangeids){
		return scoreTempStudentDao.batchRemove(seatArrangeids);
	}
	
}
