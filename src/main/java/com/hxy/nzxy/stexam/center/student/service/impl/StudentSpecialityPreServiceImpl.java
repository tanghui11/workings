package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.StudentSpecialityPreDao;
import com.hxy.nzxy.stexam.domain.StudentSpecialityPreDO;
import com.hxy.nzxy.stexam.center.student.service.StudentSpecialityPreService;



@Service
public class StudentSpecialityPreServiceImpl implements StudentSpecialityPreService {
	@Autowired
	private StudentSpecialityPreDao studentSpecialityPreDao;
	
	@Override
	public StudentSpecialityPreDO get(Long id){
		return studentSpecialityPreDao.get(id);
	}
	
	@Override
	public List<StudentSpecialityPreDO> list(Map<String, Object> map){
		return studentSpecialityPreDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentSpecialityPreDao.count(map);
	}
	
	@Override
	public int save(StudentSpecialityPreDO studentSpecialityPre){
		return studentSpecialityPreDao.save(studentSpecialityPre);
	}
	
	@Override
	public int update(StudentSpecialityPreDO studentSpecialityPre){
		return studentSpecialityPreDao.update(studentSpecialityPre);
	}
	
	@Override
	public int remove(Long id){
		return studentSpecialityPreDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentSpecialityPreDao.batchRemove(ids);
	}
	
}
