package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.KnowledgePointDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 知识点
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-01-03 09:25:06
 */
@Mapper
public interface KnowledgePointDao {

	KnowledgePointDO get(String id);
	
	List<KnowledgePointDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(KnowledgePointDO knowledgePoint);
	
	int update(KnowledgePointDO knowledgePoint);
	
	int remove(String id);
}
