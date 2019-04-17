package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.DeptWorkerDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 部门职员
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-26 09:58:49
 */
@Mapper
public interface DeptWorkerDao {

	DeptWorkerDO get(Long id);
	
	List<DeptWorkerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeptWorkerDO deptWorker);
	
	int update(DeptWorkerDO deptWorker);
	
	int remove(Long id);

	int removeByWorkerid(String workerid);
}
