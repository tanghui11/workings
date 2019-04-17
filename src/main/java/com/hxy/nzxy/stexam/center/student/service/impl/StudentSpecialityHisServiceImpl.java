package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.StudentSpecialityHisDao;
import com.hxy.nzxy.stexam.domain.StudentSpecialityHisDO;
import com.hxy.nzxy.stexam.center.student.service.StudentSpecialityHisService;



@Service
public class StudentSpecialityHisServiceImpl implements StudentSpecialityHisService {
	@Autowired
	private StudentSpecialityHisDao studentSpecialityHisDao;
	
	@Override
	public StudentSpecialityHisDO get(Long id){
		return studentSpecialityHisDao.get(id);
	}
	
	@Override
	public List<StudentSpecialityHisDO> list(Map<String, Object> map){
		return studentSpecialityHisDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentSpecialityHisDao.count(map);
	}
	
	@Override
	public int save(StudentSpecialityHisDO studentSpecialityHis){
		return studentSpecialityHisDao.save(studentSpecialityHis);
	}
	
	@Override
	public int update(StudentSpecialityHisDO studentSpecialityHis){
		return studentSpecialityHisDao.update(studentSpecialityHis);
	}
	
	@Override
	public int remove(Long id){
		return studentSpecialityHisDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentSpecialityHisDao.batchRemove(ids);
	}
	
}
