package com.hxy.nzxy.stexam.center.sys.service;

import com.hxy.nzxy.stexam.domain.SystemOptionDO;

import java.util.List;
import java.util.Map;

/**
 * 系统选项
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 11:35:19
 */
public interface SysOptionService {
	
	SystemOptionDO get(Long id);
	
	List<SystemOptionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SystemOptionDO systemOption);
	
	int update(SystemOptionDO systemOption);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
