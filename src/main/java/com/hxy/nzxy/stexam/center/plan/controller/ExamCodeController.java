package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.ExamCodeService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.PageUtils;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.common.utils.R;
import com.hxy.nzxy.stexam.domain.ExamCodeDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 准考证流水号
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
 
@Controller
@RequestMapping("/plan/examCode")
public class ExamCodeController extends SystemBaseController{
	@Autowired
	private ExamCodeService examCodeService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("plan:examCode:examCode")
	String ExamCode(){
	    return "center/plan/examCode/examCode";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:examCode:examCode")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ExamCodeDO> examCodeList = examCodeService.list(query);
        for (ExamCodeDO item : examCodeList) {

        }
		int total = examCodeService.count(query);
		PageUtils pageUtils = new PageUtils(examCodeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:examCode:add")
	String add(Model model){
	    return "center/plan/examCode/add";
	}

	@GetMapping("/edit/{fixed}")
	@RequiresPermissions("plan:examCode:edit")
	String edit(@PathVariable("fixed") String fixed,Model model){
		ExamCodeDO examCode = examCodeService.get(fixed);
		model.addAttribute("examCode", examCode);
	    return "center/plan/examCode/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:examCode:add")
	public R save( ExamCodeDO examCode){

		if(examCodeService.save(examCode)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:examCode:edit")
	public R update( ExamCodeDO examCode){

		examCodeService.update(examCode);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:examCode:remove")
	public R remove( String fixed){
		if(examCodeService.remove(fixed)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:examCode:batchRemove")
	public R remove(@RequestParam("ids[]") String[] fixeds){
		examCodeService.batchRemove(fixeds);
		return R.ok();
	}
	
}
