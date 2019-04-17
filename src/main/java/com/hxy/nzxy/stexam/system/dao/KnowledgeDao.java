package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.KnowledgeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 考试知识
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-01-03 09:25:05
 */
@Mapper
public interface KnowledgeDao {

	KnowledgeDO get(String id);
	
	List<KnowledgeDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(KnowledgeDO knowledge);
	
	int update(KnowledgeDO knowledge);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
