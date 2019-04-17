package com.hxy.nzxy.stexam.region.student.service;

import com.hxy.nzxy.stexam.domain.ScoreDO;

import java.util.List;
import java.util.Map;

/**
 * 考生成绩表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
public interface ScoreRegionService {
	
	ScoreDO get(Long id);
	
	List<ScoreDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreDO scoreRegion);
	
	int update(ScoreDO scoreRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
