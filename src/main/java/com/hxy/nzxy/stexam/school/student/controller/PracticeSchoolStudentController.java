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

import com.hxy.nzxy.stexam.domain.PracticeSchoolDO;
import com.hxy.nzxy.stexam.school.student.service.PracticeSchoolStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 实践成绩
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
 
@Controller
@RequestMapping("/student/practiceSchoolStudent")
public class PracticeSchoolStudentController extends SystemBaseController{
	@Autowired
	private PracticeSchoolStudentService practiceSchoolStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:practiceSchoolStudent:practiceSchoolStudent")
	String PracticeSchoolStudent(){
	    return "school/student/practiceSchoolStudent/practiceSchoolStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:practiceSchoolStudent:practiceSchoolStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PracticeSchoolDO> practiceSchoolStudentList = practiceSchoolStudentService.list(query);
        for (PracticeSchoolDO item : practiceSchoolStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = practiceSchoolStudentService.count(query);
		PageUtils pageUtils = new PageUtils(practiceSchoolStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:practiceSchoolStudent:add")
	String add(Model model){
	    return "school/student/practiceSchoolStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:practiceSchoolStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		PracticeSchoolDO practiceSchoolStudent = practiceSchoolStudentService.get(id);
		model.addAttribute("practiceSchoolStudent", practiceSchoolStudent);
	    return "school/student/practiceSchoolStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:practiceSchoolStudent:add")
	public R save( PracticeSchoolDO practiceSchoolStudent){
        practiceSchoolStudent.setOperator(ShiroUtils.getUserId().toString());
		if(practiceSchoolStudentService.save(practiceSchoolStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:practiceSchoolStudent:edit")
	public R update( PracticeSchoolDO practiceSchoolStudent){
	    practiceSchoolStudent.setOperator(ShiroUtils.getUserId().toString());
		practiceSchoolStudentService.update(practiceSchoolStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:practiceSchoolStudent:remove")
	public R remove( Long id){
		if(practiceSchoolStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:practiceSchoolStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		practiceSchoolStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
