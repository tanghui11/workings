package com.hxy.nzxy.stexam.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.system.domain.AppDO;
import com.hxy.nzxy.stexam.system.service.AppService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxy.nzxy.stexam.system.domain.AppDO;
import com.hxy.nzxy.stexam.system.service.AppService;

/**
 * 应用管理
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-20 09:50:18
 */

@Controller
@RequestMapping("/system/app")
public class AppController extends SystemBaseController{
    @Autowired
    private AppService appService;
    @Autowired
    private CommonService commonService;

    @GetMapping()
    @RequiresPermissions("system:app:app")
    String App() {
        return "system/app/app";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:app:app")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<AppDO> appList = appService.list(query);
        int total = appService.count(query);
        for (AppDO item : appList) {
            item.setType(FieldDictUtil.get(getAppName(), "sys_app", "type", item.getType()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        PageUtils pageUtils = new PageUtils(appList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("system:app:add")
    String add(Model model) {
        model.addAttribute("sys_app_type", commonService.listFieldDict(getAppName(), "sys_app", "type"));
        return "system/app/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:app:edit")
    String edit(@PathVariable("id") String id, Model model) {
        AppDO app = appService.get(id);
        model.addAttribute("app", app);
        return "system/app/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:app:add")
    public R save(AppDO app) {

        //查询是否已经存在年级编号
        AppDO exist = appService.get(app.getId());
        if (exist != null) {
            return R.error("编号重复");
        }

        app.setOperator(ShiroUtils.getUserId().toString());
        if (appService.save(app) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:app:edit")
    public R update(AppDO app) {
        app.setOperator(ShiroUtils.getUserId().toString());
        appService.update(app);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:app:remove")
    public R remove(String id) {
        if (appService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:app:batchRemove")
    public R remove(@RequestParam("ids[]") String[] ids) {
        appService.batchRemove(ids);
        return R.ok();
    }

}
