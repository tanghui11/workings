package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.GradeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 年级管理
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-31 17:52:54
 */
@Mapper
public interface GradeDao {

	GradeDO get(String id);
	
	List<GradeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GradeDO grade);
	
	int update(GradeDO grade);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
