package com.hxy.nzxy.stexam.center.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.sys.dao.ReportSqlDao;
import com.hxy.nzxy.stexam.domain.ReportSqlDO;
import com.hxy.nzxy.stexam.center.sys.service.ReportSqlService;



@Service
public class ReportSqlServiceImpl implements ReportSqlService {
	@Autowired
	private ReportSqlDao reportSqlDao;
	
	@Override
	public ReportSqlDO get(Integer id){
		return reportSqlDao.get(id);
	}
	
	@Override
	public List<ReportSqlDO> list(Map<String, Object> map){
		return reportSqlDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return reportSqlDao.count(map);
	}
	
	@Override
	public int save(ReportSqlDO reportSql){
		return reportSqlDao.save(reportSql);
	}
	
	@Override
	public int update(ReportSqlDO reportSql){
		return reportSqlDao.update(reportSql);
	}
	
	@Override
	public int remove(Integer id){
		return reportSqlDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return reportSqlDao.batchRemove(ids);
	}
	
}
