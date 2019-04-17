package com.hxy.nzxy.stexam.region.student.service;

import com.hxy.nzxy.stexam.domain.StudentLogDO;

import java.util.List;
import java.util.Map;

/**
 * 考生变更日志
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:14
 */
public interface StudentLogRegionService {
	
	StudentLogDO get(String changeType);
	
	List<StudentLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentLogDO studentLogRegion);
	
	int update(StudentLogDO studentLogRegion);
	
	int remove(String changeType);
	
	int batchRemove(String[] changeTypes);
}
