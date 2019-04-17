package com.hxy.nzxy.stexam.school.school.controller;

import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.ExemptionCourseReplaceItemDO;
import com.hxy.nzxy.stexam.school.school.service.ExemptionCourseReplaceItemService;
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
@RequestMapping("/school/exemptionCourseReplace")
public class ExemptionCourseReplaceItemController extends SystemBaseController{
	@Autowired
	private ExemptionCourseReplaceItemService exemptionCourseReplaceItemService;
	@GetMapping()
	@RequiresPermissions("school:exemptionCourseReplace:exemptionCourseReplace")
	String ExemptionCourseReplaceItem(){
	    return "school/school/courseExemption/exemptionCourseReplaceItem";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:exemptionCourseReplace:exemptionCourseReplace")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ExemptionCourseReplaceItemDO> exemptionCourseReplaceItemList = exemptionCourseReplaceItemService.list(query);
        for (ExemptionCourseReplaceItemDO item : exemptionCourseReplaceItemList) {
			item.setAuditStatus(FieldDictUtil.get(getAppName(),"stu_score_replace","audit_status",item.getAuditStatus()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = exemptionCourseReplaceItemService.count(query);
		PageUtils pageUtils = new PageUtils(exemptionCourseReplaceItemList, total);
		return pageUtils;
	}


	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:exemptionCourseReplace:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExemptionCourseReplaceItemDO exemptionCourseReplaceItem = exemptionCourseReplaceItemService.get(id);
		model.addAttribute("exemptionCourseReplaceItem", exemptionCourseReplaceItem);
	    return "school/school/courseExemption/replaceEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:exemptionCourseReplace:add")
	public R save( ExemptionCourseReplaceItemDO exemptionCourseReplaceItem){
		exemptionCourseReplaceItem.setOperator(ShiroUtils.getUserId().toString());
		if(exemptionCourseReplaceItemService.save(exemptionCourseReplaceItem)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:exemptionCourseReplace:edit")
	public R update( ExemptionCourseReplaceItemDO exemptionCourseReplaceItem){
		exemptionCourseReplaceItem.setOperator(ShiroUtils.getUserId().toString());
		exemptionCourseReplaceItemService.update(exemptionCourseReplaceItem);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:exemptionCourseReplace:remove")
	public R remove( Long id){
		if(exemptionCourseReplaceItemService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:exemptionCourseReplace:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		exemptionCourseReplaceItemService.batchRemove(ids);
		return R.ok();
	}
	
}