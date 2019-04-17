package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.center.student.dao.StudentChangeDao;
import com.hxy.nzxy.stexam.center.student.service.StudentChangeService;
import com.hxy.nzxy.stexam.domain.StudentChangeDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class StudentChangeServiceImpl implements StudentChangeService {
	@Autowired
	private StudentChangeDao studentChangeDao;
	
	@Override
	public StudentChangeDO get(Long id){
		return studentChangeDao.get(id);
	}
	
	@Override
	public List<StudentChangeDO> list(Map<String, Object> map){
		return studentChangeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentChangeDao.count(map);
	}
	
	@Override
	public int save(StudentChangeDO studentChange){
		return studentChangeDao.save(studentChange);
	}
	
	@Override
	public int update(StudentChangeDO studentChange){
		return studentChangeDao.update(studentChange);
	}
	
	@Override
	public int remove(Long id){
		return studentChangeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentChangeDao.batchRemove(ids);
	}

	@Override
	public int getSpecialityid(Long id) {
		return studentChangeDao.getSpecialityid(id);
	}

}
