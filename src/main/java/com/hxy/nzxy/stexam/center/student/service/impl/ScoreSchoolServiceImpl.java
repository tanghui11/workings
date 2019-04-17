package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.center.student.dao.ScoreSchoolDao;
import com.hxy.nzxy.stexam.center.student.service.ScoreSchoolService;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ScoreSchoolDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class ScoreSchoolServiceImpl implements ScoreSchoolService {
	@Autowired
	private ScoreSchoolDao scoreSchoolDao;
	
	@Override
	public ScoreSchoolDO get(Long id){
		return scoreSchoolDao.get(id);
	}
	
	@Override
	public List<ScoreSchoolDO> list(Map<String, Object> map){
		return scoreSchoolDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scoreSchoolDao.count(map);
	}
	
	@Override
	public int save(ScoreSchoolDO scoreSchool){
		return scoreSchoolDao.save(scoreSchool);
	}
	
	@Override
	public int update(ScoreSchoolDO scoreSchool){
		return scoreSchoolDao.update(scoreSchool);
	}
	
	@Override
	public int remove(Long id){
		return scoreSchoolDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return scoreSchoolDao.batchRemove(ids);
	}

	@Override
	public List<ScoreSchoolDO> studentScoreList(Query query) {
		return scoreSchoolDao.studentScoreList(query);
	}

	@Override
	public int studentScoreListcount(Query query) {
		return scoreSchoolDao.studentScoreListcount(query);
	}

	@Override
	public List<ScoreSchoolDO> schoolCourseList(Query query) {
		return scoreSchoolDao.schoolCourseList(query);
	}

	@Override
	public int schoolCourseListcount(Query query) {
		return scoreSchoolDao.schoolCourseListcount(query);
	}

}
