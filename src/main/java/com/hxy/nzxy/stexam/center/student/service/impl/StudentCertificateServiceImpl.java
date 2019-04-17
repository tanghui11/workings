package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.center.student.dao.StudentCertificateDao;
import com.hxy.nzxy.stexam.center.student.service.StudentCertificateService;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentCertificateDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;



@Service
public class StudentCertificateServiceImpl implements StudentCertificateService {
	@Autowired
	private StudentCertificateDao studentCertificateDao;
	
	@Override
	public StudentCertificateDO get(String studentid){
		return studentCertificateDao.get(studentid);
	}
	
	@Override
	public List<StudentCertificateDO> list(Map<String, Object> map){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time1 = "";
		String time2 = "";
		if ("a".equals(map.get("year"))){
			time1 = map.get("time")+"-01-01 00:00:00";
			time2 = map.get("time")+"-06-30 23:59:59";
		}
		if("b".equals(map.get("year"))){
			time1 = map.get("time")+"-07-01 00:00:00";
			time2 = map.get("time")+"-12-31 23:59:59";
		}
		map.put("time1",time1);
		map.put("time2",time2);
		return studentCertificateDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentCertificateDao.count(map);
	}
	
	@Override
	public int save(StudentCertificateDO studentCertificate){
		return studentCertificateDao.save(studentCertificate);
	}
	
	@Override
	public int update(StudentCertificateDO studentCertificate){
		return studentCertificateDao.update(studentCertificate);
	}
	
	@Override
	public int remove(Long id){
		return studentCertificateDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentCertificateDao.batchRemove(ids);
	}

	@Override
	public List<StudentCertificateDO> listDiploma(Map<String, Object> map) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time1 = "";
		String time2 = "";
		if ("a".equals(map.get("year"))){
			time1 = map.get("time")+"-01-01 00:00:00";
			time2 = map.get("time")+"-06-30 23:59:59";
		}
		if("b".equals(map.get("year"))){
			time1 = map.get("time")+"-07-01 00:00:00";
			time2 = map.get("time")+"-12-31 23:59:59";
		}
		map.put("time1",time1);
		map.put("time2",time2);
		return studentCertificateDao.listDiploma(map);
	}

	@Override
	public int countDiploma(Query query) {
		return studentCertificateDao.countDiploma(query);
	}

	@Override
	public String isCode(Long id) {
		return studentCertificateDao.isCode(id);
	}

	@Override
	public List<StudentCertificateDO> initCode() {
		return studentCertificateDao.initCode();
	}

}
