package com.hxy.nzxy.stexam.center.student.controller;

import java.util.*;

import com.hxy.nzxy.stexam.center.student.service.ScoreService;
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

import com.hxy.nzxy.stexam.domain.ScoreOldDO;
import com.hxy.nzxy.stexam.center.student.service.ScoreOldService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 老课程成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:24
 */
 
@Controller
@RequestMapping("/student/scoreOld")
public class ScoreOldController extends SystemBaseController{
	@Autowired
	private ScoreOldService scoreOldService;
    @Autowired
    private CommonService commonService;
    @Autowired
	private ScoreService scoreService;
	@GetMapping()
	@RequiresPermissions("student:scoreOld:scoreOld")
	String ScoreOld(){
	    return "center/student/scoreOld/scoreOld";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreOld:scoreOld")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreOldDO> scoreOldList = scoreOldService.list(query);
        for (ScoreOldDO item : scoreOldList) {
        	item.setCourseName(FieldDictUtil.get(getAppName(), "pla_old_course_nzxy", "id", item.getCourseid()));
        	item.setStatus(FieldDictUtil.get(getAppName(), "stu_score_old", "status", item.getStatus()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreOldService.count(query);
		PageUtils pageUtils = new PageUtils(scoreOldList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreOld:add")
	String add(Model model){
	    return "center/student/scoreOld/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreOld:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreOldDO scoreOld = scoreOldService.get(id);
		scoreOld.setCourseName(FieldDictUtil.get(getAppName(), "pla_old_course_nzxy", "id", scoreOld.getCourseid()));
		model.addAttribute("scoreOld", scoreOld);
	    return "center/student/scoreOld/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreOld:add")
	public R save( ScoreOldDO scoreOld){
        scoreOld.setOperator(ShiroUtils.getUserId().toString());
		if(scoreOldService.save(scoreOld)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreOld:edit")
	public R update( ScoreOldDO scoreOld,String specialityRecordid){
	    scoreOld.setOperator(ShiroUtils.getUserId().toString());
		scoreOldService.update(scoreOld);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("studentid",scoreOld.getStudentid());
		map.put("courseid",scoreOld.getCourseid());
		map.put("specialityRecordid",specialityRecordid);
		map.put("type","h");
		scoreOldService.updateScore(map);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreOld:remove")
	public R remove( Long id){
		if(scoreOldService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreOld:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreOldService.batchRemove(ids);
		return R.ok();
	}

	@GetMapping("/addRegion")
	@RequiresPermissions("student:scoreOld:add")
	String addRegion(Model model){
		return "region/student/scoreOld/add";
	}
	
}
