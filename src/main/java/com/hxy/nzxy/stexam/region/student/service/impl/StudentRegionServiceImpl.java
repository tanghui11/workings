package com.hxy.nzxy.stexam.region.student.service.impl;

import com.hxy.nzxy.stexam.center.exam.dao.TaskExamDao;
import com.hxy.nzxy.stexam.center.plan.dao.SpecialityRecordDao;

import com.hxy.nzxy.stexam.center.student.dao.StudentDao;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.*;
import com.hxy.nzxy.stexam.region.student.dao.StudentCourseRegionDao;
import com.hxy.nzxy.stexam.region.student.dao.StudentSpecialityRegionDao;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentRegionDao;
import com.hxy.nzxy.stexam.region.student.service.StudentRegionService;
import org.springframework.web.multipart.MultipartFile;


@Service
public class StudentRegionServiceImpl implements StudentRegionService {
	@Autowired
	private StudentRegionDao studentRegionDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private StudentSpecialityRegionDao studentSpecialityRegionDao;
	@Autowired
	private StudentCourseRegionDao studentCourseRegionDao;
	@Autowired
	private SpecialityRecordDao specialityRecordDao;
	@Autowired
	private TaskExamDao taskExamDao;
	@Override
	public StudentDO get(String id){
		return studentRegionDao.get(id);
	}
	
	@Override
	public List<StudentDO> list(Map<String, Object> map){
		return studentRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentRegionDao.count(map);
	}
	
	@Override
	public int save(StudentDO studentRegion, StudentSpecialityDO studentSpeciality){
		//在籍
		studentRegion.setId(studentSpeciality.getStudentid());
		studentRegion.setStatus("a");
		//待审核
		studentRegion.setAuditStatus("b");
		//确认状态待确认
		studentRegion.setConfirmStatus("a");
		studentRegion.setType("a");
		studentRegion.setClassify("c");
		studentRegion.setKjh(studentRegion.getId().substring(4,7));
		//学生基本信息
		studentRegionDao.save(studentRegion);


		//获取招生年份和季节;
		//SchoolSpecialityRegDO schoolSpecialityRegDO = schoolSpecialityRegDao.get(studentSpeciality.getSchoolSpecialityRegid());
		//专业编号
		SpecialityRecordDO specialityRecordDO = specialityRecordDao.get(Long.valueOf(studentSpeciality.getSpecialityRecordid()));
		if(specialityRecordDO!=null){
			studentSpeciality.setSpecialityid(specialityRecordDO.getSpecialityid());
		}
		/*if(schoolSpecialityRegDO!=null){
			studentSpeciality.setRegYear(schoolSpecialityRegDO.getRegYear());
			studentSpeciality.setRegSeason(schoolSpecialityRegDO.getRegSeason());
		}*/
		//报考信息
		studentSpeciality.setRegionid(studentRegion.getRegionid());
		studentSpeciality.setChildRegionid(studentRegion.getChildRegionid());
		studentSpeciality.setStatus("a");
		studentSpeciality.setAuditStatus("b");
		studentSpeciality.setGradAuditStatus("a");
		studentSpeciality.setStudentid(studentRegion.getId());
		studentSpecialityRegionDao.save(studentSpeciality);
		return 1;
	}
	
	@Override
	public int update(StudentDO studentRegion, StudentSpecialityDO studentSpeciality){

		//学生基本信息
		studentRegionDao.update(studentRegion);
		//报考信息
		studentSpeciality.setChildRegionid(studentRegion.getChildRegionid());

		studentRegionDao.updateStuStudentSpeciality(studentSpeciality);
		return 1;
	}
	
	@Override
	public int remove(String id){
		return studentRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return studentRegionDao.batchRemove(ids);
	}

	@Override
	public StudentSpecialityDO getByStudentId(String id) {
		return studentRegionDao.getByStudentId(id);
	}

	@Override
	public List<CourseDO> examTaskList(Map<String, Object> params) {
		TaskDO taskDO=	taskExamDao.getLastTask(params);
		params.put("examTaskid",taskDO.getId());
		return studentRegionDao.examTaskList(params);
	}

    @Override
    public int getEnexamCount(Query query) {
        return studentRegionDao.getEnexamCount(query);
    }

    @Override
    public int saveexam(StudentCourseDO studentCourse) {
		studentCourseRegionDao.save(studentCourse);
        return 1;
    }

    @Override
    public List<SpecialityCourseDO> getids(String specialityRecordid) {
        return studentRegionDao.getids(specialityRecordid);
    }
	@Override
	public   TaskDO getLastTask(Map<String, Object> map){
		TaskDO taskDO=	taskExamDao.getLastTask(map);
		return	taskDO;
	}





}
