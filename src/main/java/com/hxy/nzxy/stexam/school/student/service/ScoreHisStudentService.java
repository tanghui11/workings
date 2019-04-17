package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.ScoreHisDO;

import java.util.List;
import java.util.Map;

/**
 * 考生成绩表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
public interface ScoreHisStudentService {
	
	ScoreHisDO get(Long id);
	
	List<ScoreHisDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreHisDO scoreHisStudent);
	
	int update(ScoreHisDO scoreHisStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
