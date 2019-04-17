package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.RptSqlDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 报表管理
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-03-21 09:35:51
 */
@Mapper
public interface RptSqlDao {

	RptSqlDO get(String id);
	
	List<RptSqlDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RptSqlDO rptSql);
	
	int update(RptSqlDO rptSql);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
