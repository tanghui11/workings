package com.hxy.nzxy.stexam.region.student.service;

import com.hxy.nzxy.stexam.domain.ScoreChangeLogDO;

import java.util.List;
import java.util.Map;

/**
 * 成绩变更日志表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
public interface ScoreChangeLogRegionService {
	
	ScoreChangeLogDO get(String changeType);
	
	List<ScoreChangeLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreChangeLogDO scoreChangeLogRegion);
	
	int update(ScoreChangeLogDO scoreChangeLogRegion);
	
	int remove(String changeType);
	
	int batchRemove(String[] changeTypes);
}
