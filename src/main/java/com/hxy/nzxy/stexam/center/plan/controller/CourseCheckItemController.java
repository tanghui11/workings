package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.CourseCheckItemService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.CourseCheckItemDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 复选课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
 
@Controller
@RequestMapping("/plan/courseCheckItem")
public class CourseCheckItemController extends SystemBaseController{
	@Autowired
	private CourseCheckItemService courseCheckItemService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("plan:courseCheckItem:courseCheckItem")
	String CourseCheckItem(){
	    return "center/plan/courseCheckItem/courseCheckItem";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:courseCheckItem:courseCheckItem")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CourseCheckItemDO> courseCheckItemList = courseCheckItemService.list(query);
        for (CourseCheckItemDO item : courseCheckItemList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_course", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "pla_course", "classify", item.getClassify()));
			item.setAttribute(FieldDictUtil.get(getAppName(), "pla_course", "attribute", item.getAttribute()));
			item.setPracticeCourseid(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getPracticeCourseid())+" "+item.getPracticeCourseid());
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = courseCheckItemService.count(query);
		PageUtils pageUtils = new PageUtils(courseCheckItemList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:courseCheckItem:add")
	String add(Model model){
	    return "center/plan/courseCheckItem/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:courseCheckItem:edit")
	String edit(@PathVariable("id") Long id,Model model){
		CourseCheckItemDO courseCheckItem = courseCheckItemService.get(id);
		courseCheckItem.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", courseCheckItem.getCourseid()));
		model.addAttribute("courseCheckItem", courseCheckItem);
	    return "center/plan/courseCheckItem/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:courseCheckItem:add")
	public R save( CourseCheckItemDO courseCheckItem){
        courseCheckItem.setOperator(ShiroUtils.getUserId().toString());
		if(courseCheckItemService.save(courseCheckItem)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:courseCheckItem:edit")
	public R update( CourseCheckItemDO courseCheckItem){
	    courseCheckItem.setOperator(ShiroUtils.getUserId().toString());
		courseCheckItemService.update(courseCheckItem);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:courseCheckItem:remove")
	public R remove( Long id){
		if(courseCheckItemService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:courseCheckItem:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		courseCheckItemService.batchRemove(ids);
		return R.ok();
	}
	
}
