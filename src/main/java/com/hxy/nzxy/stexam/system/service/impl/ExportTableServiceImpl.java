package com.hxy.nzxy.stexam.system.service.impl;

import com.hxy.nzxy.stexam.system.dao.ExportTableDao;
import com.hxy.nzxy.stexam.system.domain.ExportTableDO;
import com.hxy.nzxy.stexam.system.service.ExportTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExportTableServiceImpl implements ExportTableService {
	@Autowired
	private ExportTableDao exportTableDao;
	
	@Override
	public ExportTableDO get(Long id){
		return exportTableDao.get(id);
	}
	
	@Override
	public List<ExportTableDO> list(Map<String, Object> map){
		return exportTableDao.list(map);
	}

	@Override
	public List<ExportTableDO> list() {
		return exportTableDao.list();
	}

	@Override
	public List<Map<String, String>> tablelist(String sql) {
		return exportTableDao.tablelist(sql);
	}

	@Override
	public int count(Map<String, Object> map){
		return exportTableDao.count(map);
	}
	
	@Override
	public int save(ExportTableDO exportTable){
		return exportTableDao.save(exportTable);
	}
	
	@Override
	public int update(ExportTableDO exportTable){
		return exportTableDao.update(exportTable);
	}
	
	@Override
	public int remove(Long id){
		return exportTableDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return exportTableDao.batchRemove(ids);
	}
	
}
