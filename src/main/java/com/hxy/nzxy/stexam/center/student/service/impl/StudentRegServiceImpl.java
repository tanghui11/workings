package com.hxy.nzxy.stexam.center.student.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.StudentRegDao;
import com.hxy.nzxy.stexam.domain.StudentRegDO;
import com.hxy.nzxy.stexam.center.student.service.StudentRegService;



@Service
public class StudentRegServiceImpl implements StudentRegService {
	@Autowired
	private StudentRegDao studentRegDao;
	
	@Override
	public StudentRegDO get(Long id){
		return studentRegDao.get(id);
	}
	
	@Override
	public List<StudentRegDO> list(Map<String, Object> map){
		return studentRegDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentRegDao.count(map);
	}
	
	@Override
	public int save(StudentRegDO studentReg){
		return studentRegDao.save(studentReg);
	}
	
	@Override
	public int update(StudentRegDO studentReg){
		return studentRegDao.update(studentReg);
	}
	
	@Override
	public int remove(Long id){
		return studentRegDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return studentRegDao.batchRemove(ids);
	}

	@Override
	public int updateAudit(Map<String, Object> params) {
		return studentRegDao.updateAudit(params);
	}

	@Override
	public int batchUpdateAudit(Long[] ids, String auditStatus) {
		Map  map =new HashMap();
		map.put("array",ids);
		map.put("auditStatus",auditStatus);
		return studentRegDao.batchUpdateAudit(map);
	}

}
