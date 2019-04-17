package com.hxy.nzxy.stexam.school.school.controller;

import com.hxy.nzxy.stexam.center.student.service.StudentInService;
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
@RequestMapping("/school/studentInMigration")
public class StudentInMigrationController extends SystemBaseController{
	@Autowired
	private StudentInService studentInService;
	@GetMapping()
	@RequiresPermissions("school:studentInMigration:studentInMigration")
	String StudentIn(){
	    return "school/school/studentInMigration/studentInMigration";
	}
	
	@ResponseBody
	@GetMapping("/migrationList")
	@RequiresPermissions("school:studentInMigration:studentInMigration")
	public PageUtils migrationList(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentInDO> studentInList = studentInService.migrationList(query);
        for (StudentInDO item : studentInList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
            item.setStatus(FieldDictUtil.get(getAppName(),"stu_student_in","status",item.getStatus()));
			item.setGender(FieldDictUtil.get(getAppName(),"sys_worker","gender",item.getGender()));

		}
		int total = studentInService.migrationCount(query);
		PageUtils pageUtils = new PageUtils(studentInList, total);
		return pageUtils;
	}

/**
 *功能描述 调档
 * @author ypp
 * @date 2018/12/7
 * @param
 * @return com.hxy.nzxy.stexam.common.utils.R
 */
	@PostMapping( "/migration")
	@ResponseBody
	@RequiresPermissions("school:studentInMigration:studentInMigration")
	public R migration(@RequestParam("id") Long id){
		studentInService.migration(id);
		return R.ok();
	}
}