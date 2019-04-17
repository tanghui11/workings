package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.SchoolScoreDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 平时成绩
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
@Mapper
public interface SchoolScoreDao {

	SchoolScoreDO get(Long id);
	
	List<SchoolScoreDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolScoreDO schoolScore);
	
	int update(SchoolScoreDO schoolScore);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
