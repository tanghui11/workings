package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.RoleDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 角色
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-10-02 20:24:47
 */
@Mapper
public interface RoleDao {

	RoleDO get(String roleId);
	
	List<RoleDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(RoleDO role);
	
	int update(RoleDO role);
	
	int remove(String roleId);
}
