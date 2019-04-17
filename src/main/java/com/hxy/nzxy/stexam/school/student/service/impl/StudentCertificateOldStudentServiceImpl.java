package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.StudentCertificateOldStudentDao;
import com.hxy.nzxy.stexam.domain.StudentCertificateOldDO;
import com.hxy.nzxy.stexam.school.student.service.StudentCertificateOldStudentService;



@Service
public class StudentCertificateOldStudentServiceImpl implements StudentCertificateOldStudentService {
	@Autowired
	private StudentCertificateOldStudentDao studentCertificateOldStudentDao;
	
	@Override
	public StudentCertificateOldDO get(Long id){
		return studentCertificateOldStudentDao.get(id);
	}
	
	@Override
	public List<StudentCertificateOldDO> list(Map<String, Object> map){
		return studentCertificateOldStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCertificateOldStudentDao.count(map);
	}
	
	@Override
	public int save(StudentCertificateOldDO studentCertificateOldStudent){
		return studentCertificateOldStudentDao.save(studentCertificateOldStudent);
	}
	
	@Override
	public int update(StudentCertificateOldDO studentCertificateOldStudent){
		return studentCertificateOldStudentDao.update(studentCertificateOldStudent);
	}
	
	@Override
	public int remove(Long id){
		return studentCertificateOldStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentCertificateOldStudentDao.batchRemove(ids);
	}
	
}
