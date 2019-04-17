package com.hxy.nzxy.stexam.system.controller;

import com.hxy.nzxy.stexam.center.sys.service.SystemStudentService;
import com.hxy.nzxy.stexam.common.annotation.Log;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.SystemStudentDO;
import com.hxy.nzxy.stexam.system.domain.*;
import com.hxy.nzxy.stexam.system.service.*;
import com.hxy.nzxy.stexam.system.vo.UserVO;
import com.hxy.nzxy.stexam.common.annotation.Log;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.system.domain.RoleDO;
import com.hxy.nzxy.stexam.system.domain.UserDO;
import com.hxy.nzxy.stexam.system.service.*;
import com.hxy.nzxy.stexam.system.vo.UserVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/system/user")
@Controller
public class UserController extends SystemBaseController {
    private String prefix = "system/user";
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private DeptWorkerService deptWorkerService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private OrgService orgService;
    @Autowired
    private SystemStudentService systemStudentService;

    @RequiresPermissions("system:user:user")
    @GetMapping("")
    String user(Model model) {
        model.addAttribute("org", orgService.get(getOrgId()));
        model.addAttribute("deptid", getDeptid());
        return prefix + "/user";
    }
    @GetMapping("/worker")
    String Worker(Model model) {
        model.addAttribute("org",orgService.get(getOrgId()));
        model.addAttribute("deptid",getDeptid());
        return prefix +"/worker";
    }
    @GetMapping("/school")
    String school(Model model) {
        model.addAttribute("org",orgService.get(getOrgId()));
        model.addAttribute("deptid",getDeptid());
        return prefix +"/school";
    }
    @GetMapping("/region")
    String region(Model model) {
        model.addAttribute("org",orgService.get(getOrgId()));
        model.addAttribute("deptid",getDeptid());
        model.addAttribute("reg_region_type", commonService.listFieldDict(getAppName(), "reg_region", "type"));
        model.addAttribute("reg_region_photo_flag", commonService.listFieldDict(getAppName(), "reg_region", "photo_flag"));
        return prefix +"/region";
    }
    @GetMapping("/college")
    String college(Model model) {

        return prefix +"/college";
    }
    @GetMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        Object type =  query.get("type");
        List<UserDO> sysUserList;
        int total = 0;
        //1为中心端2为考区端3为组织机构4为学院
        if(type.equals("1")){
            sysUserList = userService.list(query);
         total = userService.count(query);
        }else if(type.equals("2")){
            sysUserList   = userService.listRegion(query);
             total = userService.countRegion(query);
        }else if(type.equals("3")){
            sysUserList   = userService.listSchool(query);
             total = userService.countSchool(query);
        }else if(type.equals("4")){
            sysUserList   = userService.listCollege(query);
             total = userService.countCollege(query);
        }else {
            sysUserList   = new ArrayList<UserDO>();

        }

        for (UserDO item : sysUserList) {
            item.setDeptWorkerStatus(FieldDictUtil.get(getAppName(), "sys_dept_worker", "status", item.getDeptWorkerStatus()));
            item.setStatus(FieldDictUtil.get(getAppName(), "sys_user", "status", item.getStatus()));
        }

        PageUtils pageUtil = new PageUtils(sysUserList, total);
        return pageUtil;
    }


    @GetMapping("/add")
    String add(Model model) {
        model.addAttribute("sys_user_status", commonService.listFieldDict(getAppName(), "sys_user", "status"));

        List<RoleDO> roles = roleService.list(getUserId(), "");
        model.addAttribute("roles", roles);
        return prefix + "/add";
    }

    @RequiresPermissions("system:user:edit")
    @GetMapping("/edit/{userid}")
    String edit(Model model, @PathVariable("userid") String userid) {
        model.addAttribute("sys_user_status", commonService.listFieldDict(getAppName(), "sys_user", "status"));
        UserDO userDO = userService.get(userid);
        model.addAttribute("userDO", userDO);
        List<RoleDO> roles = roleService.list(getUserId(), userid);
        model.addAttribute("roles", roles);
        return prefix + "/edit";
    }

    @RequiresPermissions("system:user:edit")
    @Log("更新用户")
    @PostMapping("/update")
    @ResponseBody
    public R update(UserDO user) {


        //更新用户状态
        if (userService.update(user) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @RequiresPermissions("system:user:edit")
    @Log("添加用户")
    @PostMapping("/save")
    @ResponseBody
    public R save(UserDO user) {
        String type = user.getType();

            //添加用户
            UserDO userDO = new UserDO();
            userDO.setWorkerid(user.getWorkerid());
            userDO.setName(user.getName());
            userDO.setPwd(MD5Utils.encrypt("111111"));
            userDO.setRoleIds(user.getRoleIds());
            userDO.setType(user.getType());
            userDO.setStatus("a");
            userDO.setOperator(getUserId().toString());
            userService.save(userDO);

            return R.ok();

    }

    @RequiresPermissions("system:user:remove")
    @Log("停用用户")
    @PostMapping("/remove")
    @ResponseBody
    R remove(String id) {
        //更改用户信息状态为停用
        UserDO userDO = userService.get(id);
        if (userDO != null) {
            userDO.setStatus("b");
            userDO.setRoleIds(new ArrayList<String>());
            if (userService.update(userDO) == 0) {
                return R.error("修改用户状态失败！");
            }
            return R.ok();
        }
        return R.error("帐号不存在!");
    }

    /**
     * 删除
     */
    @PostMapping("/resetPwd")
    @ResponseBody
    @RequiresPermissions("system:worker:resetPwd")
    public R resetPwd(String id) {
        UserDO userDO = userService.get(id);
        userDO.setPwd(MD5Utils.encrypt("123456"));
        userDO.setRoleIds(new ArrayList<String>());
        if (userService.update(userDO) > 0) {
            return R.ok();
        } else {
            return R.error(1, "重置失败");
        }
    }

    @RequestMapping({"/changePwd"})
    public String changePwd() {
        return prefix + "/changePwd";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/changePwdSave")
    public R changePwdSave(UserVO UserVO) {
        //先验证旧密码是否正确
        UserDO userOld = userService.get(getUserId());
        if (userOld.getPwd().equals(MD5Utils.encrypt(UserVO.getPwdOld()))) {
            UserDO userDO = new UserDO();
            userDO.setId(getUserId());
            userDO.setPwd(MD5Utils.encrypt(UserVO.getPwdNew()));
            userDO.setRoleIds(new ArrayList<String>());
            if (userService.update(userDO) > 0) {
                ShiroUtils.logout();
                return R.ok("修改成功, 请重新登录!");
            }
            return R.error("修改失败");
        } else {
            return R.error("旧密码错误");
        }
    }
}
