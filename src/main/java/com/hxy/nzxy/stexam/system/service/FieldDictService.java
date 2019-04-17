package com.hxy.nzxy.stexam.system.service;

import com.hxy.nzxy.stexam.common.domain.CommonDO;
import com.hxy.nzxy.stexam.system.domain.FieldDictDO;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-20 14:57:46
 */
public interface FieldDictService {
	
	FieldDictDO get(Map<String, Object> map);
	
	List<FieldDictDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FieldDictDO fieldDict);
	
	int update(FieldDictDO fieldDict);
	
	int remove(Map<String, Object> map);

	List<CommonDO> listAllTables(Map<String, Object> map);

	List<CommonDO> listAllFields(Map<String, Object> map);

	int saveCache(FieldDictDO fieldDict);

	int updateCache(FieldDictDO fieldDict);

	int removeCache(FieldDictDO fieldDict);


	int batchremoveCache(FieldDictDO fieldDict, String[] ids);
}
