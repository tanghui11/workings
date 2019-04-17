package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.PracticeSchoolHisStudentDao;
import com.hxy.nzxy.stexam.domain.PracticeSchoolHisDO;
import com.hxy.nzxy.stexam.school.student.service.PracticeSchoolHisStudentService;



@Service
public class PracticeSchoolHisStudentServiceImpl implements PracticeSchoolHisStudentService {
	@Autowired
	private PracticeSchoolHisStudentDao practiceSchoolHisStudentDao;
	
	@Override
	public PracticeSchoolHisDO get(Long id){
		return practiceSchoolHisStudentDao.get(id);
	}
	
	@Override
	public List<PracticeSchoolHisDO> list(Map<String, Object> map){
		return practiceSchoolHisStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return practiceSchoolHisStudentDao.count(map);
	}
	
	@Override
	public int save(PracticeSchoolHisDO practiceSchoolHisStudent){
		return practiceSchoolHisStudentDao.save(practiceSchoolHisStudent);
	}
	
	@Override
	public int update(PracticeSchoolHisDO practiceSchoolHisStudent){
		return practiceSchoolHisStudentDao.update(practiceSchoolHisStudent);
	}
	
	@Override
	public int remove(Long id){
		return practiceSchoolHisStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return practiceSchoolHisStudentDao.batchRemove(ids);
	}
	
}
