package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.RoleMenuDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 角色与菜单对应关系
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-10-03 11:08:59
 */
@Mapper
public interface RoleMenuDao {

	RoleMenuDO get(Long id);
	
	List<RoleMenuDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(RoleMenuDO roleMenu);
	
	int update(RoleMenuDO roleMenu);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	List<String> listMenuIdByRoleId(Map<String,Object> map);
	
	int removeByRoleId(String roleId);
	
	int batchSave(List<RoleMenuDO> list);
}
