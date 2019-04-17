package com.hxy.nzxy.stexam.region.student.service;

import com.hxy.nzxy.stexam.domain.ScoreInHisDO;

import java.util.List;
import java.util.Map;

/**
 * 转入成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
public interface ScoreInHisRegionService {
	
	ScoreInHisDO get(Long id);
	
	List<ScoreInHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreInHisDO scoreInHisRegion);
	
	int update(ScoreInHisDO scoreInHisRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
