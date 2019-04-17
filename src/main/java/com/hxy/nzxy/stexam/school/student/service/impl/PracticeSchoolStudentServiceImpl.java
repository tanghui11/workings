package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.PracticeSchoolStudentDao;
import com.hxy.nzxy.stexam.domain.PracticeSchoolDO;
import com.hxy.nzxy.stexam.school.student.service.PracticeSchoolStudentService;



@Service
public class PracticeSchoolStudentServiceImpl implements PracticeSchoolStudentService {
	@Autowired
	private PracticeSchoolStudentDao practiceSchoolStudentDao;
	
	@Override
	public PracticeSchoolDO get(Long id){
		return practiceSchoolStudentDao.get(id);
	}
	
	@Override
	public List<PracticeSchoolDO> list(Map<String, Object> map){
		return practiceSchoolStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return practiceSchoolStudentDao.count(map);
	}
	
	@Override
	public int save(PracticeSchoolDO practiceSchoolStudent){
		return practiceSchoolStudentDao.save(practiceSchoolStudent);
	}
	
	@Override
	public int update(PracticeSchoolDO practiceSchoolStudent){
		return practiceSchoolStudentDao.update(practiceSchoolStudent);
	}
	
	@Override
	public int remove(Long id){
		return practiceSchoolStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return practiceSchoolStudentDao.batchRemove(ids);
	}
	
}
