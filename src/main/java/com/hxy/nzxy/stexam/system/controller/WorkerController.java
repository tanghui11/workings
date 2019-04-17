package com.hxy.nzxy.stexam.system.controller;

import java.util.*;
import java.util.regex.Pattern;

import com.hxy.nzxy.stexam.common.annotation.Log;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.system.domain.*;
import com.hxy.nzxy.stexam.system.service.*;
import com.hxy.nzxy.stexam.system.vo.UserVO;
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

import javax.websocket.server.PathParam;

/**
 * 职员管理
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-23 11:47:43
 */

@Controller
@RequestMapping("/system/worker")
public class WorkerController extends SystemBaseController {
    @Autowired
    private WorkerService workerService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private DeptWorkerService deptWorkerService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrgService orgService;

    @GetMapping()
    @RequiresPermissions("system:worker:worker")
    String Worker(Model model) {
        model.addAttribute("org",orgService.get(getOrgId()));
        model.addAttribute("deptid",getDeptid());
        return "system/worker/worker";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:worker:worker")
    PageUtils list(@RequestParam Map<String, Object> params) {
        params.put("dwStatus", "a");
        Query query = new Query(params);
        List<WorkerDO> sysWorkerList = workerService.list(query);
        for (WorkerDO item : sysWorkerList) {
            item.setGender(FieldDictUtil.get(getAppName(), "sys_worker", "gender", item.getGender()));
            item.setStatus(FieldDictUtil.get(getAppName(), "sys_worker", "status", item.getStatus()));
        }
        int total = workerService.count(query);
        PageUtils pageUtil = new PageUtils(sysWorkerList, total - 1);
        return pageUtil;
    }

    @GetMapping("/add")
    @RequiresPermissions("system:worker:add")
    String add(Model model, @PathParam("deptid") String deptid) {
        DeptDO deptDO = deptService.get(deptid);
        if (deptDO != null) {
            model.addAttribute("deptName", deptDO.getName());
        }
        model.addAttribute("deptid", deptid);
        model.addAttribute("sys_worker_gender", commonService.listFieldDict(getAppName(), "sys_worker", "gender"));
        model.addAttribute("sys_worker_first_education", commonService.listFieldDict(getAppName(), "sys_worker", "first_education"));
        model.addAttribute("sys_worker_status", commonService.listFieldDict(getAppName(), "sys_worker", "status"));
        return "system/worker/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:worker:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("sys_worker_gender", commonService.listFieldDict(getAppName(), "sys_worker", "gender"));
        model.addAttribute("sys_worker_first_education", commonService.listFieldDict(getAppName(), "sys_worker", "first_education"));
        model.addAttribute("sys_worker_status", commonService.listFieldDict(getAppName(), "sys_worker", "status"));
        WorkerDO worker = workerService.get(id.toString());
        model.addAttribute("worker", worker);
        return "system/worker/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:worker:add")
    public R save(WorkerDO worker) {
        if (!Pattern.matches("^(?:\\+86)?1\\d{10}$", worker.getMphone())) {
            return R.error("请输入正确的手机号码!");
        }
        worker.setPinyin(PinYinUtil.getFirstSpell(worker.getName()));
        //判断用户是否存在
        WorkerDO exist = workerService.getByMphone(worker.getMphone());
        //先判断用户的离职状态,如果在职,不允许添加
        if (exist != null && exist.getStopStatus().equals("a")) {
            return R.error("该用户已在'" + exist.getOrgName() + " - " + exist.getDeptName() + "'就职, 不允许重复添加!");
        }
        //用户不存在, 直接添加
        if (exist == null) {
            worker.setOperator(getUserId().toString());
            if (workerService.save(worker) > 0) {
                //插入职员部门关系数据
                DeptWorkerDO dwo = new DeptWorkerDO();
                dwo.setDeptid(worker.getDeptid());
                dwo.setWorkerid(worker.getId());
                dwo.setStatus("a");
                dwo.setOperator(getUserId().toString());
                deptWorkerService.save(dwo);
                //查询默认角色
           /*    WorkerDO workerDO = workerService.get(getUserId().toString());
                OrgDO orgDO = orgService.get(workerDO.getOrgid());
                String listDefaultUserRole = FieldDictUtil.get(getAppName(), "system_option", "id", "默认用户角色-"+orgDO.getType());
                String[] arrDefaultUserRole = listDefaultUserRole.split(",");
                //添加用户
                UserDO userDO = new UserDO();
                userDO.setWorkerid(worker.getId());
                userDO.setName(worker.getMphone());
                userDO.setPwd(MD5Utils.encrypt(worker.getMphone()));
                userDO.setRoleIds(Arrays.asList(arrDefaultUserRole));
                userDO.setStatus("a");
                userDO.setOperator(getUserId().toString());
                userService.save(userDO);*/

                return R.ok();
            }
        } else {
            //用户存在并且状态是已离职, 做更新处理
            worker.setId(exist.getId());
            worker.setOperator(getUserId().toString());
            workerService.update(worker);

            //再更新部门职员
            DeptWorkerDO deptWorkerDO = new DeptWorkerDO();
            deptWorkerDO.setId(exist.getDeptWorkerid());
            deptWorkerDO.setDeptid(worker.getDeptid());
            deptWorkerDO.setWorkerid(worker.getId());
            deptWorkerDO.setStatus("a");
            deptWorkerDO.setOperator(getUserId().toString());
            deptWorkerService.update(deptWorkerDO);

            //添加 更新用户信息
            UserDO userDO = new UserDO();
            userDO.setWorkerid(worker.getId());
            userDO.setName(worker.getMphone());
            userDO.setPwd(MD5Utils.encrypt(worker.getMphone()));
            List<String> roleIds = new ArrayList<String>();
            roleIds.add(FieldDictUtil.get(getAppName(), "system_option", "id", "defaultcommonroler"));
            userDO.setRoleIds(roleIds);
            userDO.setStatus("a");
            userDO.setOperator(getUserId().toString());
            //判断用户是否存在
            UserDO existUser = userService.getByName(worker.getMphone());
            if (existUser == null) {
                userService.save(userDO);
            } else {
                userDO.setId(existUser.getId());
                userService.update(userDO);
            }
        }

        return R.ok();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:worker:edit")
    public R update(WorkerDO worker) {
        if (!Pattern.matches("^(?:\\+86)?1\\d{10}$", worker.getMphone())) {
            return R.error("请输入正确的手机号码!");
        }
        worker.setPinyin(PinYinUtil.getFirstSpell(worker.getName()));
        //查找旧的信息
        WorkerDO workerOld = workerService.get(worker.getId());
        //先更新职员信息
        worker.setOperator(getUserId().toString());
        workerService.update(worker);

        //再更新部门职员
        deptWorkerService.removeByWorkerid(worker.getId());
        DeptWorkerDO deptWorkerDO = new DeptWorkerDO();
        deptWorkerDO.setDeptid(worker.getDeptid());
        deptWorkerDO.setWorkerid(worker.getId());
        deptWorkerDO.setStatus("a");
        deptWorkerDO.setOperator(getUserId().toString());
        deptWorkerService.save(deptWorkerDO);

        //添加/更新用户信息
        UserDO userDO = new UserDO();
        userDO.setWorkerid(worker.getId());
        userDO.setName(worker.getMphone());
        List<String> roleIds = new ArrayList<String>();
        roleIds.add(FieldDictUtil.get(getAppName(), "system_option", "id", "defaultcommonroler"));
        userDO.setRoleIds(roleIds);
        userDO.setStatus("a");
        userDO.setOperator(getUserId().toString());
        UserDO exist = userService.getByName(workerOld.getMphone());
        if (exist != null) {
            userDO.setId(exist.getId());
            userService.update(userDO);
        } else {
            userDO.setPwd(MD5Utils.encrypt(worker.getMphone()));
            userService.save(userDO);
        }

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:worker:remove")
    public R remove(String id) {
        WorkerDO worker = workerService.get(id);
        //更新部门职员状态为离职
        DeptWorkerDO deptWorkerDO = new DeptWorkerDO();
        deptWorkerDO.setId(worker.getDeptWorkerid());
        deptWorkerDO.setDeptid(worker.getDeptid());
        deptWorkerDO.setWorkerid(worker.getId());
        deptWorkerDO.setStatus("b");
        deptWorkerDO.setOperator(getUserId().toString());
        deptWorkerService.update(deptWorkerDO);
        //更改用户信息状态为停用
        UserDO userDO = userService.getByWorkerid(id);
        if (userDO != null) {
            userDO.setStatus("b");
            userDO.setRoleIds(new ArrayList<String>());
            userService.update(userDO);
        }
        return R.ok();
    }

    /**
     * 重置密码
     */
    @PostMapping("/resetPwd")
    @ResponseBody
    @RequiresPermissions("system:worker:resetPwd")
    public R resetPwd(String id) {
        WorkerDO workerDO = workerService.get(id);
        UserDO userDO = userService.getByWorkerid(id);
        userDO.setStatus("a");
        userDO.setPwd(MD5Utils.encrypt("123456"));
        userDO.setRoleIds(new ArrayList<String>());
        if (userService.update(userDO) > 0) {
            return R.ok();
        } else {
            return R.error(1, "重置失败");
        }
    }
}