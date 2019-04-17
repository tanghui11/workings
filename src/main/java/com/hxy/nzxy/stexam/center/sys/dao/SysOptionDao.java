package com.hxy.nzxy.stexam.center.sys.dao;

import com.hxy.nzxy.stexam.domain.SystemOptionDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 系统选项
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 11:35:19
 */
@Mapper
public interface SysOptionDao {

	SystemOptionDO get(Long id);
	
	List<SystemOptionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SystemOptionDO systemOption);
	
	int update(SystemOptionDO systemOption);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
