package com.hxy.nzxy.stexam.system.service;

import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.system.domain.DeptDO;
import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.system.domain.DeptDO;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-25 15:15:11
 */
public interface DeptService {
	
	DeptDO get(String id);
	
	List<DeptDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeptDO dept);
	
	int update(DeptDO dept);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<TreeEx<DeptDO>> getTree(Map<String, Object> map);

	boolean checkDeptHasWorker(String deptid);

	List<DeptDO> listDeptForTree(Map<String, Object> map);

	List<DeptDO> verifyChildrenByDeptid(Map<String, Object> map);
}
