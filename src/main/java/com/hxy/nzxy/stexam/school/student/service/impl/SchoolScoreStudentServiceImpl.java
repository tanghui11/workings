package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.SchoolScoreStudentDao;
import com.hxy.nzxy.stexam.domain.SchoolScoreDO;
import com.hxy.nzxy.stexam.school.student.service.SchoolScoreStudentService;



@Service
public class SchoolScoreStudentServiceImpl implements SchoolScoreStudentService {
	@Autowired
	private SchoolScoreStudentDao schoolScoreStudentDao;
	
	@Override
	public SchoolScoreDO get(Long id){
		return schoolScoreStudentDao.get(id);
	}
	
	@Override
	public List<SchoolScoreDO> list(Map<String, Object> map){
		return schoolScoreStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return schoolScoreStudentDao.count(map);
	}
	
	@Override
	public int save(SchoolScoreDO schoolScoreStudent){
		return schoolScoreStudentDao.save(schoolScoreStudent);
	}
	
	@Override
	public int update(SchoolScoreDO schoolScoreStudent){
		return schoolScoreStudentDao.update(schoolScoreStudent);
	}
	
	@Override
	public int remove(Long id){
		return schoolScoreStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return schoolScoreStudentDao.batchRemove(ids);
	}
	@Override
	public String getSpecialityRegcordid(String specialityid,String schoolid,String collegeid,String direction) {
		return schoolScoreStudentDao.getSpecialityRegcordid(specialityid,schoolid,collegeid,direction);
	}
//	@Override
//	public int inPutGrade(String studentid,String courseid,String grade,String specialityRecordid) {
//		return schoolScoreStudentDao.inPutGrade(studentid,courseid,grade,specialityRecordid);
//	}

	@Override
	public int inPutGrade(Map<String,String> map) {
		return schoolScoreStudentDao.inPutGrade(map);
	}
	@Override
	public int whetherAreadyIn(Map<String,String> map) {
		return schoolScoreStudentDao.whetherAreadyIn(map);
	}
}
