package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.student.service.StudentCertificateService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.StudentCertificateDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 *功能描述 毕业证书管理
 * @author ypp
 * @date 2018/12/19
 * @param
 * @return
 */

@Controller
@RequestMapping("/student/studentCertificate")
public class StudentCertificateController extends SystemBaseController{
	@Autowired
	private StudentCertificateService studentCertificateService;
	@Autowired
	private CommonService commonService;

	@GetMapping()
	@RequiresPermissions("student:studentCertificate:studentCertificate")
	String studentCertificate(){
	    return "center/student/studentCertificate/studentCertificate";
	}

	@GetMapping("/listDiplomahtml")
	@RequiresPermissions("student:studentCertificate:studentCertificate")
	String listDiploma(){
		return "center/student/studentCertificate/diploma";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCertificate:studentCertificate")
	public PageUtils list(@RequestParam Map<String, Object> params,Model model){
		//如果选择了专业，就把考区条件去掉
		if (!(params.get("specialityid") == null || params.get("specialityid") == ""))
		{
			params.replace("regionid","");
			params.replace("childRegionid","");
			params.replace("collegeid","");
			params.replace("teachSiteid","");
		}
		//查询列表数据
        Query query = new Query(params);
		List<StudentCertificateDO> studentSpecialityList = studentCertificateService.list(query);
        for (StudentCertificateDO item : studentSpecialityList) {

        	item.setAuditStatus(FieldDictUtil.get(getAppName(),"stu_student_speciality","grad_audit_status",item.getAuditStatus()));
			item.setAuditOperator(UserUtil.getName(item.getAuditOperator()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCertificateService.count(query);
		PageUtils pageUtils = new PageUtils(studentSpecialityList, total);
		return pageUtils ;
	}

    @ResponseBody
    @GetMapping("/listDiploma/{studentid}")
    @RequiresPermissions("student:studentCertificate:studentCertificate")
    public PageUtils listDiploma(@RequestParam Map<String, Object> params, @PathVariable String studentid){
		params.put("studentid",studentid);
        //查询列表数据
        Query query = new Query(params);
        List<StudentCertificateDO> studentCertificateList = studentCertificateService.listDiploma(query);
        for (StudentCertificateDO item : studentCertificateList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
        int total = studentCertificateService.countDiploma(query);
        PageUtils pageUtils = new PageUtils(studentCertificateList, total);
        return pageUtils;
    }

	@GetMapping("/add/{studentid}")
	@RequiresPermissions("student:studentCertificate:add")
	String add(@PathVariable("studentid")String studentid, Model model){
		StudentCertificateDO studentCertificate = studentCertificateService.get(studentid);
		model.addAttribute("studentCertificate", studentCertificate);
	    return "center/student/studentCertificate/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCertificate:edit")
	String edit(@PathVariable("id") String id,Model model){
		StudentCertificateDO studentCertificate = studentCertificateService.get(id);
		model.addAttribute("studentCertificate", studentCertificate);
	    return "center/student/studentCertificate/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save/{isgraduateDirection}")
	@RequiresPermissions("student:studentCertificate:add")
	public R save( StudentCertificateDO studentCertificate,boolean isgraduateDirection,Long id){
        studentCertificate.setOperator(ShiroUtils.getUserId().toString());
        //判断是否不带专业方向
        if (!isgraduateDirection){
            studentCertificate.setGraduateDirection(null);
        }
        //判断是否存在毕业证书信息
        String code = studentCertificateService.isCode(id);
        if (StringUtils.isEmpty(code)){
           return R.error("毕业证"+code+"已经存在，不能重复添加");
        }
		if(studentCertificateService.save(studentCertificate)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 *功能描述 批量生成
	 * @author ypp
	 * @date 2018/12/29
	 * @param
	 * @return
	 */
	@ResponseBody
	@PostMapping("/initCode")
	public void initCode(){
		StudentidCheckCode studentidCheckCode = new StudentidCheckCode();
		List<StudentCertificateDO> studentCertificateList = studentCertificateService.initCode();
		for (StudentCertificateDO item : studentCertificateList) {
			studentidCheckCode.GetCheckCode(item.getStudentid());
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCertificate:edit")
	public R update( StudentCertificateDO studentCertificate){
	    studentCertificate.setOperator(ShiroUtils.getUserId().toString());
		studentCertificateService.update(studentCertificate);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCertificate:remove")
	public R remove( Long id){
		if(studentCertificateService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCertificate:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentCertificateService.batchRemove(ids);
		return R.ok();
	}
	
}
