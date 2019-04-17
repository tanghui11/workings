package com.hxy.nzxy.stexam.center.sys.dao;

import com.hxy.nzxy.stexam.domain.ReportSqlDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 报表sql表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-12-11 17:56:42
 */
@Mapper
public interface ReportSqlDao {

	ReportSqlDO get(Integer id);
	
	List<ReportSqlDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ReportSqlDO reportSql);
	
	int update(ReportSqlDO reportSql);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
