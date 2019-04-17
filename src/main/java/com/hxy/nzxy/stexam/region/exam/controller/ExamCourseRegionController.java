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

import com.hxy.nzxy.stexam.domain.ExamCourseDO;
import com.hxy.nzxy.stexam.region.exam.service.ExamCourseRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 开考课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:22
 */
 
@Controller
@RequestMapping("/exam/examCourseRegion")
public class ExamCourseRegionController extends SystemBaseController{
	@Autowired
	private ExamCourseRegionService examCourseRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("exam:examCourseRegion:examCourseRegion")
	String ExamCourseRegion(){
	    return "region/exam/examCourseRegion/examCourseRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exam:examCourseRegion:examCourseRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ExamCourseDO> examCourseRegionList = examCourseRegionService.list(query);
        for (ExamCourseDO item : examCourseRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = examCourseRegionService.count(query);
		PageUtils pageUtils = new PageUtils(examCourseRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exam:examCourseRegion:add")
	String add(Model model){
	    return "region/exam/examCourseRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:examCourseRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExamCourseDO examCourseRegion = examCourseRegionService.get(id);
		model.addAttribute("examCourseRegion", examCourseRegion);
	    return "region/exam/examCourseRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:examCourseRegion:add")
	public R save( ExamCourseDO examCourseRegion){
        examCourseRegion.setOperator(ShiroUtils.getUserId().toString());
		if(examCourseRegionService.save(examCourseRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exam:examCourseRegion:edit")
	public R update( ExamCourseDO examCourseRegion){
	    examCourseRegion.setOperator(ShiroUtils.getUserId().toString());
		examCourseRegionService.update(examCourseRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exam:examCourseRegion:remove")
	public R remove( Long id){
		if(examCourseRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exam:examCourseRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		examCourseRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
