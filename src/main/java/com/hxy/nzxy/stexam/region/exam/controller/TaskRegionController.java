package com.hxy.nzxy.stexam.region.exam.controller;

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

import com.hxy.nzxy.stexam.domain.TaskDO;
import com.hxy.nzxy.stexam.region.exam.service.TaskRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考试任务
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:23
 */
 
@Controller
@RequestMapping("/exam/taskRegion")
public class TaskRegionController extends SystemBaseController{
	@Autowired
	private TaskRegionService taskRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("exam:taskRegion:taskRegion")
	String TaskRegion(){
	    return "region/exam/taskRegion/taskRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exam:taskRegion:taskRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TaskDO> taskRegionList = taskRegionService.list(query);
        for (TaskDO item : taskRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = taskRegionService.count(query);
		PageUtils pageUtils = new PageUtils(taskRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exam:taskRegion:add")
	String add(Model model){
	    return "region/exam/taskRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:taskRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		TaskDO taskRegion = taskRegionService.get(id);
		model.addAttribute("taskRegion", taskRegion);
	    return "region/exam/taskRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:taskRegion:add")
	public R save( TaskDO taskRegion){
        taskRegion.setOperator(ShiroUtils.getUserId().toString());
		if(taskRegionService.save(taskRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exam:taskRegion:edit")
	public R update( TaskDO taskRegion){
	    taskRegion.setOperator(ShiroUtils.getUserId().toString());
		taskRegionService.update(taskRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exam:taskRegion:remove")
	public R remove( Long id){
		if(taskRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exam:taskRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		taskRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
