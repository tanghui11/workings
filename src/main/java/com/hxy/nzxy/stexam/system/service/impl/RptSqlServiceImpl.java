package com.hxy.nzxy.stexam.system.service.impl;

import com.hxy.nzxy.stexam.system.dao.RptSqlDao;
import com.hxy.nzxy.stexam.system.domain.RptSqlDO;
import com.hxy.nzxy.stexam.system.service.RptSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.system.dao.RptSqlDao;
import com.hxy.nzxy.stexam.system.domain.RptSqlDO;
import com.hxy.nzxy.stexam.system.service.RptSqlService;



@Service
public class RptSqlServiceImpl implements RptSqlService {
	@Autowired
	private RptSqlDao rptSqlDao;
	
	@Override
	public RptSqlDO get(String id){
		return rptSqlDao.get(id);
	}
	
	@Override
	public List<RptSqlDO> list(Map<String, Object> map){
		return rptSqlDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return rptSqlDao.count(map);
	}
	
	@Override
	public int save(RptSqlDO rptSql){
		return rptSqlDao.save(rptSql);
	}
	
	@Override
	public int update(RptSqlDO rptSql){
		return rptSqlDao.update(rptSql);
	}
	
	@Override
	public int remove(String id){
		return rptSqlDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return rptSqlDao.batchRemove(ids);
	}
	
}
