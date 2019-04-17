package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.ScoreOldHisDO;

import java.util.List;
import java.util.Map;

/**
 * 老课程成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
public interface ScoreOldHisService {
	
	ScoreOldHisDO get(Long id);
	
	List<ScoreOldHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreOldHisDO scoreOldHis);
	
	int update(ScoreOldHisDO scoreOldHis);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
