package com.hxy.nzxy.stexam.center.region.controller;

import com.hxy.nzxy.stexam.center.region.service.StudentOutService;
import com.hxy.nzxy.stexam.center.student.service.impl.StudentSpecialityServiceImpl;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.StudentOutSpecialityDO;
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
 * 考绩转出
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:19
 */
 
@Controller
@RequestMapping("/region/studentOutSpeciality")
public class StudentOutSpecialityController extends SystemBaseController{
	@Autowired
	private StudentOutService studentOutService;
    @Autowired
    private CommonService commonService;
    private StudentSpecialityServiceImpl studentSpecialityService;

    @GetMapping()
	@RequiresPermissions("region:studentOutSpeciality:studentOutSpeciality")
	String StudentOut(){
	    return "center/region/studentOutSpeciality/studentOutSpeciality";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:studentOutSpeciality:studentOutSpeciality")
	public PageUtils studentOutSpecialityList(@RequestParam Map<String, Object> params ){
		//查询列表数据
        Query query = new Query(params);

		List<StudentOutSpecialityDO> studentOutSpecialityList = studentOutService.studentOutSpecialityList(query);
        for (StudentOutSpecialityDO item : studentOutSpecialityList) {
        	item.setStudentid(params.get("studentid").toString());
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
			item.setStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "status", item.getStatus()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "audit_status", item.getAuditStatus()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentOutService.studentOutSpecialityCount(query);
		PageUtils pageUtils = new PageUtils(studentOutSpecialityList, total);
		return pageUtils;
	}


}