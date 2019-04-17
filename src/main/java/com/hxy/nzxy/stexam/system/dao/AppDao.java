package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.AppDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 应用管理
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-20 09:50:18
 */
@Mapper
public interface AppDao {

	AppDO get(String id);
	
	List<AppDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AppDO app);
	
	int update(AppDO app);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
