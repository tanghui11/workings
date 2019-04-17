package com.hxy.nzxy.stexam.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.system.domain.MenuDO;
import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.system.domain.MenuDO;
import org.springframework.stereotype.Service;

import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.system.domain.MenuDO;

@Service
public interface MenuService {
    Tree<MenuDO> getSysMenuTree(String id);

    List<Tree<MenuDO>> listMenuTree(String id);

    Tree<MenuDO> getTree(String appid);

    Tree<MenuDO> getTree(String appid, String roleid);

    List<MenuDO> list(Map<String, Object> map);

    int remove(String id);

    int save(MenuDO menu);

    int update(MenuDO menu);

    MenuDO get(String id);

    Set<String> listPerms(String userId);

    Tree<MenuDO> listMenuTreeForHelp(String id);
}
