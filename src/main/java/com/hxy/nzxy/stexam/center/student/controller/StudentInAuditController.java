package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.student.service.StudentInService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.StudentInDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 考生转入考籍
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentInAudit")
public class StudentInAuditController extends SystemBaseController{
	@Autowired
	private StudentInService studentInService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentInAudit:studentInAudit")
	String StudentIn(){
	    return "center/student/studentInAudit/studentInAudit";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentInAudit:studentInAudit")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentInDO> studentInList = studentInService.list(query);
        for (StudentInDO item : studentInList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
            item.setStatus(FieldDictUtil.get(getAppName(),"stu_student_in","status",item.getStatus()));
			item.setGender(FieldDictUtil.get(getAppName(),"sys_worker","gender",item.getGender()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(),"stu_student_in","audit_status",item.getAuditStatus()));
        }
		int total = studentInService.count(query);
		PageUtils pageUtils = new PageUtils(studentInList, total);
		return pageUtils;
	}

/**
 *功能描述 审核
 * @author ypp
 * @date 2018/11/21
 * @param
 * @return com.hxy.nzxy.stexam.common.utils.R
 */
	@PostMapping( "/auditStatus")
	@ResponseBody
	@RequiresPermissions("student:studentInAudit:audit")
	public R auditStatus(@RequestParam("id") Long id){
		if (studentInService.auditStatus(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 *功能描述  取消审核
	 * @author ypp
	 * @date 2018/11/21
	 * @param
	 * @return com.hxy.nzxy.stexam.common.utils.R
	 */
	@PostMapping( "/cancelAuditStatus")
	@ResponseBody
	@RequiresPermissions("student:studentInAudit:cancleAudit")
	public R cancelAuditStatus(@RequestParam("id") Long id){
		if (studentInService.cancelAuditStatus(id) > 0) {
				return R.ok();
		}
		return R.error();
	}
}