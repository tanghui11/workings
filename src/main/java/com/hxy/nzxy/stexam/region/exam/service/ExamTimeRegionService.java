package com.hxy.nzxy.stexam.region.exam.service;

import com.hxy.nzxy.stexam.domain.ExamTimeDO;

import java.util.List;
import java.util.Map;

/**
 * 考试时间
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:22
 */
public interface ExamTimeRegionService {
	
	ExamTimeDO get(Long id);
	
	List<ExamTimeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamTimeDO examTimeRegion);
	
	int update(ExamTimeDO examTimeRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
