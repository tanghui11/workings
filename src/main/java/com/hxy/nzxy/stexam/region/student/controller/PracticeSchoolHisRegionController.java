package com.hxy.nzxy.stexam.region.student.controller;

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

import com.hxy.nzxy.stexam.domain.PracticeSchoolHisDO;
import com.hxy.nzxy.stexam.region.student.service.PracticeSchoolHisRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 实践成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
 
@Controller
@RequestMapping("/student/practiceSchoolHisRegion")
public class PracticeSchoolHisRegionController extends SystemBaseController{
	@Autowired
	private PracticeSchoolHisRegionService practiceSchoolHisRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:practiceSchoolHisRegion:practiceSchoolHisRegion")
	String PracticeSchoolHisRegion(){
	    return "region/student/practiceSchoolHisRegion/practiceSchoolHisRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:practiceSchoolHisRegion:practiceSchoolHisRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PracticeSchoolHisDO> practiceSchoolHisRegionList = practiceSchoolHisRegionService.list(query);
        for (PracticeSchoolHisDO item : practiceSchoolHisRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = practiceSchoolHisRegionService.count(query);
		PageUtils pageUtils = new PageUtils(practiceSchoolHisRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:practiceSchoolHisRegion:add")
	String add(Model model){
	    return "region/student/practiceSchoolHisRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:practiceSchoolHisRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		PracticeSchoolHisDO practiceSchoolHisRegion = practiceSchoolHisRegionService.get(id);
		model.addAttribute("practiceSchoolHisRegion", practiceSchoolHisRegion);
	    return "region/student/practiceSchoolHisRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:practiceSchoolHisRegion:add")
	public R save( PracticeSchoolHisDO practiceSchoolHisRegion){
        practiceSchoolHisRegion.setOperator(ShiroUtils.getUserId().toString());
		if(practiceSchoolHisRegionService.save(practiceSchoolHisRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:practiceSchoolHisRegion:edit")
	public R update( PracticeSchoolHisDO practiceSchoolHisRegion){
	    practiceSchoolHisRegion.setOperator(ShiroUtils.getUserId().toString());
		practiceSchoolHisRegionService.update(practiceSchoolHisRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:practiceSchoolHisRegion:remove")
	public R remove( Long id){
		if(practiceSchoolHisRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:practiceSchoolHisRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		practiceSchoolHisRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
