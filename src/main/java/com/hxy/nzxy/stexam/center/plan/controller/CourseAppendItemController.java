package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.CourseAppendItemService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.CourseAppendItemDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 须加考专业
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
 
@Controller
@RequestMapping("/plan/courseAppendItem")
public class CourseAppendItemController extends SystemBaseController{
	@Autowired
	private CourseAppendItemService courseAppendItemService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("plan:courseAppendItem:courseAppendItem")
	String CourseAppendItem(){
	    return "center/plan/courseAppendItem/courseAppendItem";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:courseAppendItem:courseAppendItem")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CourseAppendItemDO> courseAppendItemList = courseAppendItemService.list(query);
        for (CourseAppendItemDO item : courseAppendItemList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = courseAppendItemService.count(query);
		PageUtils pageUtils = new PageUtils(courseAppendItemList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:courseAppendItem:add")
	String add(Model model){
	    return "center/plan/courseAppendItem/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:courseAppendItem:edit")
	String edit(@PathVariable("id") Long id,Model model){
		CourseAppendItemDO courseAppendItem = courseAppendItemService.get(id);
		model.addAttribute("courseAppendItem", courseAppendItem);
	    return "center/plan/courseAppendItem/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:courseAppendItem:add")
	public R save( CourseAppendItemDO courseAppendItem){
        courseAppendItem.setOperator(ShiroUtils.getUserId().toString());
		if(courseAppendItemService.save(courseAppendItem)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:courseAppendItem:edit")
	public R update( CourseAppendItemDO courseAppendItem){
	    courseAppendItem.setOperator(ShiroUtils.getUserId().toString());
		courseAppendItemService.update(courseAppendItem);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:courseAppendItem:remove")
	public R remove( Long id){
		if(courseAppendItemService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:courseAppendItem:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		courseAppendItemService.batchRemove(ids);
		return R.ok();
	}
	
}
