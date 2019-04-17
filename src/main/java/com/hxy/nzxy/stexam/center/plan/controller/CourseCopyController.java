package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.CourseCopyService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.CourseCopyDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 课程管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
 
@Controller
@RequestMapping("/plan/courseCopy")
public class CourseCopyController extends SystemBaseController{
	@Autowired
	private CourseCopyService courseCopyService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("plan:courseCopy:courseCopy")
	String CourseCopy(){
	    return "center/plan/courseCopy/courseCopy";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:courseCopy:courseCopy")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CourseCopyDO> courseCopyList = courseCopyService.list(query);
        for (CourseCopyDO item : courseCopyList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = courseCopyService.count(query);
		PageUtils pageUtils = new PageUtils(courseCopyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:courseCopy:add")
	String add(Model model){
	    return "center/plan/courseCopy/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:courseCopy:edit")
	String edit(@PathVariable("id") String id,Model model){
		CourseCopyDO courseCopy = courseCopyService.get(id);
		model.addAttribute("courseCopy", courseCopy);
	    return "center/plan/courseCopy/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:courseCopy:add")
	public R save( CourseCopyDO courseCopy){
        courseCopy.setOperator(ShiroUtils.getUserId().toString());
		if(courseCopyService.save(courseCopy)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:courseCopy:edit")
	public R update( CourseCopyDO courseCopy){
	    courseCopy.setOperator(ShiroUtils.getUserId().toString());
		courseCopyService.update(courseCopy);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:courseCopy:remove")
	public R remove( String id){
		if(courseCopyService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:courseCopy:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		courseCopyService.batchRemove(ids);
		return R.ok();
	}
	
}
