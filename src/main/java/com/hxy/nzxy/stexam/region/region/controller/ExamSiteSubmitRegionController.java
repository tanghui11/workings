package com.hxy.nzxy.stexam.region.region.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.exam.service.TaskService;
import com.hxy.nzxy.stexam.domain.ExamSiteDO;
import com.hxy.nzxy.stexam.domain.TaskDO;
import com.hxy.nzxy.stexam.region.region.service.ExamSiteRegionService;
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

import com.hxy.nzxy.stexam.domain.ExamSiteSubmitDO;
import com.hxy.nzxy.stexam.region.region.service.ExamSiteSubmitRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考点上报
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:10:33
 */
 
@Controller
@RequestMapping("/region/examSiteSubmitRegion")
public class ExamSiteSubmitRegionController extends SystemBaseController{
	@Autowired
	private ExamSiteSubmitRegionService examSiteSubmitRegionService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private ExamSiteRegionService examSiteRegionService;
	@Autowired
	private TaskService taskService;
	@GetMapping()
	@RequiresPermissions("region:examSiteSubmitRegion:examSiteSubmitRegion")
	String ExamSiteSubmitRegion(Model model){
		String regType = ShiroUtils.getUser().getRegType();
		model.addAttribute("examSite",ShiroUtils.getUser().getWorkerid());
		/*model.addAttribute("reg_exam_site_school", commonService.listFieldDict(getAppName(), "reg_exam_site", "school"));
		model.addAttribute("reg_exam_site_standard", commonService.listFieldDict(getAppName(), "reg_exam_site", "standard"));*/
		//model.addAttribute("reg_exam_site_status", commonService.listFieldDict(getAppName(), "reg_exam_site", "status"));
		if (regType.equals("a")){
			return "region/region/examSiteSubmitRegion/examSiteSubmitRegion";
		}else{
			/*ExamSiteDO examSiteRegion = examSiteRegionService.get(Long.valueOf(ShiroUtils.getUser().getWorkerid()));
			model.addAttribute("examSiteRegion", examSiteRegion);*/
			return "region/region/examSiteSubmitRegion/examSiteSubmitRegionXian";
		}
	}
	@GetMapping("/up")
	String ExamSiteSubmitRegionUp(Model model){

			return "region/region/examSiteSubmitRegion/examSiteSubmitRegionUp";

	}

	/**
	 * 考点信息
	 */
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:examSiteSubmitRegion:examSiteSubmitRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		String regionid= String.valueOf(params.get("regionid"));

		if(regionid!=null&&!"".equals(regionid)) {
			List<ExamSiteDO> examSiteList = examSiteRegionService.list(query);
			for (ExamSiteDO item : examSiteList) {
				item.setStatus(FieldDictUtil.get(getAppName(), "reg_exam_site", "status", item.getStatus()));
				item.setOperator(UserUtil.getName(item.getOperator()));
				item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			}
			int total = examSiteRegionService.count(query);
			PageUtils pageUtils = new PageUtils(examSiteList, total);
			return pageUtils;
		}else {
			return new PageUtils(new ArrayList<>(), 0);
		}
	}
	/**
	 * 已上报考点信息
	 */
	@ResponseBody
	@GetMapping("/ylist")
	@RequiresPermissions("region:examSiteSubmitRegion:examSiteSubmitRegion")
	public PageUtils ylist(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		String regionid= String.valueOf(params.get("regionid"));

		if(regionid!=null&&!"".equals(regionid)) {
			List<ExamSiteSubmitDO> examSiteSubmitRegionList = examSiteSubmitRegionService.list(query);
			for (ExamSiteSubmitDO item : examSiteSubmitRegionList) {
				item.setStatus(FieldDictUtil.get(getAppName(), "reg_exam_site_submit", "status", item.getStatus()));
				item.setOperator(UserUtil.getName(item.getOperator()));
				item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			}
			int total = examSiteSubmitRegionService.count(query);
			PageUtils pageUtils = new PageUtils(examSiteSubmitRegionList, total);
			return pageUtils;
		}else {
			return new PageUtils(new ArrayList<>(), 0);
		}
	}

	@GetMapping("/add/{id}")
	@RequiresPermissions("region:examSiteSubmitRegion:add")
	String add(@PathVariable("regionid") Long regionid,Model model){
		/*ExamSiteDO examSiteRegion = examSiteRegionService.get(regionid);
		model.addAttribute("examSiteRegion",examSiteRegion);*/
		//model.addAttribute("reg_exam_site_standard", commonService.listFieldDict(getAppName(), "reg_exam_site", "standard"));
	    return "region/region/examSiteSubmitRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("region:examSiteSubmitRegion:edit")
	String edit(@PathVariable("id") Long id,Model model,@RequestParam Map<String, Object> params){
		ExamSiteDO examSiteRegion = examSiteRegionService.get(id);

		List<TaskDO> taskList = taskService.taskList(params);
		for (TaskDO item : taskList) {
			item.setExamMonth(FieldDictUtil.get(getAppName(), "exam_task", "exam_month", item.getExamMonth()));
			item.setExamName(item.getExamYear()+"  "+item.getExamMonth());
		}

		model.addAttribute("taskList",taskList);
		model.addAttribute("examSiteRegion", examSiteRegion);
	    return "region/region/examSiteSubmitRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:examSiteSubmitRegion:add")
	public R save( ExamSiteSubmitDO examSiteSubmitRegion){
        examSiteSubmitRegion.setOperator(ShiroUtils.getUserId().toString());
        examSiteSubmitRegion.setStatus("a");
		if(examSiteSubmitRegionService.save(examSiteSubmitRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:examSiteSubmitRegion:edit")
	public R update( ExamSiteSubmitDO examSiteSubmitRegion){
	    examSiteSubmitRegion.setOperator(ShiroUtils.getUserId().toString());
		examSiteSubmitRegionService.update(examSiteSubmitRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:examSiteSubmitRegion:remove")
	public R remove( Long id){
		if(examSiteSubmitRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:examSiteSubmitRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		examSiteSubmitRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
