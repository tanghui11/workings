package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.ScoreInDO;

import java.util.List;
import java.util.Map;

/**
 * 转入成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
public interface ScoreInService {
	
	ScoreInDO get(Long id);
	
	List<ScoreInDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreInDO scoreIn);
	
	int update(ScoreInDO scoreIn);
	
	int audit(Long id);
	
	int batchRemove(Long[] ids);
}
