package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.student.domain.FileInfoQueryDO;
import com.hxy.nzxy.stexam.center.student.service.FileInfoQueryService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 归档信息查询
 * 
 * @author ypp
 * @email
 * @date 2018-08-07 17:07:24
 */
 
@Controller
@RequestMapping("/student/fileInfoQuery")
public class FileInfoQueryController extends SystemBaseController{
	@Autowired
	private FileInfoQueryService fileInfoQueryService;
	@GetMapping()
	@RequiresPermissions("student:fileInfoQuery:fileInfoQuery")
	String fileInfoQuery(){
	    return "center/student/fileInfoQuery/fileInfoQuery";
	}

	@GetMapping("/listDiplomahtml")
	@RequiresPermissions("student:fileInfoQuery:fileInfoQuery")
	String listDiploma(){
		return "center/student/fileInfoQuery/diploma";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:fileInfoQuery:fileInfoQuery")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FileInfoQueryDO> fileInfoQueryServiceList = fileInfoQueryService.list(query);
        for (FileInfoQueryDO item : fileInfoQueryServiceList) {

			item.setAuditStatus(FieldDictUtil.get(getAppName(),"stu_student_speciality","status",item.getAuditStatus()));
			item.setGradAuditOperator(FieldDictUtil.get(getAppName(),"stu_student_speciality","grad_audit_operator",item.getGradAuditOperator()));
        }
		int total = fileInfoQueryService.count(query);
		PageUtils pageUtils = new PageUtils(fileInfoQueryServiceList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/listDiploma")
	@RequiresPermissions("student:fileInfoQuery:fileInfoQuery")
	public PageUtils listDiploma(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<FileInfoQueryDO> fileInfoQueryServiceList = fileInfoQueryService.listDiploma(query);
		for (FileInfoQueryDO item : fileInfoQueryServiceList) {

		}
		int total = fileInfoQueryService.countDiploma(query);
		PageUtils pageUtils = new PageUtils(fileInfoQueryServiceList, total);
		return pageUtils;
	}

	/**
	 * 取消归档
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:fileInfoQuery:add")
	public R save( FileInfoQueryDO fileInfoQuery){
		fileInfoQuery.setOperator(ShiroUtils.getUserId().toString());
		if(fileInfoQueryService.save(fileInfoQuery)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:fileInfoQuery:edit")
	public R update( FileInfoQueryDO fileInfoQuery){
		fileInfoQuery.setOperator(ShiroUtils.getUserId().toString());
		fileInfoQueryService.update(fileInfoQuery);
		return R.ok();
	}


}