package com.hxy.nzxy.stexam.region.exam.service;

import com.hxy.nzxy.stexam.domain.TaskDO;

import java.util.List;
import java.util.Map;

/**
 * 考试任务
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:23
 */
public interface TaskRegionService {
	
	TaskDO get(Long id);
	
	List<TaskDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TaskDO taskRegion);
	
	int update(TaskDO taskRegion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
