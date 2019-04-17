package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.ScoreOldDO;

import java.util.List;
import java.util.Map;

/**
 * 老课程成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
public interface ScoreOldService {
	
	ScoreOldDO get(Long id);
	
	List<ScoreOldDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreOldDO scoreOld);
	
	int update(ScoreOldDO scoreOld);
	int updateScore(Map<String,Object> map);

	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
