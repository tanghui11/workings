package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.student.service.StudentCertificateQueryService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.DateUtils;
import com.hxy.nzxy.stexam.common.utils.PageUtils;
import com.hxy.nzxy.stexam.common.utils.Query;
import com.hxy.nzxy.stexam.common.utils.UserUtil;
import com.hxy.nzxy.stexam.domain.StudentCertificateDO;
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
 * 毕业证书管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentCertificateQuery")
public class StudentCertificateQueryController extends SystemBaseController{
	@Autowired
	private StudentCertificateQueryService studentCertificateQueryService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentCertificateQuery:studentCertificateQuery")
	String StudentCertificate(){
	    return "center/student/studentCertificateQuery/studentCertificateQuery";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCertificateQuery:studentCertificateQuery")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCertificateDO> studentCertificateList = studentCertificateQueryService.list(query);
        for (StudentCertificateDO item : studentCertificateList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCertificateQueryService.count(query);
		PageUtils pageUtils = new PageUtils(studentCertificateList, total);
		return pageUtils;
	}
}