package com.hxy.nzxy.stexam.system.service;

import com.hxy.nzxy.stexam.system.domain.ExportTableDO;

import java.util.List;
import java.util.Map;

/**
 * 导出数据表名
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-03-16 16:14:20
 */
public interface ExportTableService {
	
	ExportTableDO get(Long id);
	
	List<ExportTableDO> list(Map<String, Object> map);

	List<ExportTableDO> list( );

	List<Map<String,String>> tablelist(String sql);

	int count(Map<String, Object> map);
	
	int save(ExportTableDO exportTable);
	
	int update(ExportTableDO exportTable);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
