package com.hxy.nzxy.stexam.center.student.service.impl;

import com.hxy.nzxy.stexam.center.student.dao.StudentInDao;
import com.hxy.nzxy.stexam.center.student.service.StudentInService;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.StudentInDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class StudentInServiceImpl implements StudentInService {
	@Autowired
	private StudentInDao studentInDao;
	
	@Override
	public StudentInDO get(String id){
		return studentInDao.get(id);
	}
	
	@Override
	public List<StudentInDO> list(Map<String, Object> map){
		return studentInDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studentInDao.count(map);
	}
	
	@Override
	public int save(StudentInDO studentIn){
		return studentInDao.save(studentIn);
	}
	
	@Override
	public int update(StudentInDO studentIn){
		return studentInDao.update(studentIn);
	}
	
	@Override
	public int remove(Long id){
		return studentInDao.remove(id);
	}
	@Override
	public int batchRemove(Long[] ids){
		return studentInDao.batchRemove(ids);
	}

	//取消调档
	@Override
	public int cancelStatus(Long id){
		return studentInDao.cancelStatus(id);
	}

	@Override
	public int migration(Long id) {
		return studentInDao.migration(id);
	}

	//审核
	@Override
	public int auditStatus(Long id) {
		return studentInDao.auditStatus(id);
	}

	@Override
	public int cancelAuditStatus(Long id) {
		return studentInDao.cancelAuditStatus(id);
	}

	@Override
	public List<StudentInDO> migrationList(Query query) {
		return studentInDao.migrationList(query);
	}

	//调档
	@Override
	public int migrationCount(Query query) {
		return studentInDao.migrationCount(query);
	}

}