package com.hxy.nzxy.stexam.region.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.region.student.dao.StudentCertificateOldRegionDao;
import com.hxy.nzxy.stexam.domain.StudentCertificateOldDO;
import com.hxy.nzxy.stexam.region.student.service.StudentCertificateOldRegionService;



@Service
public class StudentCertificateOldRegionServiceImpl implements StudentCertificateOldRegionService {
	@Autowired
	private StudentCertificateOldRegionDao studentCertificateOldRegionDao;
	
	@Override
	public StudentCertificateOldDO get(Long id){
		return studentCertificateOldRegionDao.get(id);
	}
	
	@Override
	public List<StudentCertificateOldDO> list(Map<String, Object> map){
		return studentCertificateOldRegionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCertificateOldRegionDao.count(map);
	}
	
	@Override
	public int save(StudentCertificateOldDO studentCertificateOldRegion){
		return studentCertificateOldRegionDao.save(studentCertificateOldRegion);
	}
	
	@Override
	public int update(StudentCertificateOldDO studentCertificateOldRegion){
		return studentCertificateOldRegionDao.update(studentCertificateOldRegion);
	}
	
	@Override
	public int remove(Long id){
		return studentCertificateOldRegionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentCertificateOldRegionDao.batchRemove(ids);
	}
	
}
