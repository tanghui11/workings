package com.hxy.nzxy.stexam.center.sys.service.impl;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ReportSqlDO;
import com.hxy.nzxy.stexam.domain.SpecialityRecordDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.sys.dao.ReportDao;
import com.hxy.nzxy.stexam.domain.ReportDO;
import com.hxy.nzxy.stexam.center.sys.service.ReportService;



@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportDao reportDao;
	
	@Override
	public ReportDO get(Integer id){
		return reportDao.get(id);
	}

	@Override
	public ReportDO getReportInfo(String code){
		return reportDao.getReportInfo(code);
	}

	@Override
	public List<ReportDO> list(Map<String, Object> map){
		return reportDao.list(map);
	}

	@Override
	public List<ReportSqlDO> getReportSql(int id){
		return reportDao.getReportSql(id);
	}

	@Override
	public int keepInDB(Map<String, Object> map){
		return reportDao.keepInDB(map);
	}

	@Override
	public int isInDB(Map<String, Object> map){
		return reportDao.isInDB(map);
	}

	@Override
	public int keepSQL(int id,String type,String sql, String operator){
		return reportDao.keepSQL(id,type,sql,operator);
	}

	@Override
	public List<Map<String,Object>> runSql(Map<String, Object> map){
		return reportDao.runSql(map);
	}

	@Override
	public List<ReportDO> menuSelected(String menuType){
		return reportDao.menuSelected(menuType);
	}

	@Override
	public List<SpecialityRecordDO> listKcszyxf(Map<String, Object> map) {
		return reportDao.listKcszyxf(map);
	}

	@Override
	public int countKcszyxf(Map<String, Object> map) {
		return reportDao.countKcszyxf(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return reportDao.count(map);
	}
	
	@Override
	public int save(ReportDO report){
		return reportDao.save(report);
	}
	
	@Override
	public int update(ReportDO report){
		return reportDao.update(report);
	}
	
	@Override
	public int remove(Integer id){
		return reportDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return reportDao.batchRemove(ids);
	}
	
}
