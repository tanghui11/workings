package com.hxy.nzxy.stexam.school.student.service.impl;

import com.hxy.nzxy.stexam.domain.ScoreSchoolDO;
import com.hxy.nzxy.stexam.domain.SpecialityCourseDO;
import com.hxy.nzxy.stexam.school.student.dao.SchoolCourseScoreInputDao;
import com.hxy.nzxy.stexam.school.student.domain.SchoolCourseScoreInputDO;
import com.hxy.nzxy.stexam.school.student.service.SchoolCourseScoreInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SchoolCourseScoreInputServiceImpl implements SchoolCourseScoreInputService {
	@Autowired
	private SchoolCourseScoreInputDao schoolCourseScoreInputDao;
	
	@Override
	public SchoolCourseScoreInputDO get(Long id){
		return schoolCourseScoreInputDao.get(id);
	}
	
	@Override
	public List<SchoolCourseScoreInputDO> list(Map<String, Object> map){
		return schoolCourseScoreInputDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return schoolCourseScoreInputDao.count(map);
	}
	
	@Override
	public int save(SchoolCourseScoreInputDO schoolScoreStudent){
		return schoolCourseScoreInputDao.save(schoolScoreStudent);
	}
	
	@Override
	public int update(SchoolCourseScoreInputDO schoolCourseScoreInput){
		return schoolCourseScoreInputDao.update(schoolCourseScoreInput);
	}
	
	@Override
	public int remove(Long id){
		return schoolCourseScoreInputDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return schoolCourseScoreInputDao.batchRemove(ids);
	}
	@Override
	public String getSpecialityRegcordid(String specialityid,String schoolid,String collegeid,String direction) {
		return schoolCourseScoreInputDao.getSpecialityRegcordid(specialityid,schoolid,collegeid,direction);
	}
//	@Override
//	public int inPutGrade(String studentid,String courseid,String grade,String specialityRecordid) {
//		return schoolScoreStudentDao.inPutGrade(studentid,courseid,grade,specialityRecordid);
//	}

	@Override
	public int inPutGrade(Map<String,String> map) {
		return schoolCourseScoreInputDao.inPutGrade(map);
	}
	@Override
	public int whetherAreadyIn(Map<String,String> map) {
		return schoolCourseScoreInputDao.whetherAreadyIn(map);
	}

	@Override
	public List<SpecialityCourseDO> listSchoolCourse(Map<String, Object> params) {
		return schoolCourseScoreInputDao.listSchoolCourse(params);
	}


}
