package com.hxy.nzxy.stexam.center.sys.service;

import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.domain.ReportDO;
import com.hxy.nzxy.stexam.domain.ReportSqlDO;
import com.hxy.nzxy.stexam.domain.SpecialityRecordDO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报表管理表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-12-11 17:56:42
 */
public interface ReportService {
	
	ReportDO get(Integer id);

	ReportDO getReportInfo(String code);

	List<ReportDO> list(Map<String, Object> map);

	List<ReportSqlDO> getReportSql(int id);

	int count(Map<String, Object> map);
	
	int save(ReportDO report);
	
	int update(ReportDO report);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	int keepInDB(Map<String, Object> map);
	int isInDB(Map<String, Object> map);

	int keepSQL(int id ,String type,String sql,String operator);

	List<Map<String,Object>> runSql(Map<String, Object> map);
	List<ReportDO> menuSelected(String menuType);

    List<SpecialityRecordDO> listKcszyxf(Map<String, Object> map);

	int countKcszyxf(Map<String, Object> map);
}
