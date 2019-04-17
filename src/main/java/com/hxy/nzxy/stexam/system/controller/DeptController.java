package com.hxy.nzxy.stexam.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.system.domain.DeptWorkerDO;
import com.hxy.nzxy.stexam.system.domain.OrgDO;
import com.hxy.nzxy.stexam.system.service.DeptWorkerService;
import com.hxy.nzxy.stexam.system.service.OrgService;
import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.common.utils.DateUtils;
import com.hxy.nzxy.stexam.common.utils.PinYinUtil;
import com.hxy.nzxy.stexam.common.utils.R;
import com.hxy.nzxy.stexam.common.utils.UserUtil;
import com.hxy.nzxy.stexam.system.domain.DeptDO;
import com.hxy.nzxy.stexam.system.domain.DeptWorkerDO;
import com.hxy.nzxy.stexam.system.service.DeptService;
import com.hxy.nzxy.stexam.system.service.DeptWorkerService;
import com.hxy.nzxy.stexam.system.service.OrgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

import com.hxy.nzxy.stexam.system.domain.DeptDO;
import com.hxy.nzxy.stexam.system.service.DeptService;

import javax.websocket.server.PathParam;

/**
 * 部门管理
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-25 15:15:11
 */

@Controller
@RequestMapping("/system/dept")
public class DeptController extends SystemBaseController {
    private String prefix = "system/dept";
    @Autowired
    private DeptService deptService;
    @Autowired
    private OrgService orgService;

    @Autowired
    private DeptWorkerService deptWorkerService;

    @GetMapping()
    @RequiresPermissions("system:dept:dept")
    String Dept(Model model) {
        model.addAttribute("org",orgService.get(getOrgId()));
        return "system/dept/dept";
    }

    @ResponseBody
    @GetMapping("/list/{orgid}")
    @RequiresPermissions("system:dept:dept")
    public List<DeptDO> list(@PathVariable("orgid") String orgid) {
        //查询列表数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orgid", orgid);
        List<DeptDO> deptList = deptService.list(map);
        for (DeptDO item : deptList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        return deptList;
    }

    @GetMapping("/add/{orgid}")
    @RequiresPermissions("system:dept:add")
    String add(Model model, @PathVariable("orgid") String orgid) {
        model.addAttribute("org", orgService.get(orgid.toString()));
        return "system/dept/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:dept:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        DeptDO dept = deptService.get(id.toString());
        model.addAttribute("dept", dept);
        model.addAttribute("parentName", deptService.get(dept.getParentid()).getName());
        return "system/dept/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:dept:add")
    public R save(DeptDO dept) {
        dept.setPinyin(PinYinUtil.getFirstSpell(dept.getName()));
        dept.setOperator(getUserId().toString());
        if (deptService.save(dept) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:dept:edit")
    public R update(DeptDO dept) {
        dept.setPinyin(PinYinUtil.getFirstSpell(dept.getName()));
        dept.setOperator(getUserId().toString());
        deptService.update(dept);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:dept:remove")
    public R remove(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentid", id);
        List<DeptDO> children = deptService.list(map);
        if (children.size() != 0) {
            return R.error("该部门下有子部门，不允许删除！");
        }
        map.remove("parentid");
        map.put("deptid", id);
        List<DeptWorkerDO> worker = deptWorkerService.list(map);
        if (worker.size() != 0) {
            return R.error("该部门下有职员，不允许删除！");
        }
        if (deptService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @GetMapping("/treeView/{orgid}")
    String treeView(Model model, @PathVariable("orgid") String orgid) {
        model.addAttribute("orgid", orgid);
        return prefix + "/treeView";
    }

    @GetMapping("/tree")
    @ResponseBody
    public List<TreeEx<DeptDO>> tree(@RequestParam Map<String, Object> map) {
        return deptService.getTree(map);
    }
}