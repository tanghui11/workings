package com.hxy.nzxy.stexam.center.exam.service.impl;

import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.exam.dao.SecretOfficeDao;
import com.hxy.nzxy.stexam.domain.SecretOfficeDO;
import com.hxy.nzxy.stexam.center.exam.service.SecretOfficeService;



@Service
public class SecretOfficeServiceImpl implements SecretOfficeService {
	@Autowired
	private SecretOfficeDao secretOfficeDao;
	
	@Override
	public SecretOfficeDO get(Long id){
		return secretOfficeDao.get(id);
	}
	
	@Override
	public List<SecretOfficeDO> list(Map<String, Object> map){
		return secretOfficeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return secretOfficeDao.count(map);
	}
	
	@Override
	public int save(SecretOfficeDO secretOffice){
		return secretOfficeDao.save(secretOffice);
	}
	
	@Override
	public int update(SecretOfficeDO secretOffice){
		return secretOfficeDao.update(secretOffice);
	}
	
	@Override
	public int remove(Long id){
		return secretOfficeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return secretOfficeDao.batchRemove(ids);
	}

	@Override
	public void batchAudit(Long[] ids) {
		Map map=new HashMap();
		map.put("ids",ids);
		map.put("auditOperator",ShiroUtils.getUserId().toString());
		secretOfficeDao.batchAudit(map);
	}

	@Override
	public void batchAuditNo(Long[] ids) {
		Map map=new HashMap();
		map.put("ids",ids);
		map.put("auditOperator",ShiroUtils.getUserId().toString());
		secretOfficeDao.batchAuditNo(map);
	}

}
