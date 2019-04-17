package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.ScoreInHisDO;

import java.util.List;
import java.util.Map;

/**
 * 转入成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
public interface ScoreInHisStudentService {
	
	ScoreInHisDO get(Long id);
	
	List<ScoreInHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreInHisDO scoreInHisStudent);
	
	int update(ScoreInHisDO scoreInHisStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
