package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.KnowPointDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-31 19:21:45
 */
@Mapper
public interface KnowPointDao {

	KnowPointDO get(String id);
	
	List<KnowPointDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(KnowPointDO knowPoint);
	
	int update(KnowPointDO knowPoint);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
