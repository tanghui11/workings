package com.hxy.nzxy.stexam.center.school.service;

import com.hxy.nzxy.stexam.domain.SchoolScoreRatioDO;

import java.util.List;
import java.util.Map;

/**
 * 平时成绩比例设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
public interface SchoolScoreRatioService {
	
	SchoolScoreRatioDO get(Long id);
	
	List<SchoolScoreRatioDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolScoreRatioDO schoolScoreRatio);
	
	int update(SchoolScoreRatioDO schoolScoreRatio);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
