package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.student.service.StudentService;
import com.hxy.nzxy.stexam.center.student.service.StudentSpecialityService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.StudentDO;
import com.hxy.nzxy.stexam.domain.StudentSpecialityDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 考生导入
 *
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentImport")
public class StudentImportController extends SystemBaseController{
	@Autowired
	private StudentService studentService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private StudentSpecialityService studentSpecialityService;
	//考生基本信息导入
	@GetMapping("/studentImport")
	@RequiresPermissions("student:studentImport:studentImport")
	String StudentImport(Model model){

		return "center/student/studentImport/StudentImport";
	}
	//考生基本信息入库
	@GetMapping("/studentStore")
	@RequiresPermissions("student:studentStore:studentStore")
	String StudentStore(Model model){

		return "center/student/studentImport/studentStore";
	}
	//考生基本信息补录
	@GetMapping("/studentRepaire")
	@RequiresPermissions("student:studentRepaire:studentRepaire")
	String StudentRepaire(Model model){

		return "center/student/studentImport/studentRepaire";
	}
	//报考数据导入
	@GetMapping("/studentExamImport")
	@RequiresPermissions("student:StudentExamImport:StudentExamImport")
	String StudentExamImport(Model model){

		return "center/student/studentImport/studentExamImport";
	}
	//报考数据入库
	@GetMapping("/studentExamStore")
	@RequiresPermissions("student:studentExamStore:studentExamStore")
	String StudentExamStore(Model model){

		return "center/student/studentImport/studentExamStore";
	}


}
