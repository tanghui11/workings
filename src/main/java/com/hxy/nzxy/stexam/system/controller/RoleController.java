package com.hxy.nzxy.stexam.system.controller;

import com.hxy.nzxy.stexam.common.annotation.Log;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.controller.BaseController;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.system.domain.AppDO;
import com.hxy.nzxy.stexam.system.domain.RoleDO;
import com.hxy.nzxy.stexam.system.service.RoleService;
import com.hxy.nzxy.stexam.common.annotation.Log;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.controller.BaseController;
import com.hxy.nzxy.stexam.common.utils.R;
import com.hxy.nzxy.stexam.system.domain.RoleDO;
import com.hxy.nzxy.stexam.system.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/system/role")
@Controller
public class RoleController extends SystemBaseController {
    String prefix = "system/role";
    @Autowired
    RoleService roleService;
    @Autowired
    CommonService commonService;

    @RequiresPermissions("system:role:role")
    @GetMapping()
    String role(Model model) {
        model.addAttribute("appList", commonService.listAllApp(null));
        return prefix + "/role";
    }

    @RequiresPermissions("system:role:role")
    @GetMapping("/list")
    @ResponseBody()
    List<RoleDO> list(@RequestParam("appid") String appid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appid", appid);
        List<RoleDO> roles = roleService.list(map);
        for (RoleDO item : roles) {
            item.setStatus(FieldDictUtil.get(getAppName(), "sys_role", "status", item.getStatus()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        return roles;
    }

    @Log("添加角色")
    @RequiresPermissions("system:role:add")
    @GetMapping("/add/{appid}")
    String add(Model model, @PathVariable("appid") String appid) {
        model.addAttribute("appid", appid);
        model.addAttribute("sys_role_status", commonService.listFieldDict(getAppName(), "sys_role", "status"));
        return prefix + "/add";
    }

    @Log("编辑角色")
    @RequiresPermissions("system:role:edit")
    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") String id, Model model) {
        model.addAttribute("sys_role_status", commonService.listFieldDict(getAppName(), "sys_role", "status"));
        RoleDO roleDO = roleService.get(id);
        model.addAttribute("role", roleDO);
        return prefix + "/edit";
    }

    @Log("保存角色")
    @RequiresPermissions("system:role:add")
    @PostMapping("/save")
    @ResponseBody()
    R save(RoleDO role) {
        if (roleService.save(role) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }

    @Log("更新角色")
    @RequiresPermissions("system:role:edit")
    @PostMapping("/update")
    @ResponseBody()
    R update(RoleDO role) {
        if (roleService.update(role) > 0) {
            return R.ok();
        } else {
            return R.error(1, "保存失败");
        }
    }

    @Log("删除角色")
    @RequiresPermissions("system:role:remove")
    @PostMapping("/remove")
    @ResponseBody()
    R save(String id) {
        if (roleService.remove(id) > 0) {
            return R.ok();
        } else {
            return R.error(1, "删除失败");
        }
    }
}
