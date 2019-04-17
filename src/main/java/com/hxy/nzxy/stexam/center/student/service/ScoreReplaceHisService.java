package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.ScoreReplaceHisDO;

import java.util.List;
import java.util.Map;

/**
 * 免考成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
public interface ScoreReplaceHisService {
	
	ScoreReplaceHisDO get(Long id);
	
	List<ScoreReplaceHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreReplaceHisDO scoreReplaceHis);
	
	int update(ScoreReplaceHisDO scoreReplaceHis);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
