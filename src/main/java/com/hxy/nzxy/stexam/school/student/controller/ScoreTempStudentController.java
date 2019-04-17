package com.hxy.nzxy.stexam.school.student.controller;

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

import com.hxy.nzxy.stexam.domain.ScoreTempDO;
import com.hxy.nzxy.stexam.school.student.service.ScoreTempStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 登分表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/scoreTempStudent")
public class ScoreTempStudentController extends SystemBaseController{
	@Autowired
	private ScoreTempStudentService scoreTempStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreTempStudent:scoreTempStudent")
	String ScoreTempStudent(){
	    return "school/student/scoreTempStudent/scoreTempStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreTempStudent:scoreTempStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreTempDO> scoreTempStudentList = scoreTempStudentService.list(query);
        for (ScoreTempDO item : scoreTempStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreTempStudentService.count(query);
		PageUtils pageUtils = new PageUtils(scoreTempStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreTempStudent:add")
	String add(Model model){
	    return "school/student/scoreTempStudent/add";
	}

	@GetMapping("/edit/{seatArrangeid}")
	@RequiresPermissions("student:scoreTempStudent:edit")
	String edit(@PathVariable("seatArrangeid") Long seatArrangeid,Model model){
		ScoreTempDO scoreTempStudent = scoreTempStudentService.get(seatArrangeid);
		model.addAttribute("scoreTempStudent", scoreTempStudent);
	    return "school/student/scoreTempStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreTempStudent:add")
	public R save( ScoreTempDO scoreTempStudent){
        scoreTempStudent.setOperator(ShiroUtils.getUserId().toString());
		if(scoreTempStudentService.save(scoreTempStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreTempStudent:edit")
	public R update( ScoreTempDO scoreTempStudent){
	    scoreTempStudent.setOperator(ShiroUtils.getUserId().toString());
		scoreTempStudentService.update(scoreTempStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreTempStudent:remove")
	public R remove( Long seatArrangeid){
		if(scoreTempStudentService.remove(seatArrangeid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreTempStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] seatArrangeids){
		scoreTempStudentService.batchRemove(seatArrangeids);
		return R.ok();
	}
	
}
