package com.hxy.nzxy.stexam.center.sys.controller;

import java.util.List;
import java.util.Map;

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

import com.hxy.nzxy.stexam.domain.NativeDO;
import com.hxy.nzxy.stexam.center.sys.service.NativeService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2019-03-19 11:58:48
 */
 
@Controller
@RequestMapping("/sys/native")
public class NativeController extends SystemBaseController{
	@Autowired
	private NativeService nativeService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("sys:native:native")
	String Native(){
	    return "center/sys/native/native";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<NativeDO> nativeList = nativeService.list(params);
        for (NativeDO item : nativeList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = nativeService.count(params);
		PageUtils pageUtils = new PageUtils(nativeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sys:native:add")
	String add(Model model){
	    return "center/sys/native/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("sys:native:edit")
	String edit(@PathVariable("id") String id,Model model){
		NativeDO nativeDo = nativeService.get(id);
		model.addAttribute("native", nativeDo);
	    return "center/sys/native/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:native:add")
	public R save( NativeDO nativeDo){
		nativeDo.setOperator(ShiroUtils.getUserId().toString());
		if(nativeService.save(nativeDo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:native:edit")
	public R update( NativeDO nativeDo){
		nativeDo.setOperator(ShiroUtils.getUserId().toString());
		nativeService.update(nativeDo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:native:remove")
	public R remove( String id){
	    if(id==null||id==""){
            return R.ok("请选择要进行删除的数据!");
        }
		if(nativeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sys:native:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
        if(ids==null||ids.length==0){
            return R.ok("请选择要进行删除的数据!");
        }

		nativeService.batchRemove(ids);
		return R.ok();
	}
	
}
