package com.hxy.nzxy.stexam.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.common.domain.CommonDO;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.system.domain.FieldDictDO;
import com.hxy.nzxy.stexam.system.service.FieldDictService;
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

/**
 * 数据字典
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-20 14:57:46
 */

@Controller
@RequestMapping("/system/fieldDict")
public class FieldDictController extends SystemBaseController {
    @Autowired
    private FieldDictService fieldDictService;
    @Autowired
    private CommonService commonService;

    @GetMapping()
    @RequiresPermissions("system:fieldDict:fieldDict")
    String FieldDict(Model model) {
        model.addAttribute("appList", commonService.listAllApp(null));
        return "system/fieldDict/fieldDict";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:fieldDict:fieldDict")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<FieldDictDO> fieldDictList = fieldDictService.list(query);
        for (FieldDictDO item : fieldDictList) {
            item.setType(FieldDictUtil.get(getAppName(), "sys_field_dict", "type", item.getType()));
            item.setDisplayStatus(FieldDictUtil.get(getAppName(), "sys_field_dict", "display_status", item.getDisplayStatus()));
            item.setStatus(FieldDictUtil.get(getAppName(), "sys_field_dict", "status", item.getStatus()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        int total = fieldDictService.count(query);
        PageUtils pageUtils = new PageUtils(fieldDictList, total);
        return pageUtils;
    }

    @GetMapping("/add/{appid}/{tableName}/{fieldName}")
    @RequiresPermissions("system:fieldDict:add")
    String add(@PathVariable("appid") String appid, @PathVariable("tableName") String tableName, @PathVariable("fieldName") String fieldName, Model model) {
        model.addAttribute("appid", appid);
        model.addAttribute("tableName", tableName);
        model.addAttribute("fieldName", fieldName);
        model.addAttribute("sys_field_dict_type", commonService.listFieldDict(getAppName(), "sys_field_dict", "type"));
        model.addAttribute("sys_field_dict_display_status", commonService.listFieldDict(getAppName(), "sys_field_dict", "display_status"));
        model.addAttribute("sys_field_dict_status", commonService.listFieldDict(getAppName(), "sys_field_dict", "status"));
        return "system/fieldDict/add";
    }

    @GetMapping("/edit/{appid}/{tableName}/{fieldName}/{fieldValue}")
    @RequiresPermissions("system:fieldDict:edit")
    String edit(@PathVariable("appid") String appid, @PathVariable("tableName") String tableName, @PathVariable("fieldName") String fieldName, @PathVariable("fieldValue") String fieldValue, Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appid", appid);
        map.put("tableName", tableName);
        map.put("fieldName", fieldName);
        map.put("fieldValue", fieldValue);

        model.addAttribute("sys_field_dict_type", commonService.listFieldDict(getAppName(), "sys_field_dict", "type"));
        model.addAttribute("sys_field_dict_display_status", commonService.listFieldDict(getAppName(), "sys_field_dict", "display_status"));
        model.addAttribute("sys_field_dict_status", commonService.listFieldDict(getAppName(), "sys_field_dict", "status"));
        FieldDictDO fieldDict = fieldDictService.get(map);
        model.addAttribute("fieldDict", fieldDict);
        return "system/fieldDict/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:fieldDict:add")
    public R save(FieldDictDO fieldDict) {
        fieldDict.setOperator(getUserId().toString());
        if (fieldDictService.save(fieldDict) > 0) {
            if(fieldDictService.saveCache(fieldDict) > 0) {
                return R.ok();
            }else{
                return R.error("添加数据成功，添加缓存失败！");
            }
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:fieldDict:edit")
    public R update(FieldDictDO fieldDict) {
        fieldDict.setOperator(getUserId().toString());
        if (fieldDictService.update(fieldDict) > 0) {
            if(fieldDictService.updateCache(fieldDict)>0) {
                return R.ok();
            }else{
                return R.error("修改数据成功，修改缓存失败！");
            }
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:fieldDict:remove")
    public R remove(@RequestParam Map<String, Object> params) {
        if (fieldDictService.remove(params) > 0) {
            FieldDictDO fieldDict = new FieldDictDO();
            fieldDict.setAppid(params.get("appid").toString());
            fieldDict.setTableName(params.get("tableName").toString());
            fieldDict.setFieldName(params.get("fieldName").toString());
            fieldDict.setFieldValue(params.get("fieldValue").toString());
            if(fieldDictService.removeCache(fieldDict)>0) {
                return R.ok();
            }else{
                return R.error("删除数据成功，删除缓存失败！");
            }
        }
        return R.error();
    }

    @ResponseBody
    @PostMapping("/listAllTables")
    @RequiresPermissions("system:systemOption:systemOption")
    public List<CommonDO> listAllTables(@RequestParam Map<String, Object> params) {
        List<CommonDO> list = fieldDictService.listAllTables(params);
        return list;
    }

    @ResponseBody
    @PostMapping("/listAllFields")
    @RequiresPermissions("system:systemOption:systemOption")
    public List<CommonDO> listAllFields(@RequestParam Map<String, Object> params) {
        List<CommonDO> list = fieldDictService.listAllFields(params);
        return list;
    }
}
