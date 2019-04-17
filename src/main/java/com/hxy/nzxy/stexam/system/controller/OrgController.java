package com.hxy.nzxy.stexam.system.controller;

import java.util.*;

import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.domain.TreeEx;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.system.domain.*;
import com.hxy.nzxy.stexam.system.service.*;
import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxy.nzxy.stexam.common.utils.*;

/**
 * 机构管理
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-19 09:39:15
 */


@Controller
@RequestMapping("/system/org")
public class OrgController extends SystemBaseController {
    @Autowired
    private OrgService orgService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private DeptWorkerService deptWorkerService;

    @Autowired
    private UserService userService;

    @GetMapping()
    @RequiresPermissions("system:org:org")
    String Org(Model model) {
        model.addAttribute("org",orgService.get(getOrgId()));
        return "system/org/org";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:org:org")
    public PageUtils list(@RequestParam Map<String, Object> map) {
        Query query = new Query(map);
        List<OrgDO> orgList = orgService.list(query);
        for (OrgDO item : orgList) {
            item.setType(FieldDictUtil.get(getAppName(), "sys_org", "type", item.getType()));
            item.setSchoolType(FieldDictUtil.get(getAppName(), "sys_org", "school_type", item.getSchoolType()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        int total = orgService.count(query);
        PageUtils pageUtil = new PageUtils(orgList, total);
        return pageUtil;
    }

    @GetMapping("/add/{parentid}")
    @RequiresPermissions("system:org:add")
    String add(Model model, @PathVariable("parentid") String parentid) {
        OrgDO orgDO;
        if (parentid.equals("0")) {
            orgDO = new OrgDO();
            orgDO.setId("0");
            orgDO.setName("顶级机构");
            model.addAttribute("parentOrg", orgDO);
        } else {
            orgDO = orgService.get(parentid.toString());
            model.addAttribute("parentOrg", orgDO);
        }
        List<FieldDictDO> listSysOrgType = commonService.listFieldDict(getAppName(), "sys_org", "type");
        //移除本机构以上的机构类别
        int pos = 0;
        for (int i = 0; i < listSysOrgType.size(); i++) {
            if (((FieldDictDO) listSysOrgType.get(i)).getId().equals(orgDO.getType())) {
                pos = i;
                break;
            }
        }
        List<FieldDictDO> listSysOrgTypeNew = new ArrayList<>();
        if((pos + 1) < listSysOrgType.size()) {
            listSysOrgTypeNew.add(listSysOrgType.get(pos + 1));
        }
        model.addAttribute("sys_org_type", listSysOrgTypeNew);
        model.addAttribute("sys_org_school_type", commonService.listFieldDict(getAppName(), "sys_org", "school_type"));
        return "system/org/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:org:edit")
    String edit(@PathVariable("id") String id, Model model) {
        OrgDO orgDO = orgService.get(id);
        model.addAttribute("org", orgDO);
        List<FieldDictDO> listSysOrgType = commonService.listFieldDict(getAppName(), "sys_org", "type");
        //移除本机构以上的机构类别
        int pos = 0;
        for (int i = 0; i < listSysOrgType.size(); i++) {
            if (((FieldDictDO) listSysOrgType.get(i)).getId().equals(orgDO.getType())) {
                pos = i;
                break;
            }
        }
        List<FieldDictDO> listSysOrgTypeNew = new ArrayList<>();
        listSysOrgTypeNew.add(listSysOrgType.get(pos));
        model.addAttribute("sys_org_type", listSysOrgTypeNew);
        model.addAttribute("sys_org_school_type", commonService.listFieldDict(getAppName(), "sys_org", "school_type"));
        return "system/org/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:org:add")
    @Transactional
    public R save(OrgDO org) {
        //查询是否已经存在科目编号
        Map<String, Object> map = new HashMap<>();
        map.put("code", org.getCode());
        List<OrgDO> exist = orgService.list(map);
        if (exist.size() > 0) {
            return R.error("机构代码已存在,请重新填写");
        }

        org.setPinyin(PinYinUtil.getFirstSpell(org.getName()));
        org.setOperator(getUserId().toString());

        if (orgService.save(org) > 0) {
            //添加默认的部门
            DeptDO deptDO = new DeptDO();
            deptDO.setParentid("0");
            deptDO.setOrgid(org.getId());
            deptDO.setName(org.getName());
            deptDO.setPinyin(PinYinUtil.getFirstSpell(org.getName()));
            deptDO.setOperator(getUserId().toString());
            deptService.save(deptDO);

            //添加默认职员
            WorkerDO workerDO = new WorkerDO();
            workerDO.setName(org.getName());
            workerDO.setPinyin(PinYinUtil.getFirstSpell(org.getName()));
            workerDO.setGender("m");
            workerDO.setStatus("a");
            workerDO.setOperator(getUserId().toString());
            workerService.save(workerDO);

            //添加部门职员
            DeptWorkerDO deptWorkerDO = new DeptWorkerDO();
            deptWorkerDO.setDeptid(deptDO.getId());
            deptWorkerDO.setWorkerid(workerDO.getId());
            deptWorkerDO.setStatus("a");
            deptWorkerDO.setOperator(getUserId().toString());
            deptWorkerService.save(deptWorkerDO);

            //查询默认角色
            String listDefaultUserRole = FieldDictUtil.get(getAppName(), "system_option", "id", "默认用户角色-"+org.getType());
            String[] arrDefaultUserRole = listDefaultUserRole.split(",");
            //添加用户
            UserDO userDO = new UserDO();
            userDO.setWorkerid(workerDO.getId());
            userDO.setName(org.getCode());
            userDO.setPwd(MD5Utils.encrypt(userDO.getName()));
            userDO.setRoleIds(Arrays.asList(arrDefaultUserRole));
            userDO.setStatus("a");
            userDO.setOperator(getUserId().toString());
            userService.save(userDO);

            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:org:edit")
    public R update(OrgDO org) {
        org.setPinyin(PinYinUtil.getFirstSpell(org.getName()));
        org.setOperator(getUserId().toString());
        orgService.update(org);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:org:remove")
    public R remove(String id) {
        Map map = new HashMap();
        map.put("orgid", id);
        if(deptService.list(map).size() > 0){
            return R.error("该机构下已经增加了部门信息，不能删除！");
        }
        map.clear();
        map.put("parentid", id);
        if (orgService.list(map).size() > 0) {
            return R.error("该机构下有子机构信息，不能删除！");
        }
        if (orgService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @GetMapping("/treeView/{orgid}")
    public String treeView(Model model,@PathVariable("orgid") String orgid) {
        model.addAttribute("orgid",orgid);
        return "/system/org/treeView";
    }

    @GetMapping("/tree")
    @ResponseBody
    public List<TreeEx<OrgDO>> tree(@RequestParam Map<String, Object> map) {
        return orgService.getTree(map);
    }

    @GetMapping("/treeAll")
    @ResponseBody
    public Tree<OrgDO> treeAll(@RequestParam Map<String, Object> map) {
        return orgService.getTreeAll(map);
    }
}
