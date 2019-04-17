package com.hxy.nzxy.stexam.system.service;

import com.hxy.nzxy.stexam.system.domain.DeptWorkerDO;
import com.hxy.nzxy.stexam.system.domain.DeptWorkerDO;

import java.util.List;
import java.util.Map;

/**
 * 部门职员
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-26 09:58:49
 */
public interface DeptWorkerService {
	
	DeptWorkerDO get(Long id);
	
	List<DeptWorkerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeptWorkerDO deptWorker);
	
	int update(DeptWorkerDO deptWorker);

	int remove(Long id);

	int removeByWorkerid(String workerid);
}
