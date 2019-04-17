package com.hxy.nzxy.stexam.center.sys.controller;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.common.config.Constant;
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

import com.hxy.nzxy.stexam.domain.SystemOptionDO;
import com.hxy.nzxy.stexam.center.sys.service.SysOptionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 系统选项
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 11:35:19
 */
 
@Controller
@RequestMapping("/sys/systemOption")
public class SysOptionController extends SystemBaseController{
	@Autowired
	private SysOptionService systemOptionService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private FieldDictService fieldDictService;
	@GetMapping()
	@RequiresPermissions("sys:systemOption:systemOption")
	String SystemOption(){
	    return "center/sys/systemOption/systemOption";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:systemOption:systemOption")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("ids","24,25,30,31");
        Query query = new Query(params);

		List<SystemOptionDO> systemOptionList = systemOptionService.list(query);
        for (SystemOptionDO item : systemOptionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = systemOptionService.count(query);
		PageUtils pageUtils = new PageUtils(systemOptionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sys:systemOption:add")
	String add(Model model){
	    return "center/sys/systemOption/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("sys:systemOption:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SystemOptionDO systemOption = systemOptionService.get(id);
		model.addAttribute("systemOption", systemOption);
	    return "center/sys/systemOption/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:systemOption:add")
	public R save( SystemOptionDO systemOption){
        systemOption.setOperator(ShiroUtils.getUserId().toString());
		if(systemOptionService.save(systemOption)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(systemOption.getAppid());
			fieldDict.setTableName("system_option");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(systemOption.getId()+"");
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
	@RequiresPermissions("sys:systemOption:edit")
	public R update( SystemOptionDO systemOption){
	    systemOption.setOperator(ShiroUtils.getUserId().toString());
		if(systemOptionService.update(systemOption)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(systemOption.getAppid());
			fieldDict.setTableName("system_option");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(systemOption.getId()+"");
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
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:systemOption:remove")
	public R remove( Long id){
	    if(id==null){
            return R.ok("请选择要进行删除的数据!");
        }
		if(systemOptionService.remove(id)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("system_option");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(id+"");
			if(fieldDictService.removeCache(fieldDict)>0) {
				return R.ok();
			}else{
				return R.error("删除数据成功，删除缓存失败！");
			}
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sys:systemOption:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
        if(ids==null){
            return R.ok("请选择要进行删除的数据!");
        }

		if(systemOptionService.batchRemove(ids)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setTableName("system_option");
			fieldDict.setFieldName("id");
			fieldDict.setSeq(0);
			if(fieldDictService.batchremoveCache(fieldDict,CommonUtil.longToString(ids))>0) {
				return R.ok();
			}else{
				return R.error("删除数据成功，删除缓存失败！");
			}
		}
		return R.error();
	}
	
}
