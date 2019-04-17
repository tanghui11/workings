package com.hxy.nzxy.stexam.region.student.service;

import com.hxy.nzxy.stexam.domain.ScoreOldDO;

import java.util.List;
import java.util.Map;

/**
 * 老课程成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
public interface ScoreOldRegionService {
	
	ScoreOldDO get(Long id);
	
	List<ScoreOldDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreOldDO scoreOldRegion);
	
	int update(ScoreOldDO scoreOldRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
