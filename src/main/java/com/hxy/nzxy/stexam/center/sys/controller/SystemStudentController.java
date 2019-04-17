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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxy.nzxy.stexam.domain.SystemStudentDO;
import com.hxy.nzxy.stexam.center.sys.service.SystemStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 准考证号表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 14:32:12
 */
 
@Controller
@RequestMapping("/sys/systemStudent")
public class SystemStudentController extends SystemBaseController{
	@Autowired
	private SystemStudentService systemStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("sys:systemStudent:systemStudent")
	String SystemStudent(){
	    return "center/sys/systemStudent/systemStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:systemStudent:systemStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SystemStudentDO> systemStudentList = systemStudentService.list(query);
        for (SystemStudentDO item : systemStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = systemStudentService.count(query);
		PageUtils pageUtils = new PageUtils(systemStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sys:systemStudent:add")
	String add(Model model){
	    return "center/sys/systemStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("sys:systemStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SystemStudentDO systemStudent = systemStudentService.get(id);
		model.addAttribute("systemStudent", systemStudent);
	    return "center/sys/systemStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:systemStudent:add")
	public R save( SystemStudentDO systemStudent){
        systemStudent.setOperator(ShiroUtils.getUserId().toString());
		if(systemStudentService.save(systemStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:systemStudent:edit")
	public R update( SystemStudentDO systemStudent){
	    systemStudent.setOperator(ShiroUtils.getUserId().toString());
		systemStudentService.update(systemStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:systemStudent:remove")
	public R remove( Long id){
	    if(id==null){
            return R.ok("请选择要进行删除的数据!");
        }
		if(systemStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sys:systemStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
        if(ids==null){
            return R.ok("请选择要进行删除的数据!");
        }

		systemStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
