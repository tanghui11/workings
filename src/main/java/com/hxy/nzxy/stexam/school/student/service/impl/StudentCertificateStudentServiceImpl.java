package com.hxy.nzxy.stexam.school.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.school.student.dao.StudentCertificateStudentDao;
import com.hxy.nzxy.stexam.domain.StudentCertificateDO;
import com.hxy.nzxy.stexam.school.student.service.StudentCertificateStudentService;



@Service
public class StudentCertificateStudentServiceImpl implements StudentCertificateStudentService {
	@Autowired
	private StudentCertificateStudentDao studentCertificateStudentDao;
	
	@Override
	public StudentCertificateDO get(Long id){
		return studentCertificateStudentDao.get(id);
	}
	
	@Override
	public List<StudentCertificateDO> list(Map<String, Object> map){
		return studentCertificateStudentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCertificateStudentDao.count(map);
	}
	
	@Override
	public int save(StudentCertificateDO studentCertificateStudent){
		return studentCertificateStudentDao.save(studentCertificateStudent);
	}
	
	@Override
	public int update(StudentCertificateDO studentCertificateStudent){
		return studentCertificateStudentDao.update(studentCertificateStudent);
	}
	
	@Override
	public int remove(Long id){
		return studentCertificateStudentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentCertificateStudentDao.batchRemove(ids);
	}
	
}
