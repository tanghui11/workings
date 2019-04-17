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

import com.hxy.nzxy.stexam.domain.SchoolScoreDO;
import com.hxy.nzxy.stexam.school.student.service.SchoolScoreStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 平时成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
 
@Controller
@RequestMapping("/student/schoolScoreStudent")
public class SchoolScoreStudentController extends SystemBaseController{
	@Autowired
	private SchoolScoreStudentService schoolScoreStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping("/schoolScore")
	@RequiresPermissions("student:schoolScoreStudent:schoolScoreStudent")
	String schoolScore(){
	    return "school/student/schoolScoreStudent/schoolScoreStudent";
	}

	@GetMapping("/courseInfo")
	@RequiresPermissions("student:schoolScoreStudent:schoolScoreStudent")
	String courseInfo(){
		return "school/student/schoolScoreStudent/specialityCourse";
	}

	@GetMapping("/studentInfo")
	@RequiresPermissions("student:schoolScoreStudent:schoolScoreStudent")
	String studentInfo(){
		return "school/student/schoolScoreStudent/studentFileStudent";
	}
	@GetMapping("/schoolScoreList")
	@RequiresPermissions("student:schoolScoreStudent:schoolScoreStudent")
	String schoolScoreList(){
		return "school/student/schoolScoreStudent/schoolScoreList";
	}
	//平时成绩
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:schoolScoreStudent:schoolScoreStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SchoolScoreDO> schoolScoreStudentList = schoolScoreStudentService.list(query);
        for (SchoolScoreDO item : schoolScoreStudentList) {
        	item.setStatus(FieldDictUtil.get(getAppName(), "stu_school_score", "status", item.getStatus()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = schoolScoreStudentService.count(query);
		PageUtils pageUtils = new PageUtils(schoolScoreStudentList, total);
		return pageUtils;
	}


	@GetMapping("/add")
	@RequiresPermissions("student:schoolScoreStudent:add")
	String add(Model model){
	    return "school/student/schoolScoreStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:schoolScoreStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SchoolScoreDO schoolScoreStudent = schoolScoreStudentService.get(id);
		model.addAttribute("schoolScoreStudent", schoolScoreStudent);
	    return "school/student/schoolScoreStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:schoolScoreStudent:add")
	public R save( SchoolScoreDO schoolScoreStudent){
        schoolScoreStudent.setOperator(ShiroUtils.getUserId().toString());
		schoolScoreStudent.setStatus("a");
		schoolScoreStudent.setAuditStatus("a");
		if(schoolScoreStudentService.save(schoolScoreStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:schoolScoreStudent:edit")
	public R update( SchoolScoreDO schoolScoreStudent){
	    schoolScoreStudent.setOperator(ShiroUtils.getUserId().toString());
		schoolScoreStudentService.update(schoolScoreStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:schoolScoreStudent:remove")
	public R remove( Long id){
		if(schoolScoreStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:schoolScoreStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		schoolScoreStudentService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 分数录入
	 * 参数需要包括
	 * 准考证号、studentid
	 * 课程代码、courseid
	 * 成绩、grade
	 * 专业代码	specialityid
	 * 主考院校编号	schoolid
	 * 主考学院编号	collegeid
	 * 专业方向	direction
	 */
	@PostMapping( "/scoreIn")
	@ResponseBody
	@RequiresPermissions("student:schoolScoreStudent:batchRemove")
	public R scoreIn(@RequestParam Map<String,String> map){
		String message = "";
		String operator = ShiroUtils.getUserId().toString();
		map.put("operator", operator);
//		if(schoolScoreStudentService.whetherAreadyIn(map) != 0){
//
//		}
		int i = schoolScoreStudentService.inPutGrade(map);
		if (i != 0){
			message = "分数录入成功";
		}

		return R.ok(message);
	}
}
