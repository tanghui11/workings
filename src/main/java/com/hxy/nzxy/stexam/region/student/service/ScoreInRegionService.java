package com.hxy.nzxy.stexam.region.student.service;

import com.hxy.nzxy.stexam.domain.ScoreInDO;

import java.util.List;
import java.util.Map;

/**
 * 转入成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
public interface ScoreInRegionService {
	
	ScoreInDO get(Long id);
	
	List<ScoreInDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreInDO scoreInRegion);
	
	int update(ScoreInDO scoreInRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
