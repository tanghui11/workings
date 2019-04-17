package com.hxy.nzxy.stexam.region.exam.service;

import com.hxy.nzxy.stexam.domain.ExamItemDO;

import java.util.List;
import java.util.Map;

/**
 * 考试项目
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:22
 */
public interface ExamItemRegionService {
	
	ExamItemDO get(Long id);
	
	List<ExamItemDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ExamItemDO examItemRegion);
	
	int update(ExamItemDO examItemRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
