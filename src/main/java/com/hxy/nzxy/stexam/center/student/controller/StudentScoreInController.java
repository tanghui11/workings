package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.student.service.StudentScoreInService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.StudentScoreInDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * 转入成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentScoreIn")
public class StudentScoreInController extends SystemBaseController{
	@Autowired
	private StudentScoreInService studentScoreInService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentScoreIn:studentScoreIn")
	String StudentScoreIn(){
	    return "center/student/studentScoreIn/studentScoreIn";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentScoreIn:studentScoreIn")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentScoreInDO> studentScoreInList = studentScoreInService.list(query);
        for (StudentScoreInDO item : studentScoreInList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));

		}
		int total = studentScoreInService.count(query);
		PageUtils pageUtils = new PageUtils(studentScoreInList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("student:studentScoreIn:add")
	String add(Model model){
	    return "center/student/studentScoreIn/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentScoreIn:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentScoreInDO studentScoreIn = studentScoreInService.get(id);
		studentScoreIn.setCourseName("("+studentScoreIn.getCourseid()+")"+FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", studentScoreIn.getCourseid()));
		model.addAttribute("studentScoreIn", studentScoreIn);
	    return "center/student/studentScoreIn/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentScoreIn:add")
	public R save( StudentScoreInDO studentScoreIn){
        studentScoreIn.setOperator(ShiroUtils.getUserId().toString());
		if(studentScoreInService.save(studentScoreIn)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentScoreIn:edit")
	public R update( StudentScoreInDO studentScoreIn){
	    studentScoreIn.setOperator(ShiroUtils.getUserId().toString());
		studentScoreInService.update(studentScoreIn);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentScoreIn:remove")
	public R remove( Long id){
		if(studentScoreInService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentScoreIn:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentScoreInService.batchRemove(ids);
		return R.ok();
	}

}
