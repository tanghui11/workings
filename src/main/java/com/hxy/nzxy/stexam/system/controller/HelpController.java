package com.hxy.nzxy.stexam.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.dao.HelpDao;
import com.hxy.nzxy.stexam.system.domain.MenuDO;
import com.hxy.nzxy.stexam.system.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxy.nzxy.stexam.system.domain.HelpDO;
import com.hxy.nzxy.stexam.system.service.HelpService;
import com.hxy.nzxy.stexam.common.utils.PageUtils;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.common.utils.R;

import javax.websocket.server.PathParam;

/**
 * 帮助文件
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-01-13 16:44:38
 */

@Controller
@RequestMapping("/system/help")
public class HelpController extends SystemBaseController {
    @Autowired
    private HelpService helpService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private MenuService menuService;

    @GetMapping()
    String Help(@PathParam("menuid") String menuid, Model model) {
        model.addAttribute("menuid", menuid);
        return "system/help/help";
    }

    @ResponseBody
    @GetMapping("/list/{menuid}")
    public Map<String, Object> list(@PathVariable("menuid") String menuid, Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("menuid", menuid);
        if (!SecurityUtils.getSubject().isPermitted("system:help:edit")) {
            map.put("status", "b");
        }
        HelpDO helpDO = helpService.get(map);
        Map<String, Object> re = new HashMap<>();
        if (helpDO != null) {
            re.put("code", 0);
            re.put("msg", "查询成功");
            re.put("data", helpDO);
        } else {
            re.put("code", 1);
            re.put("msg", "查询失败");
        }
        return re;
    }

    @GetMapping("/edit/{menuid}")
    @RequiresPermissions("system:help:edit")
    String edit(@PathVariable("menuid") String menuid, Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("menuid", menuid);
        HelpDO help = helpService.get(map);
        model.addAttribute("help", help);
        model.addAttribute("menuid", menuid);
        model.addAttribute("sys_help_status", commonService.listFieldDict(getAppName(), "sys_help", "status"));
        if(help == null) {
            return "system/help/add";
        } else {
            return "system/help/edit";
        }
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:help:edit")
    public R save(HelpDO help) {
        help.setOperator(getUserId());
        Map<String, Object> map = new HashMap<>();
        map.put("menuid", help.getMenuid());
        HelpDO helpDO = helpService.get(map);
        if (helpDO == null) {
            if (helpService.save(help) > 0) {
                return R.ok();
            }
            return R.error();
        } else {
            if (helpService.update(help) > 0) {
                return R.ok();
            }
            return R.error();
        }
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:help:remove")
    public R remove(String menuid) {
        helpService.remove(menuid);
        return R.ok();
    }

    @GetMapping("/tree")
    @ResponseBody
    Tree<MenuDO> tree() {
        if (SecurityUtils.getSubject().isPermitted("system:help:edit")) {
            return menuService.getSysMenuTree(getUserId());
        } else {
            return menuService.listMenuTreeForHelp(getUserId());
        }
    }
}