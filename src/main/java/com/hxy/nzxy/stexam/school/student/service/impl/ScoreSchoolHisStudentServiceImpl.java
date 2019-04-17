package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.ScoreSchoolHisStudentDao;
import com.hxy.nzxy.stexam.domain.ScoreSchoolHisDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreSchoolHisStudentService;



@Service
public class ScoreSchoolHisStudentServiceImpl implements ScoreSchoolHisStudentService {
	@Autowired
	private ScoreSchoolHisStudentDao scoreSchoolHisStudentDao;
	
	@Override
	public ScoreSchoolHisDO get(Long id){
		return scoreSchoolHisStudentDao.get(id);
	}
	
	@Override
	public List<ScoreSchoolHisDO> list(Map<String, Object> map){
		return scoreSchoolHisStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreSchoolHisStudentDao.count(map);
	}
	
	@Override
	public int save(ScoreSchoolHisDO scoreSchoolHisStudent){
		return scoreSchoolHisStudentDao.save(scoreSchoolHisStudent);
	}
	
	@Override
	public int update(ScoreSchoolHisDO scoreSchoolHisStudent){
		return scoreSchoolHisStudentDao.update(scoreSchoolHisStudent);
	}
	
	@Override
	public int remove(Long id){
		return scoreSchoolHisStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreSchoolHisStudentDao.batchRemove(ids);
	}
	
}
