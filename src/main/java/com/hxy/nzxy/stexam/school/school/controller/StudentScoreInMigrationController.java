package com.hxy.nzxy.stexam.school.school.controller;

import com.hxy.nzxy.stexam.center.student.service.StudentScoreInService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.DateUtils;
import com.hxy.nzxy.stexam.common.utils.PageUtils;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.common.utils.UserUtil;
import com.hxy.nzxy.stexam.domain.StudentScoreInDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/school/studentScoreInMigration")
public class StudentScoreInMigrationController extends SystemBaseController{
	@Autowired
	private StudentScoreInService studentScoreInService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("school:studentScoreInMigration:studentScoreInMigration")
	String StudentScoreIn(){
	    return "school/school/studentInMigration/studentScoreInMigration";
	}
	
	@ResponseBody
	@GetMapping("/migrationList")
	@RequiresPermissions("school:studentScoreInMigration:studentScoreInMigration")
	public PageUtils migrationList(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentScoreInDO> studentScoreInList = studentScoreInService.migrationList(query);
        for (StudentScoreInDO item : studentScoreInList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentScoreInService.migrationCount(query);
		PageUtils pageUtils = new PageUtils(studentScoreInList, total);
		return pageUtils;
	}
}