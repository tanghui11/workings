package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.StudentCertificateOldDao;
import com.hxy.nzxy.stexam.domain.StudentCertificateOldDO;
import com.hxy.nzxy.stexam.center.student.service.StudentCertificateOldService;



@Service
public class StudentCertificateOldServiceImpl implements StudentCertificateOldService {
	@Autowired
	private StudentCertificateOldDao studentCertificateOldDao;
	
	@Override
	public StudentCertificateOldDO get(Long id){
		return studentCertificateOldDao.get(id);
	}
	
	@Override
	public List<StudentCertificateOldDO> list(Map<String, Object> map){
		return studentCertificateOldDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCertificateOldDao.count(map);
	}
	
	@Override
	public int save(StudentCertificateOldDO studentCertificateOld){
		return studentCertificateOldDao.save(studentCertificateOld);
	}
	
	@Override
	public int update(StudentCertificateOldDO studentCertificateOld){
		return studentCertificateOldDao.update(studentCertificateOld);
	}
	
	@Override
	public int remove(Long id){
		return studentCertificateOldDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentCertificateOldDao.batchRemove(ids);
	}
	
}
