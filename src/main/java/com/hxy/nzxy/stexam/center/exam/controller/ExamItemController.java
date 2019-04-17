package com.hxy.nzxy.stexam.center.exam.controller;

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

import com.hxy.nzxy.stexam.domain.ExamItemDO;
import com.hxy.nzxy.stexam.center.exam.service.ExamItemService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考试项目
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
 
@Controller
@RequestMapping("/exam/examItem")
public class ExamItemController extends SystemBaseController{
	@Autowired
	private ExamItemService examItemService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("exam:examItem:examItem")
	String ExamItem(){
	    return "center/exam/examItem/examItem";
	}
	@GetMapping("/openExamItem")
	String openExamItem(){
		return "center/exam/examItem/openExamItem";
	}
	@ResponseBody
	@GetMapping("/listopen")
	public PageUtils listopen(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ExamItemDO> examItemList = examItemService.list(query);
		for (ExamItemDO item : examItemList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setExamMonth(FieldDictUtil.get(getAppName(), "exa_exam_item", "exam_month",item.getExamMonth()));
		}
		int total = examItemService.count(query);
		PageUtils pageUtils = new PageUtils(examItemList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exam:examItem:examItem")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ExamItemDO> examItemList = examItemService.list(query);
        for (ExamItemDO item : examItemList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setExamMonth(FieldDictUtil.get(getAppName(), "exa_exam_item", "exam_month",item.getExamMonth()));
        }
		int total = examItemService.count(query);
		PageUtils pageUtils = new PageUtils(examItemList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exam:examItem:add")
	String add(Model model){
		model.addAttribute("exa_exam_item_exam_month", commonService.listFieldDict(getAppName(), "exa_exam_item", "exam_month"));
	    return "center/exam/examItem/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:examItem:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExamItemDO examItem = examItemService.get(id);
		model.addAttribute("examItem", examItem);
		model.addAttribute("exa_exam_item_exam_month", commonService.listFieldDict(getAppName(), "exa_exam_item", "exam_month"));
	    return "center/exam/examItem/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:examItem:add")
	public R save( ExamItemDO examItem){
        examItem.setOperator(ShiroUtils.getUserId().toString());
		if(examItemService.save(examItem)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exam:examItem:edit")
	public R update( ExamItemDO examItem){
	    examItem.setOperator(ShiroUtils.getUserId().toString());
		examItemService.update(examItem);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exam:examItem:remove")
	public R remove( Long id){
		if(examItemService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exam:examItem:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		examItemService.batchRemove(ids);
		return R.ok();
	}
	
}
