package com.hxy.nzxy.stexam.school.student.service.impl;

import com.hxy.nzxy.stexam.center.plan.dao.SpecialityRecordDao;
import com.hxy.nzxy.stexam.center.region.dao.RegionDao;
import com.hxy.nzxy.stexam.center.school.dao.SchoolSiteDao;
import com.hxy.nzxy.stexam.center.school.dao.SchoolSpecialityRegDao;
import com.hxy.nzxy.stexam.center.school.dao.TeachSiteDao;
import com.hxy.nzxy.stexam.domain.*;
import com.sun.org.apache.bcel.internal.generic.I2D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.StudentSpecialityStudentDao;
import com.hxy.nzxy.stexam.school.student.service.StudentSpecialityStudentService;



@Service
public class StudentSpecialityStudentServiceImpl implements StudentSpecialityStudentService {
	@Autowired
	private StudentSpecialityStudentDao studentSpecialityStudentDao;
	@Autowired
	private TeachSiteDao teachSiteDao;
	@Autowired
	private SchoolSiteDao schoolSiteDao;
	@Autowired
	private RegionDao regionDao;
	@Autowired
	private SchoolSpecialityRegDao schoolSpecialityRegDao;
	@Autowired
	private SpecialityRecordDao specialityRecordDao;
	@Override
	public StudentSpecialityDO get(Long id){
		return studentSpecialityStudentDao.get(id);
	}
	
	@Override
	public List<StudentSpecialityDO> list(Map<String, Object> map){
		return studentSpecialityStudentDao.list(map);
	}

	@Override
	public List<ScoreDO> applyExcelScore(String studentid, String specialityRecordid){
		return studentSpecialityStudentDao.applyExcelScore(studentid,specialityRecordid);
	}

	@Override
	public int count(Map<String, Object> map){
		return studentSpecialityStudentDao.count(map);
	}
	
	@Override
	public int save(StudentSpecialityDO studentSpecialityStudent){
		//获取地区代码
		TeachSiteDO teachSiteDO = teachSiteDao.get(studentSpecialityStudent.getTeachSiteid());
		SchoolSiteDO schoolSiteDO = schoolSiteDao.get(teachSiteDO.getSchoolSiteid());
		studentSpecialityStudent.setChildRegionid(schoolSiteDO.getRegionid());
		RegionDO regionDO = regionDao.get(schoolSiteDO.getRegionid());
		studentSpecialityStudent.setRegionid(regionDO.getParentid());
		studentSpecialityStudent.setStatus("a");
		studentSpecialityStudent.setAuditStatus("a");
		studentSpecialityStudent.setGradAuditStatus("a");
		//获取招生年份和季节; school_speciality_regid
		SchoolSpecialityRegDO schoolSpecialityRegDO = schoolSpecialityRegDao.get(studentSpecialityStudent.getSchoolSpecialityRegid());
		//专业编号
		SpecialityRecordDO specialityRecordDO = specialityRecordDao.get(Long.valueOf(studentSpecialityStudent.getSpecialityRecordid()));
		if(specialityRecordDO!=null){
			studentSpecialityStudent.setSpecialityid(specialityRecordDO.getSpecialityid());
		}
		if(schoolSpecialityRegDO!=null){
			studentSpecialityStudent.setRegYear(schoolSpecialityRegDO.getRegYear());
			studentSpecialityStudent.setRegSeason(schoolSpecialityRegDO.getRegSeason());
		}
		return studentSpecialityStudentDao.save(studentSpecialityStudent);
	}
	
	@Override
	public int update(StudentSpecialityDO studentSpecialityStudent){
		//获取招生年份和季节; school_speciality_regid
		SchoolSpecialityRegDO schoolSpecialityRegDO = schoolSpecialityRegDao.get(studentSpecialityStudent.getSchoolSpecialityRegid());
		//专业编号
		SpecialityRecordDO specialityRecordDO = specialityRecordDao.get(Long.valueOf(studentSpecialityStudent.getSpecialityRecordid()));
		if(specialityRecordDO!=null){
			studentSpecialityStudent.setSpecialityid(specialityRecordDO.getSpecialityid());
		}
		if(schoolSpecialityRegDO!=null){
			studentSpecialityStudent.setRegYear(schoolSpecialityRegDO.getRegYear());
			studentSpecialityStudent.setRegSeason(schoolSpecialityRegDO.getRegSeason());
		}
		return studentSpecialityStudentDao.update(studentSpecialityStudent);
	}
	
	@Override
	public int remove(Long id){
		return studentSpecialityStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentSpecialityStudentDao.batchRemove(ids);
	}

	@Override
	public StudentSpecialityDO getByStudentId(String id) {
		return studentSpecialityStudentDao.getByStudentId(id);
	}

}
