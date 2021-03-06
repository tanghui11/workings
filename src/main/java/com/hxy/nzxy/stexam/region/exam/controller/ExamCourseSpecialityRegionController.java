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

import com.hxy.nzxy.stexam.domain.ExamCourseSpecialityDO;
import com.hxy.nzxy.stexam.region.exam.service.ExamCourseSpecialityRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 课程限报专业
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:22
 */
 
@Controller
@RequestMapping("/exam/examCourseSpecialityRegion")
public class ExamCourseSpecialityRegionController extends SystemBaseController{
	@Autowired
	private ExamCourseSpecialityRegionService examCourseSpecialityRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("exam:examCourseSpecialityRegion:examCourseSpecialityRegion")
	String ExamCourseSpecialityRegion(){
	    return "region/exam/examCourseSpecialityRegion/examCourseSpecialityRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exam:examCourseSpecialityRegion:examCourseSpecialityRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ExamCourseSpecialityDO> examCourseSpecialityRegionList = examCourseSpecialityRegionService.list(query);
        for (ExamCourseSpecialityDO item : examCourseSpecialityRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = examCourseSpecialityRegionService.count(query);
		PageUtils pageUtils = new PageUtils(examCourseSpecialityRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exam:examCourseSpecialityRegion:add")
	String add(Model model){
	    return "region/exam/examCourseSpecialityRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:examCourseSpecialityRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExamCourseSpecialityDO examCourseSpecialityRegion = examCourseSpecialityRegionService.get(id);
		model.addAttribute("examCourseSpecialityRegion", examCourseSpecialityRegion);
	    return "region/exam/examCourseSpecialityRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:examCourseSpecialityRegion:add")
	public R save( ExamCourseSpecialityDO examCourseSpecialityRegion){
        examCourseSpecialityRegion.setOperator(ShiroUtils.getUserId().toString());
		if(examCourseSpecialityRegionService.save(examCourseSpecialityRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exam:examCourseSpecialityRegion:edit")
	public R update( ExamCourseSpecialityDO examCourseSpecialityRegion){
	    examCourseSpecialityRegion.setOperator(ShiroUtils.getUserId().toString());
		examCourseSpecialityRegionService.update(examCourseSpecialityRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exam:examCourseSpecialityRegion:remove")
	public R remove( Long id){
		if(examCourseSpecialityRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exam:examCourseSpecialityRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		examCourseSpecialityRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
