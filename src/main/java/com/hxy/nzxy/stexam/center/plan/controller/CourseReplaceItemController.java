package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.CourseReplaceItemService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.CourseReplaceItemDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 顶替课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
 
@Controller
@RequestMapping("/plan/courseReplaceItem")
public class CourseReplaceItemController extends SystemBaseController{
	@Autowired
	private CourseReplaceItemService courseReplaceItemService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("plan:courseReplaceItem:courseReplaceItem")
	String CourseReplaceItem(){
	    return "center/plan/courseReplaceItem/courseReplaceItem";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:courseReplaceItem:courseReplaceItem")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CourseReplaceItemDO> courseReplaceItemList = courseReplaceItemService.list(query);
        for (CourseReplaceItemDO item : courseReplaceItemList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setShowFlag(FieldDictUtil.get(getAppName(), "pla_course_replace_item", "show_flag", item.getShowFlag()));

		}
		int total = courseReplaceItemService.count(query);
		PageUtils pageUtils = new PageUtils(courseReplaceItemList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:courseReplaceItem:add")
	String add(Model model){
		model.addAttribute("pla_course_replace_item_show_flag", commonService.listFieldDict(getAppName(), "pla_course_replace_item", "show_flag"));

		return "center/plan/courseReplaceItem/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:courseReplaceItem:edit")
	String edit(@PathVariable("id") Long id,Model model){
		CourseReplaceItemDO courseReplaceItem = courseReplaceItemService.get(id);
		model.addAttribute("courseReplaceItem", courseReplaceItem);
		model.addAttribute("pla_course_replace_item_show_flag", commonService.listFieldDict(getAppName(), "pla_course_replace_item", "show_flag"));
	    return "center/plan/courseReplaceItem/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:courseReplaceItem:add")
	public R save( CourseReplaceItemDO courseReplaceItem){
        courseReplaceItem.setOperator(ShiroUtils.getUserId().toString());
        if(courseReplaceItemService.selectInDB(courseReplaceItem) != null ){
        	return R.error(0,"该课程顶替已存在!");
		}
		if(courseReplaceItemService.save(courseReplaceItem)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:courseReplaceItem:edit")
	public R update( CourseReplaceItemDO courseReplaceItem){
	    courseReplaceItem.setOperator(ShiroUtils.getUserId().toString());
		courseReplaceItemService.update(courseReplaceItem);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:courseReplaceItem:remove")
	public R remove( Long id){
		if(courseReplaceItemService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:courseReplaceItem:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		courseReplaceItemService.batchRemove(ids);
		return R.ok();
	}
	
}
