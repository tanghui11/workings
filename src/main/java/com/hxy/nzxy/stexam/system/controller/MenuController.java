package com.hxy.nzxy.stexam.system.controller;

import com.hxy.nzxy.stexam.common.annotation.Log;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.controller.BaseController;
import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.R;
import com.hxy.nzxy.stexam.system.domain.MenuDO;
import com.hxy.nzxy.stexam.system.service.MenuService;
import com.hxy.nzxy.stexam.common.annotation.Log;
import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.R;
import com.hxy.nzxy.stexam.system.domain.MenuDO;
import com.hxy.nzxy.stexam.system.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/system/menu")
@Controller
public class MenuController extends SystemBaseController {
    String prefix = "system/menu";
    @Autowired
    MenuService menuService;
    @Autowired
    private CommonService commonService;

    @RequiresPermissions("system:menu:menu")
    @GetMapping()
    String menu(Model model) {
        model.addAttribute("appList", commonService.listAllApp(null));
        return prefix + "/menu";
    }

    @RequiresPermissions("system:menu:menu")
    @RequestMapping("/list")
    @ResponseBody
    List<MenuDO> list(@RequestParam("appid") String appid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appid", appid);
        List<MenuDO> menus = menuService.list(map);
        return menus;
    }

    @Log("添加菜单")
    @RequiresPermissions("system:menu:add")
    @GetMapping("/add/{appid}/{parentid}")
    String add(Model model, @PathVariable("appid") String appid, @PathVariable("parentid") String pId) {
        model.addAttribute("appid", appid);
        model.addAttribute("parentid", pId);
        if (pId.equals("0")) {
            model.addAttribute("pName", "根目录");
        } else {
            model.addAttribute("pName", menuService.get(pId).getName());
        }
        model.addAttribute("sys_menu_type", commonService.listFieldDict(getAppName(), "sys_menu", "type"));
        model.addAttribute("sys_menu_target", commonService.listFieldDict(getAppName(), "sys_menu", "target"));
        model.addAttribute("sys_menu_status", commonService.listFieldDict(getAppName(), "sys_menu", "status"));
        return prefix + "/add";
    }

    @Log("编辑菜单")
    @RequiresPermissions("system:menu:edit")
    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable("id") String id) {
        MenuDO mdo = menuService.get(id);
        String parentid = mdo.getParentid();
        model.addAttribute("parentid", parentid);
        if (parentid.equals("0")) {
            model.addAttribute("pName", "根目录");
        } else {
            model.addAttribute("pName", menuService.get(parentid).getName());
        }
        model.addAttribute("sys_menu_type", commonService.listFieldDict(getAppName(), "sys_menu", "type"));
        model.addAttribute("sys_menu_target", commonService.listFieldDict(getAppName(), "sys_menu", "target"));
        model.addAttribute("sys_menu_status", commonService.listFieldDict(getAppName(), "sys_menu", "status"));
        model.addAttribute("menu", mdo);
        return prefix + "/edit";
    }

    @Log("保存菜单")
    @RequiresPermissions("system:menu:add")
    @PostMapping("/save")
    @ResponseBody
    R save(MenuDO menu) {
        if (menuService.save(menu) > 0) {
            //如果增加的是菜单，并且选择了自动增加按钮选项，那么自动增加增删改查按钮配置信息
            if (menu.getAutoInsertButton() != null && menu.getAutoInsertButton().equals("on")) {
                MenuDO menuButton = new MenuDO();
                menuButton.setParentid(menu.getId());
                menuButton.setAppid(menu.getAppid());
                menuButton.setStatus(menu.getStatus());
                String[] arrParentPerms = menu.getPerms().split(":");
                String menuPerms = "";
                if (arrParentPerms.length >= 2) {
                    menuPerms = arrParentPerms[0] + ":" + arrParentPerms[1];
                }
                //增加按钮
                menuButton.setName("增加");
                menuButton.setPerms(menuPerms + ":add");
                menuButton.setType(2);
                menuButton.setSeq(menu.getSeq().toString() + "01");
                if (menuService.save(menuButton) <= 0) {
                    return R.error(1, "菜单信息保存成功，添加按钮信息保存失败！");
                }
                //修改按钮
                menuButton.setName("修改");
                menuButton.setPerms(menuPerms + ":edit");
                menuButton.setType(2);
                menuButton.setSeq(menu.getSeq().toString() + "02");
                if (menuService.save(menuButton) <= 0) {
                    return R.error(1, "菜单信息保存成功，修改按钮信息保存失败！");
                }
                //删除按钮
                menuButton.setName("删除");
                menuButton.setPerms(menuPerms + ":remove");
                menuButton.setType(2);
                menuButton.setSeq(menu.getSeq().toString() + "03");
                if (menuService.save(menuButton) <= 0) {
                    return R.error(1, "菜单信息保存成功，删除按钮信息保存失败！");
                }
                //批量删除按钮
                menuButton.setName("批量删除");
                menuButton.setPerms(menuPerms + ":batchRemove");
                menuButton.setType(2);
                menuButton.setSeq(menu.getSeq().toString() + "04");
                if (menuService.save(menuButton) <= 0) {
                    return R.error(1, "菜单信息保存成功，批量删除按钮信息保存失败！");
                }
            }
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }

    @Log("更新菜单")
    @RequiresPermissions("system:menu:edit")
    @PostMapping("/update")
    @ResponseBody
    R update(MenuDO menu) {
        if (menuService.update(menu) > 0) {
            return R.ok();
        } else {
            return R.error(1, "重置失败");
        }
    }

    @Log("删除菜单")
    @RequiresPermissions("system:menu:remove")
    @PostMapping("/remove")
    @ResponseBody
    R remove(String id) {
        MenuDO menu = menuService.get(id);
        if (menu == null) {
            return R.error(1, "没有找到要删除的菜单！");
        }
        Map map = new HashMap();
        map.put("parentid", menu.getId());
        List<MenuDO> list = menuService.list(map);
        if (list.size() > 0) {
            return R.error(1, "该菜单有子功能，不能删除！");
        }
        if (menuService.remove(id) > 0) {
            return R.ok();
        } else {
            return R.error(1, "删除失败");
        }
    }

    @GetMapping("/tree/{appid}")
    @ResponseBody
    Tree<MenuDO> tree(@PathVariable("appid") String appid) {
        Tree<MenuDO> tree = new Tree<MenuDO>();
        tree = menuService.getTree(appid);
        return tree;
    }

    @GetMapping("/tree/{appid}/{roleid}")
    @ResponseBody
    Tree<MenuDO> tree(@PathVariable("appid") String appid, @PathVariable("roleid") String roleid) {
        Tree<MenuDO> tree = new Tree<MenuDO>();
        tree = menuService.getTree(appid, roleid);
        return tree;
    }
}
