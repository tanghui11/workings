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

import com.hxy.nzxy.stexam.domain.StudentLogDO;
import com.hxy.nzxy.stexam.center.student.service.StudentLogService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生变更日志
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentLog")
public class StudentLogController extends SystemBaseController{
	@Autowired
	private StudentLogService studentLogService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentLog:studentLog")
	String StudentLog(){
	    return "center/student/studentLog/studentLog";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentLog:studentLog")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentLogDO> studentLogList = studentLogService.list(query);
        for (StudentLogDO item : studentLogList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentLogService.count(query);
		PageUtils pageUtils = new PageUtils(studentLogList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentLog:add")
	String add(Model model){
	    return "center/student/studentLog/add";
	}

	@GetMapping("/edit/{changeType}")
	@RequiresPermissions("student:studentLog:edit")
	String edit(@PathVariable("changeType") String changeType,Model model){
		StudentLogDO studentLog = studentLogService.get(changeType);
		model.addAttribute("studentLog", studentLog);
	    return "center/student/studentLog/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentLog:add")
	public R save( StudentLogDO studentLog){
        studentLog.setOperator(ShiroUtils.getUserId().toString());
		if(studentLogService.save(studentLog)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentLog:edit")
	public R update( StudentLogDO studentLog){
	    studentLog.setOperator(ShiroUtils.getUserId().toString());
		studentLogService.update(studentLog);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentLog:remove")
	public R remove( String changeType){
		if(studentLogService.remove(changeType)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentLog:batchRemove")
	public R remove(@RequestParam("ids[]") String[] changeTypes){
		studentLogService.batchRemove(changeTypes);
		return R.ok();
	}
	
}
