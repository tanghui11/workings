package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.ExportTableDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 导出数据表名
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-03-16 16:14:20
 */
@Mapper
public interface ExportTableDao {

	ExportTableDO get(Long id);
	
	List<ExportTableDO> list(Map<String, Object> map);

	List<Map<String,String>>  tablelist(String sql);

	List<ExportTableDO> list( );

	int count(Map<String, Object> map);
	
	int save(ExportTableDO exportTable);
	
	int update(ExportTableDO exportTable);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
