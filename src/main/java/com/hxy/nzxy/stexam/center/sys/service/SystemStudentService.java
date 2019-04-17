package com.hxy.nzxy.stexam.center.sys.service;

import com.hxy.nzxy.stexam.domain.SystemStudentDO;

import java.util.List;
import java.util.Map;

/**
 * 准考证号表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 14:32:12
 */
public interface SystemStudentService {
	
	SystemStudentDO get(Long id);
	
	List<SystemStudentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SystemStudentDO systemStudent);
	
	int update(SystemStudentDO systemStudent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
