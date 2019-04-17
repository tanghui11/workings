package com.hxy.nzxy.stexam.region.student.dao;

import com.hxy.nzxy.stexam.domain.ScoreChangeLogDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 成绩变更日志表
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
@Mapper
public interface ScoreChangeLogRegionDao {

	ScoreChangeLogDO get(String changeType);
	
	List<ScoreChangeLogDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ScoreChangeLogDO scoreChangeLogRegion);
	
	int update(ScoreChangeLogDO scoreChangeLogRegion);
	
	int remove(String change_type);
	
	int batchRemove(String[] changeTypes);
}
