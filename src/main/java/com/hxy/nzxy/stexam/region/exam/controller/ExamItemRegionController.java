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

import com.hxy.nzxy.stexam.domain.ExamItemDO;
import com.hxy.nzxy.stexam.region.exam.service.ExamItemRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考试项目
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-11 10:32:22
 */
 
@Controller
@RequestMapping("/exam/examItemRegion")
public class ExamItemRegionController extends SystemBaseController{
	@Autowired
	private ExamItemRegionService examItemRegionService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("exam:examItemRegion:examItemRegion")
	String ExamItemRegion(){
	    return "region/exam/examItemRegion/examItemRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exam:examItemRegion:examItemRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ExamItemDO> examItemRegionList = examItemRegionService.list(query);
        for (ExamItemDO item : examItemRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = examItemRegionService.count(query);
		PageUtils pageUtils = new PageUtils(examItemRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exam:examItemRegion:add")
	String add(Model model){
	    return "region/exam/examItemRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:examItemRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExamItemDO examItemRegion = examItemRegionService.get(id);
		model.addAttribute("examItemRegion", examItemRegion);
	    return "region/exam/examItemRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:examItemRegion:add")
	public R save( ExamItemDO examItemRegion){
        examItemRegion.setOperator(ShiroUtils.getUserId().toString());
		if(examItemRegionService.save(examItemRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exam:examItemRegion:edit")
	public R update( ExamItemDO examItemRegion){
	    examItemRegion.setOperator(ShiroUtils.getUserId().toString());
		examItemRegionService.update(examItemRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exam:examItemRegion:remove")
	public R remove( Long id){
		if(examItemRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exam:examItemRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		examItemRegionService.batchRemove(ids);
		return R.ok();
	}
	
}
