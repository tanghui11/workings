package com.hxy.nzxy.stexam.system.service.impl;

import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.utils.BuildTree;
import com.hxy.nzxy.stexam.system.dao.MenuDao;
import com.hxy.nzxy.stexam.system.dao.RoleMenuDao;
import com.hxy.nzxy.stexam.system.domain.MenuDO;
import com.hxy.nzxy.stexam.system.service.MenuService;
import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.utils.BuildTree;
import com.hxy.nzxy.stexam.system.dao.MenuDao;
import com.hxy.nzxy.stexam.system.dao.RoleMenuDao;
import com.hxy.nzxy.stexam.system.domain.MenuDO;
import com.hxy.nzxy.stexam.system.service.MenuService;
import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.utils.BuildTree;
import com.hxy.nzxy.stexam.system.dao.MenuDao;
import com.hxy.nzxy.stexam.system.dao.RoleMenuDao;
import com.hxy.nzxy.stexam.system.domain.MenuDO;
import com.hxy.nzxy.stexam.system.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SuppressWarnings("AlibabaRemoveCommentedCode")
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuDao menuMapper;
    @Autowired
    RoleMenuDao roleMenuMapper;
    @Value("${edu-system.sub-name}")
    private String eduSystemSubName;

    /**
     * @param
     * @return 树形菜单
     */
    @Override
    public Tree<MenuDO> getSysMenuTree(String id) {
        List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
        List<MenuDO> menuDOs = menuMapper.listMenu(id);
        for (MenuDO sysMenuDO : menuDOs) {
            Tree<MenuDO> tree = new Tree<MenuDO>();
            tree.setId(sysMenuDO.getId().toString());
            tree.setParentId(sysMenuDO.getParentid().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysMenuDO.getUrl());
            attributes.put("icon", sysMenuDO.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<MenuDO> t = BuildTree.build(trees, eduSystemSubName);
        return t;
    }

    @Override
    public List<MenuDO> list(Map<String, Object> map) {
        List<MenuDO> menus = menuMapper.list(map);
        return menus;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int remove(String id) {
        int result = menuMapper.remove(id);
        return result;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int save(MenuDO menu) {
        int r = menuMapper.save(menu);
        return r;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int update(MenuDO menu) {
        int r = menuMapper.update(menu);
        return r;
    }

    @Override
    public MenuDO get(String id) {
        MenuDO menuDO = menuMapper.get(id);
        return menuDO;
    }

    @Override
    public Tree<MenuDO> getTree(String appid) {
        List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appid", appid);
        List<MenuDO> menuDOs = menuMapper.list(map);
        for (MenuDO sysMenuDO : menuDOs) {
            Tree<MenuDO> tree = new Tree<MenuDO>();
            tree.setId(sysMenuDO.getId().toString());
            tree.setParentId(sysMenuDO.getParentid().toString());
            tree.setText(sysMenuDO.getName());
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<MenuDO> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Tree<MenuDO> getTree(String appid, String roleid) {
        Map<String, Object> mapMenu = new HashMap<String, Object>();
        mapMenu.put("appid", appid);
        // 根据roleId查询权限
        List<MenuDO> menus = menuMapper.list(mapMenu);
        Map<String, Object> mapRoleMenu = new HashMap<String, Object>();
        mapRoleMenu.put("appid", appid);
        mapRoleMenu.put("roleid", roleid);
        List<String> menuIds = roleMenuMapper.listMenuIdByRoleId(mapRoleMenu);
        List<String> temp = menuIds;
        for (MenuDO menu : menus) {
            if (temp.contains(menu.getParentid())) {
                menuIds.remove(menu.getParentid());
            }
        }
        List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
        List<MenuDO> menuDOs = menuMapper.list(mapMenu);
        for (MenuDO sysMenuDO : menuDOs) {
            Tree<MenuDO> tree = new Tree<MenuDO>();
            tree.setId(sysMenuDO.getId().toString());
            tree.setParentId(sysMenuDO.getParentid().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> state = new HashMap<>(16);
            String menuId = sysMenuDO.getId();
            if (menuIds.contains(menuId.toString())) {
                state.put("selected", true);
            } else {
                state.put("selected", false);
            }
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<MenuDO> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Set<String> listPerms(String userId) {
        List<String> perms = menuMapper.listUserPerms(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<Tree<MenuDO>> listMenuTree(String id) {
        List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
        List<MenuDO> menuDOs = menuMapper.listMenuByUserId(id);
        for (MenuDO sysMenuDO : menuDOs) {
            Tree<MenuDO> tree = new Tree<MenuDO>();
            tree.setId(sysMenuDO.getId().toString());
            tree.setParentId(sysMenuDO.getParentid().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysMenuDO.getUrl());
            attributes.put("icon", sysMenuDO.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<MenuDO>> list = BuildTree.buildList(trees, "0");
        return list;
    }

    @Override
    public Tree<MenuDO> listMenuTreeForHelp(String id) {
        List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
        List<MenuDO> menuDOs = menuMapper.listMenuTreeForHelp(id);
        for (MenuDO sysMenuDO : menuDOs) {
            Tree<MenuDO> tree = new Tree<MenuDO>();
            tree.setId(sysMenuDO.getId().toString());
            tree.setParentId(sysMenuDO.getParentid().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysMenuDO.getUrl());
            attributes.put("icon", sysMenuDO.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<MenuDO> list = BuildTree.build(trees, eduSystemSubName);
        return list;
    }
}
