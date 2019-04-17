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

import com.hxy.nzxy.stexam.domain.ScoreReplaceDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreReplaceService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 免考成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/scoreReplace")
public class ScoreReplaceController extends SystemBaseController{
	@Autowired
	private ScoreReplaceService scoreReplaceService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreReplace:scoreReplace")
	String ScoreReplace(Model model){
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
		model.addAttribute("stu_score_replace_audit_status", commonService.listFieldDict(getAppName(), "stu_score_replace", "audit_status"));
	    return "center/student/scoreReplace/scoreReplace";
	}

	@GetMapping("/scoreApply")
	@RequiresPermissions("student:scoreReplace:scoreReplace")
	String courseApply(Model model){
		model.addAttribute("pla_course_free_type", commonService.listFieldDict(getAppName(), "pla_course_free", "type"));
		return "center/student/scoreReplace/scoreApply";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreReplace:scoreReplace")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreReplaceDO> scoreReplaceList = scoreReplaceService.list(query);
        for (ScoreReplaceDO item : scoreReplaceList) {
        	item.setAuditStatus(FieldDictUtil.get(getAppName(), "stu_score_replace", "audit_status", item.getAuditStatus()));
        	item.setStatus(FieldDictUtil.get(getAppName(), "stu_score_replace", "status", item.getStatus()));
        	item.setFreeCourseName(FieldDictUtil.get(getAppName(), "stu_score_replace_nzxy", "id", item.getCourseid()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreReplaceService.count(query);
		PageUtils pageUtils = new PageUtils(scoreReplaceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreReplace:add")
	String add(Model model){
	    return "center/student/scoreReplace/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreReplace:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreReplaceDO scoreReplace = scoreReplaceService.get(id);
		model.addAttribute("scoreReplace", scoreReplace);
	    return "center/student/scoreReplace/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreReplace:add")
	public R save( ScoreReplaceDO scoreReplace){
        scoreReplace.setOperator(ShiroUtils.getUserId().toString());
		if(scoreReplaceService.save(scoreReplace)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreReplace:edit")
	public R update( ScoreReplaceDO scoreReplace){
	    scoreReplace.setOperator(ShiroUtils.getUserId().toString());
		scoreReplaceService.update(scoreReplace);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreReplace:remove")
	public R remove( Long id){
		if(scoreReplaceService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreReplace:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreReplaceService.batchRemove(ids);
		return R.ok();
	}
	/**
	 * 审核
	 */
	@ResponseBody
	@RequestMapping("/updateStatus")
	@RequiresPermissions("student:scoreReplace:edit")
	public R updateStatus( @RequestParam Map<String, Object> params){
		params.put("operator",ShiroUtils.getUserId().toString());
		String msg="";
		int i = scoreReplaceService.updateStatus(params);
		if (i > 0){
			msg = "审核成功！";
			return R.ok();
		}else{
			msg = "审核失败";
			return R.error(0,msg);
		}

	}

}
