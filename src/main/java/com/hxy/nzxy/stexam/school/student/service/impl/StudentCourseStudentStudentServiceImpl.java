package com.hxy.nzxy.stexam.school.student.service.impl;

import com.hxy.nzxy.stexam.center.exam.dao.ExamCourseDao;
import com.hxy.nzxy.stexam.center.region.dao.RegionDao;
import com.hxy.nzxy.stexam.center.school.dao.SchoolSiteDao;
import com.hxy.nzxy.stexam.center.school.dao.TeachSiteDao;
import com.hxy.nzxy.stexam.common.utils.JSONUtils;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.domain.*;
import com.hxy.nzxy.stexam.school.student.dao.StudentCourseStudentDao;
import com.hxy.nzxy.stexam.school.student.dao.StudentCourseStudentStudentDao;
import com.hxy.nzxy.stexam.school.student.service.StudentCourseStudentStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class StudentCourseStudentStudentServiceImpl implements StudentCourseStudentStudentService {
	@Autowired
	private StudentCourseStudentStudentDao studentCourseStudentStudentDao;
	@Autowired
	private TeachSiteDao teachSiteDao;
	@Autowired
	private SchoolSiteDao schoolSiteDao;
	@Autowired
	private RegionDao regionDao;
	@Autowired
	private ExamCourseDao examCourseDao;

	@Override
	public StudentCourseDO get(Long id){
		return studentCourseStudentStudentDao.get(id);
	}
	
	@Override
	public List<StudentCourseDO> list(Map<String, Object> map){
		return studentCourseStudentStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCourseStudentStudentDao.count(map);
	}
	
	@Override
	public int save(StudentCourseDO studentCourseStudent){
		return studentCourseStudentStudentDao.save(studentCourseStudent);
	}
	
	@Override
	public int update(StudentCourseDO studentCourseStudent){
		return studentCourseStudentStudentDao.update(studentCourseStudent);
	}
	
	@Override
	public int remove(Long id){
		return studentCourseStudentStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentCourseStudentStudentDao.batchRemove(ids);
	}

	@Override
	public List<TaskDO> listTask(Map<String, Object> params) {
		return studentCourseStudentStudentDao.listTask(params);
	}

	@Override
	public List<ExamCourseDO> listCourse(Query query) {
		return studentCourseStudentStudentDao.listCourse(query);
	}

	@Override
	public int listCourseCount(Query query) {
		return studentCourseStudentStudentDao.listCourseCount(query);
	}
	@Override
	public int saveCourse(  String[] examCourseid, String childRegionid,String regionid, String studentid) {

		//查询出考试考区编号 考试区县编号 获取地区代码
		StudentCourseDO  studentCourse =new StudentCourseDO();
		studentCourse.setChildRegionid(Long.valueOf(childRegionid));
		studentCourse.setRegionid(Long.valueOf(regionid));
		studentCourse.setType("b");
		studentCourse.setStatus("a");
		studentCourse.setArrangeStatus("a");
		studentCourse.setOperator(ShiroUtils.getUserId().toString());
		studentCourse.setStudentid(studentid);


	for (int i=0;i<examCourseid.length;i++){
		studentCourse.setExamCourseid(Long.valueOf(examCourseid[i]));
		//可以优化批量
			studentCourseStudentStudentDao.save(studentCourse);
	}


		return 1;
	}

}
