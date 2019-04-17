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

import com.hxy.nzxy.stexam.domain.PracticeSchoolHisDO;
import com.hxy.nzxy.stexam.school.student.service.PracticeSchoolHisStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 实践成绩_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
 
@Controller
@RequestMapping("/student/practiceSchoolHisStudent")
public class PracticeSchoolHisStudentController extends SystemBaseController{
	@Autowired
	private PracticeSchoolHisStudentService practiceSchoolHisStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:practiceSchoolHisStudent:practiceSchoolHisStudent")
	String PracticeSchoolHisStudent(){
	    return "school/student/practiceSchoolHisStudent/practiceSchoolHisStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:practiceSchoolHisStudent:practiceSchoolHisStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PracticeSchoolHisDO> practiceSchoolHisStudentList = practiceSchoolHisStudentService.list(query);
        for (PracticeSchoolHisDO item : practiceSchoolHisStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = practiceSchoolHisStudentService.count(query);
		PageUtils pageUtils = new PageUtils(practiceSchoolHisStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:practiceSchoolHisStudent:add")
	String add(Model model){
	    return "school/student/practiceSchoolHisStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:practiceSchoolHisStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		PracticeSchoolHisDO practiceSchoolHisStudent = practiceSchoolHisStudentService.get(id);
		model.addAttribute("practiceSchoolHisStudent", practiceSchoolHisStudent);
	    return "school/student/practiceSchoolHisStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:practiceSchoolHisStudent:add")
	public R save( PracticeSchoolHisDO practiceSchoolHisStudent){
        practiceSchoolHisStudent.setOperator(ShiroUtils.getUserId().toString());
		if(practiceSchoolHisStudentService.save(practiceSchoolHisStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:practiceSchoolHisStudent:edit")
	public R update( PracticeSchoolHisDO practiceSchoolHisStudent){
	    practiceSchoolHisStudent.setOperator(ShiroUtils.getUserId().toString());
		practiceSchoolHisStudentService.update(practiceSchoolHisStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:practiceSchoolHisStudent:remove")
	public R remove( Long id){
		if(practiceSchoolHisStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:practiceSchoolHisStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		practiceSchoolHisStudentService.batchRemove(ids);
		return R.ok();
	}
	
}
