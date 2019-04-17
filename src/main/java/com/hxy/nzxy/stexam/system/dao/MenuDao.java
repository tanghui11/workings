package com.hxy.nzxy.stexam.system.dao;

import com.hxy.nzxy.stexam.system.domain.MenuDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单管理
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-10-03 09:45:09
 */
@Mapper
public interface MenuDao {

	MenuDO get(String menuId);
	
	List<MenuDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(MenuDO menu);
	
	int update(MenuDO menu);
	
	int remove(String menuId);

	List<MenuDO> listMenu(String userid);

	List<MenuDO> listMenuByUserId(String id);

	List<String> listUserPerms(String id);

	List<MenuDO> listMenuTreeForHelp(String id);
}
