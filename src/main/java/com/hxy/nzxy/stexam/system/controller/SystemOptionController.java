package com.hxy.nzxy.stexam.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.hxy.nzxy.stexam.system.domain.SystemOptionDO;
import com.hxy.nzxy.stexam.system.service.SystemOptionService;

/**
 * 系统选项
 *
 * @author dragon
 * @email 330504176@qq.com
 * @date 2017-12-26 11:21:06
 */

@Controller
@RequestMapping("/system/systemOption")
public class SystemOptionController extends SystemBaseController{
    @Autowired
    private SystemOptionService systemOptionService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private FieldDictService fieldDictService;

    @GetMapping()
    @RequiresPermissions("system:systemOption:systemOption")
    String SystemOption(Model model) {
        model.addAttribute("appList", commonService.listAllApp(null));
        return "system/systemOption/systemOption";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:systemOption:systemOption")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<SystemOptionDO> systemOptionList = systemOptionService.list(query);
        int total = systemOptionService.count(query);
        for (SystemOptionDO item : systemOptionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        PageUtils pageUtils = new PageUtils(systemOptionList, total);
        return pageUtils;
    }

    @GetMapping("/add/{appid}")
    @RequiresPermissions("system:systemOption:add")
    String add(Model model, @PathVariable("appid") String appid) {
        model.addAttribute("appid", appid);
        return "system/systemOption/add";
    }

    @GetMapping("/edit/{appid}/{id}")
    @RequiresPermissions("system:systemOption:edit")
    String edit(@PathVariable("appid") String appid, @PathVariable("id") String id, Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appid", appid);
        map.put("id", id);
        SystemOptionDO systemOption = systemOptionService.get(map);
        model.addAttribute("systemOption", systemOption);
        return "system/systemOption/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:systemOption:add")
    public R save(SystemOptionDO systemOption) {
        systemOption.setOperator(getUserId().toString());
        if (systemOptionService.save(systemOption) > 0) {
            FieldDictDO fieldDict = new FieldDictDO();
            fieldDict.setAppid(systemOption.getAppid());
            fieldDict.setTableName("system_option");
            fieldDict.setFieldName("id");
            fieldDict.setFieldValue(systemOption.getId());
            fieldDict.setFieldMean(systemOption.getValue());
            fieldDict.setSeq(0);
            if(fieldDictService.saveCache(fieldDict)>0) {
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
    @RequiresPermissions("system:systemOption:edit")
    public R update(SystemOptionDO systemOption) {
        systemOption.setOperator(getUserId().toString());
        if(systemOptionService.update(systemOption) > 0){
            FieldDictDO fieldDict = new FieldDictDO();
            fieldDict.setAppid(systemOption.getAppid());
            fieldDict.setTableName("system_option");
            fieldDict.setFieldName("id");
            fieldDict.setFieldValue(systemOption.getId());
            fieldDict.setFieldMean(systemOption.getValue());
            fieldDict.setSeq(0);
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
    @RequiresPermissions("system:systemOption:remove")
    public R remove( String appid, String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appid", appid);
        map.put("id", id);
        if (systemOptionService.remove(map) > 0) {
            FieldDictDO fieldDict = new FieldDictDO();
            fieldDict.setAppid(appid);
            fieldDict.setTableName("system_option");
            fieldDict.setFieldName("id");
            fieldDict.setFieldValue(id);
            if(fieldDictService.removeCache(fieldDict)>0) {
                return R.ok();
            }else{
                return R.error("删除数据成功，删除缓存失败！");
            }
        }
        return R.error();
    }
    @RequestMapping("/batchRemove")
    @ResponseBody
    public R batchRemove(  String[] ids) {
        ids= new String [] {"9" , "8" , "7" , "6" , "61" , "60"};
            FieldDictDO fieldDict = new FieldDictDO();
            fieldDict.setTableName("system_option");
            fieldDict.setFieldName("id");
            if(fieldDictService.batchremoveCache(fieldDict,ids)>0) {
                return R.ok();
            }else{
                return R.error("删除数据成功，删除缓存失败！");
            }

    }
}
