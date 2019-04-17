package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.ScoreReplaceHisDO;

import java.util.List;
import java.util.Map;

/**
 * 免考成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
public interface ScoreReplaceHisStudentService {
	
	ScoreReplaceHisDO get(Long id);
	
	List<ScoreReplaceHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreReplaceHisDO scoreReplaceHisStudent);
	
	int update(ScoreReplaceHisDO scoreReplaceHisStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
