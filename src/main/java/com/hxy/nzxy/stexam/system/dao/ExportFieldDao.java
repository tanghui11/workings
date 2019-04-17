package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.ExportFieldDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 导出数据字段
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-03-16 17:39:29
 */
@Mapper
public interface ExportFieldDao {

	ExportFieldDO get(Long id);
	
	List<ExportFieldDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExportFieldDO exportField);
	
	int update(ExportFieldDO exportField);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
