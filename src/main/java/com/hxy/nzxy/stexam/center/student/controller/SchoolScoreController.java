package com.hxy.nzxy.stexam.center.student.controller;

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

import com.hxy.nzxy.stexam.domain.SchoolScoreDO;
import com.hxy.nzxy.stexam.center.student.service.SchoolScoreService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 平时成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
 
@Controller
@RequestMapping("/student/schoolScore")
public class SchoolScoreController extends SystemBaseController{
	@Autowired
	private SchoolScoreService schoolScoreService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:schoolScore:schoolScore")
	String SchoolScore(){
	    return "center/student/schoolScore/schoolScore";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:schoolScore:schoolScore")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SchoolScoreDO> schoolScoreList = schoolScoreService.list(query);
        for (SchoolScoreDO item : schoolScoreList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = schoolScoreService.count(query);
		PageUtils pageUtils = new PageUtils(schoolScoreList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:schoolScore:add")
	String add(Model model){
	    return "center/student/schoolScore/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:schoolScore:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SchoolScoreDO schoolScore = schoolScoreService.get(id);
		model.addAttribute("schoolScore", schoolScore);
	    return "center/student/schoolScore/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:schoolScore:add")
	public R save( SchoolScoreDO schoolScore){
        schoolScore.setOperator(ShiroUtils.getUserId().toString());
		if(schoolScoreService.save(schoolScore)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:schoolScore:edit")
	public R update( SchoolScoreDO schoolScore){
	    schoolScore.setOperator(ShiroUtils.getUserId().toString());
		schoolScoreService.update(schoolScore);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:schoolScore:remove")
	public R remove( Long id){
		if(schoolScoreService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:schoolScore:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		schoolScoreService.batchRemove(ids);
		return R.ok();
	}

}
