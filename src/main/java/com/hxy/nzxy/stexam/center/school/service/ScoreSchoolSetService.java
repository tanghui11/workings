package com.hxy.nzxy.stexam.center.school.service;

import com.hxy.nzxy.stexam.domain.ScoreSchoolSetDO;

import java.util.List;
import java.util.Map;

/**
 * 校考课程录入设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
public interface ScoreSchoolSetService {
	
	ScoreSchoolSetDO get(Long id);
	
	List<ScoreSchoolSetDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ScoreSchoolSetDO scoreSchoolSet);
	
	int update(ScoreSchoolSetDO scoreSchoolSet);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
