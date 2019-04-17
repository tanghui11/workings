package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentCertificateRegionDao;
import com.hxy.nzxy.stexam.domain.StudentCertificateDO;
import com.hxy.nzxy.stexam.region.student.service.StudentCertificateRegionService;



@Service
public class StudentCertificateRegionServiceImpl implements StudentCertificateRegionService {
	@Autowired
	private StudentCertificateRegionDao studentCertificateRegionDao;
	
	@Override
	public StudentCertificateDO get(Long id){
		return studentCertificateRegionDao.get(id);
	}
	
	@Override
	public List<StudentCertificateDO> list(Map<String, Object> map){
		return studentCertificateRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCertificateRegionDao.count(map);
	}
	
	@Override
	public int save(StudentCertificateDO studentCertificateRegion){
		return studentCertificateRegionDao.save(studentCertificateRegion);
	}
	
	@Override
	public int update(StudentCertificateDO studentCertificateRegion){
		return studentCertificateRegionDao.update(studentCertificateRegion);
	}
	
	@Override
	public int remove(Long id){
		return studentCertificateRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentCertificateRegionDao.batchRemove(ids);
	}
	
}
