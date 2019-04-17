package com.hxy.nzxy.stexam.center.exam.service.impl;

import com.hxy.nzxy.stexam.center.exam.dao.ExamCourseDao;
import com.hxy.nzxy.stexam.center.exam.dao.ExamCourseResitDao;
import com.hxy.nzxy.stexam.center.exam.dao.TaskExamDao;
import com.hxy.nzxy.stexam.center.exam.service.ExamCourseResitService;
import com.hxy.nzxy.stexam.center.exam.service.ExamCourseService;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ExamCourseDO;
import com.hxy.nzxy.stexam.domain.TaskDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ExamCourseResitServiceImpl implements ExamCourseResitService {
	@Autowired
	private ExamCourseResitDao examCourseDao;
	@Autowired
	private TaskExamDao taskDao;
	@Override
	public ExamCourseDO get(Long id){
		return examCourseDao.get(id);
	}
	
	@Override
	public List<ExamCourseDO> list(Map<String, Object> map){
		return examCourseDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return examCourseDao.count(map);
	}
	
	@Override
	public int save(ExamCourseDO examCourse){
		return examCourseDao.save(examCourse);
	}
	
	@Override
	public int update(ExamCourseDO examCourse){
		return examCourseDao.update(examCourse);
	}
	
	@Override
	public int remove(Long id){
		return examCourseDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return examCourseDao.batchRemove(ids);
	}

	@Override
	public List<ExamCourseDO> listProposition(Query query) {
		return examCourseDao.listProposition(query);
	}

	@Override
	public int countProposition(Query query) {
		return examCourseDao.countProposition(query);
	}

	@Override
	public void updateAllDefault(ExamCourseDO examCourse) {
		examCourseDao.updateAllDefault(examCourse);
	}

	@Override
	public void updateSequence(ExamCourseDO examCourse) {
	List<ExamCourseDO>	list =examCourseDao.listSequence(examCourse);
		examCourseDao.updateSequence(list);
	}

	@Override
	public void updateTask(TaskDO task) {
		taskDao.updateStatus(task);
	}

}
