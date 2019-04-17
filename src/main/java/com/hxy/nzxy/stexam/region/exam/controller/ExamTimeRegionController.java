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

import com.hxy.nzxy.stexam.domain.ExamTimeDO;
import com.hxy.nzxy.stexam.region.exam.service.ExamTimeRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考试时间
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:22
 */
 
@Controller
@RequestMapping("/exam/examTimeRegion")
public class ExamTimeRegionController extends SystemBaseController{
	@Autowired
	private ExamTimeRegionService examTimeRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("exam:examTimeRegion:examTimeRegion")
	String ExamTimeRegion(){
	    return "region/exam/examTimeRegion/examTimeRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exam:examTimeRegion:examTimeRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ExamTimeDO> examTimeRegionList = examTimeRegionService.list(query);
        for (ExamTimeDO item : examTimeRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = examTimeRegionService.count(query);
		PageUtils pageUtils = new PageUtils(examTimeRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exam:examTimeRegion:add")
	String add(Model model){
	    return "region/exam/examTimeRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:examTimeRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExamTimeDO examTimeRegion = examTimeRegionService.get(id);
		model.addAttribute("examTimeRegion", examTimeRegion);
	    return "region/exam/examTimeRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:examTimeRegion:add")
	public R save( ExamTimeDO examTimeRegion){
        examTimeRegion.setOperator(ShiroUtils.getUserId().toString());
		if(examTimeRegionService.save(examTimeRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exam:examTimeRegion:edit")
	public R update( ExamTimeDO examTimeRegion){
	    examTimeRegion.setOperator(ShiroUtils.getUserId().toString());
		examTimeRegionService.update(examTimeRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exam:examTimeRegion:remove")
	public R remove( Long id){
		if(examTimeRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exam:examTimeRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		examTimeRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
