package com.hxy.nzxy.stexam.school.student.service;

import com.hxy.nzxy.stexam.domain.ScoreInDO;

import java.util.List;
import java.util.Map;

/**
 * 转入成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
public interface ScoreInStudentService {
	
	ScoreInDO get(Long id);
	
	List<ScoreInDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreInDO scoreInStudent);
	
	int update(ScoreInDO scoreInStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
