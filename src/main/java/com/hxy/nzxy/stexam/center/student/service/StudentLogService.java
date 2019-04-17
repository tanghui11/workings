package com.hxy.nzxy.stexam.center.student.service;

import com.hxy.nzxy.stexam.domain.StudentLogDO;

import java.util.List;
import java.util.Map;

/**
 * 考生变更日志
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
public interface StudentLogService {
	
	StudentLogDO get(String changeType);
	
	List<StudentLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudentLogDO studentLog);
	
	int update(StudentLogDO studentLog);
	
	int remove(String changeType);
	
	int batchRemove(String[] changeTypes);
}
