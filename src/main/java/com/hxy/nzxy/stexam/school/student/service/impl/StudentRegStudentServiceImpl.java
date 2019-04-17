package com.hxy.nzxy.stexam.school.student.service.impl;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentDO;
import com.hxy.nzxy.stexam.domain.StudentSpecialityDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.StudentRegStudentDao;
import com.hxy.nzxy.stexam.domain.StudentRegDO;
import com.hxy.nzxy.stexam.school.student.service.StudentRegStudentService;



@Service
public class StudentRegStudentServiceImpl implements StudentRegStudentService {
	@Autowired
	private StudentRegStudentDao studentRegStudentDao;
	
	@Override
	public StudentRegDO get(Long id){
		return studentRegStudentDao.get(id);
	}
	
	@Override
	public List<StudentRegDO> list(Map<String, Object> map){
		return studentRegStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentRegStudentDao.count(map);
	}
	
	@Override
	public int save(StudentRegDO studentRegStudent){
		return studentRegStudentDao.save(studentRegStudent);
	}
	
	@Override
	public int update(StudentRegDO studentRegStudent){
		return studentRegStudentDao.update(studentRegStudent);
	}
	
	@Override
	public int remove(Long id){
		return studentRegStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentRegStudentDao.batchRemove(ids);
	}

	@Override
	public List<StudentSpecialityDO> listStudent(Query query) {
		return studentRegStudentDao.listStudent(query);
	}

	@Override
	public int countStudent(Query query) {
		return studentRegStudentDao.countStudent(query);
	}

	@Override
	public int selectYear(StudentRegDO studentReg) {
		return studentRegStudentDao.selectYear(studentReg);
	}

	@Override
	public List<StudentSpecialityDO> listStudentStudent(Query query) {
		return studentRegStudentDao.listStudentStudent(query);
	}

	@Override
	public int countStudentStudent(Query query) {
		return studentRegStudentDao.countStudentStudent(query);
	}

	@Override
	public void batchAuditPass(Long[] ids) {
		studentRegStudentDao.batchAuditPass(ids);
	}

	@Override
	public void batchAuditNoPass(Long[] ids) {
		studentRegStudentDao.batchAuditNoPass(ids);
	}

}
