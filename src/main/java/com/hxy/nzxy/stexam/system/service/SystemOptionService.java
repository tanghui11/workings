package com.hxy.nzxy.stexam.system.service;

import com.hxy.nzxy.stexam.system.domain.SystemOptionDO;
import com.hxy.nzxy.stexam.system.domain.SystemOptionDO;

import java.util.List;
import java.util.Map;

/**
 * 系统选项
 * 
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-26 11:21:06
 */
public interface SystemOptionService {
	
	SystemOptionDO get(Map<String, Object> map);
	
	List<SystemOptionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SystemOptionDO systemOption);
	
	int update(SystemOptionDO systemOption);
	
	int remove(Map<String, Object> map);
}
