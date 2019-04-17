package com.hxy.nzxy.stexam.center.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreChangeLogDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 成绩变更日志表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
@Mapper
public interface ScoreChangeLogDao {

	ScoreChangeLogDO get(String changeType);
	
	List<ScoreChangeLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreChangeLogDO scoreChangeLog);
	
	int update(ScoreChangeLogDO scoreChangeLog);
	
	int remove(String change_type);
	
	int batchRemove(String[] changeTypes);
}
