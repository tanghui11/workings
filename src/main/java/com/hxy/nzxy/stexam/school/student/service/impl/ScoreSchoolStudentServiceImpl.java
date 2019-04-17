package com.hxy.nzxy.stexam.school.student.service.impl;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ScoreSchoolDO;
import com.hxy.nzxy.stexam.school.student.dao.ScoreSchoolStudentDao;
import com.hxy.nzxy.stexam.school.student.service.ScoreSchoolStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class ScoreSchoolStudentServiceImpl implements ScoreSchoolStudentService {
	@Autowired
	private ScoreSchoolStudentDao scoreSchoolStudentDao;
	
	@Override
	public ScoreSchoolDO get(Long id){
		return scoreSchoolStudentDao.get(id);
	}
	
	@Override
	public List<ScoreSchoolDO> list(Map<String, Object> map){
		return scoreSchoolStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreSchoolStudentDao.count(map);
	}
	
	@Override
	public int save(ScoreSchoolDO scoreSchoolStudent){
		return scoreSchoolStudentDao.save(scoreSchoolStudent);
	}
	
	@Override
	public int update(ScoreSchoolDO scoreSchoolStudent){
		return scoreSchoolStudentDao.update(scoreSchoolStudent);
	}
	
	@Override
	public int remove(Long id){
		return scoreSchoolStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreSchoolStudentDao.batchRemove(ids);
	}

	@Override
	public List<ScoreSchoolDO> schoolCourseList(Query query) {
		return scoreSchoolStudentDao.schoolCourseList(query);
	}

	@Override
	public int schoolCourseListcount(Query query) {
		return scoreSchoolStudentDao.schoolCourseListcount(query);
	}

	@Override
	public List<ScoreSchoolDO> studentScoreList(Query query) {
		return scoreSchoolStudentDao.studentScoreList(query);
	}

	@Override
	public int studentScoreListcount(Query query) {
		return scoreSchoolStudentDao.studentScoreListcount(query);
	}

}
