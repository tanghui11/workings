package com.hxy.nzxy.stexam.system.controller;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.system.domain.ExportTableDO;
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

import com.hxy.nzxy.stexam.system.domain.ExportFieldDO;
import com.hxy.nzxy.stexam.system.service.ExportFieldService;

/**
 * 导出数据字段
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2018-03-16 17:39:29
 */

@Controller
@RequestMapping("/system/exportField")
public class ExportFieldController extends SystemBaseController {
    @Autowired
    private ExportFieldService exportFieldService;
    @Autowired
    private CommonService commonService;

    @GetMapping("/{exportTableid}")
    @RequiresPermissions("system:exportField:exportField")
    String ExamRegOrg(Model model, @PathVariable("exportTableid") String exportTableid) {
        model.addAttribute("exportTableid", exportTableid);
        return "system/exportField/exportField";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:exportField:exportField")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        List<ExportFieldDO> exportFieldList = exportFieldService.list(params);
        for (ExportFieldDO item : exportFieldList) {
            item.setType(FieldDictUtil.get(getAppName(), "sys_export_field", "type", item.getType()));
            item.setTransType(FieldDictUtil.get(getAppName(), "sys_export_field", "trans_type", item.getTransType()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        PageUtils pageUtils = new PageUtils(exportFieldList, exportFieldList.size());
        return pageUtils;
    }

    /**
     * 添加页面
     */
    @GetMapping("/add/{exportTableid}")
    @RequiresPermissions("system:exportField:add")
    String add(Model model, @PathVariable("exportTableid") String exportTableid) {
        model.addAttribute("exportTableid", exportTableid);
        model.addAttribute("export_field_transType",commonService.listFieldDict(getAppName(), "sys_export_field", "trans_type"));
        model.addAttribute("export_field_type", commonService.listFieldDict(getAppName(), "sys_export_field", "type"));
        return "system/exportField/add";

    }

    /**
     * 修改页面
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:exportField:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        ExportFieldDO exportField = exportFieldService.get(id);
        model.addAttribute("export_field_type", commonService.listFieldDict(getAppName(), "sys_export_field", "type"));
        model.addAttribute("export_field_transType",commonService.listFieldDict(getAppName(), "sys_export_field", "trans_type"));
        //exportField.setType(FieldDictUtil.get(getAppName(), "sys_export_field", "type", exportField.getType()));
        //exportField.setTransType(FieldDictUtil.get(getAppName(), "sys_export_field", "trans_type", exportField.getTransType()));
        model.addAttribute("exportField", exportField);
        return "system/exportField/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:exportField:add")
    public R save(ExportFieldDO exportField) {
        exportField.setOperator(getUserId().toString());
        if (exportFieldService.save(exportField) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:exportField:edit")
    public R update(ExportFieldDO exportField) {
        exportField.setOperator(getUserId().toString());
        exportFieldService.update(exportField);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:exportField:remove")
    public R remove(String id) {
        if (exportFieldService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:exportField:batchRemove")
    public R remove(@RequestParam("ids[]") String[] ids) {
        exportFieldService.batchRemove(ids);
        return R.ok();
    }
}
