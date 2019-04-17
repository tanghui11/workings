package com.hxy.nzxy.stexam.center.school.controller;

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

import com.hxy.nzxy.stexam.domain.SchoolSpecialityRegDO;
import com.hxy.nzxy.stexam.center.school.service.SchoolSpecialityRegService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 中心端专业招生备案审核
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
 
@Controller
@RequestMapping("/school/schoolSpecialityReg")
public class SchoolSpecialityRegController extends SystemBaseController{
	@Autowired
	private SchoolSpecialityRegService schoolSpecialityRegService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("school:schoolSpecialityReg:schoolSpecialityReg")
	String SchoolSpecialityReg(Model model){
		model.addAttribute("sch_school_speciality_reg_status", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "status"));
		model.addAttribute("sch_school_speciality_reg_audit_status", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "audit_status"));
		model.addAttribute("sch_school_speciality_reg_classify", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "classify"));
		model.addAttribute("sch_school_speciality_reg_type", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "type"));
		model.addAttribute("sch_school_speciality_reg_method", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "method"));
		model.addAttribute("sch_school_speciality_reg_educate_length", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "educate_length"));
		model.addAttribute("sch_school_speciality_reg_reg_season", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "reg_season"));
		return "center/school/schoolSpecialityReg/schoolSpecialityReg";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:schoolSpecialityReg:schoolSpecialityReg")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SchoolSpecialityRegDO> schoolSpecialityRegList = schoolSpecialityRegService.list(query);
        for (SchoolSpecialityRegDO item : schoolSpecialityRegList) {
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "status", item.getStatus()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "audit_status", item.getAuditStatus()));
			item.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", item.getClassify()));
			item.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", item.getType()));
			item.setMethod(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "method", item.getMethod()));
			item.setEducateLength(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "educate_length", item.getEducateLength()));
			item.setRegSeason(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "reg_season", item.getRegSeason()));
			item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = schoolSpecialityRegService.count(query);
		PageUtils pageUtils = new PageUtils(schoolSpecialityRegList, total);
		return pageUtils;
	}

	/**
	 * 单个审核/取消审核
	 */
	@PostMapping( "/updateAudit")
	@ResponseBody
	@RequiresPermissions("school:schoolSpecialityReg:audit")
	public R updateAudit( @RequestParam Map<String, Object> params){
		String auditStatus=(String)params.get("auditStatus");
		String messages="审核成功！";
		if("a".equals(auditStatus))
		{
			messages="取消审核成功！";
		}
		if(schoolSpecialityRegService.updateAudit(params)>0){
			return R.ok(messages);
		}
		return R.error();
	}

	/**
	 * 批量审核/取消审核
	 */
	@PostMapping( "/batchUpdateAudit")
	@ResponseBody
	@RequiresPermissions("school:schoolSpecialityReg:batchAudit")
	public R batchUpdateAudit(@RequestParam("ids[]") Long[] ids,@RequestParam("auditStatus") String auditStatus){

		String messages="批量审核成功！";
		if("a".equals(auditStatus))
		{
			messages="批量取消审核成功！";
		}
		schoolSpecialityRegService.batchUpdateAudit(ids,auditStatus);
		return R.ok();
	}

	// 新生花名册需要用到的弹出层
	//学校对应年季度对应专业

	@GetMapping("/schoolSpeciality")
	String schoolSpeciality(Model model){
		model.addAttribute("sch_school_speciality_reg_status", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "status"));
		model.addAttribute("sch_school_speciality_reg_audit_status", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "audit_status"));
		model.addAttribute("sch_school_speciality_reg_classify", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "classify"));
		model.addAttribute("sch_school_speciality_reg_type", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "type"));
		model.addAttribute("sch_school_speciality_reg_method", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "method"));
		model.addAttribute("sch_school_speciality_reg_educate_length", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "educate_length"));
		model.addAttribute("sch_school_speciality_reg_reg_season", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "reg_season"));
		return "center/school/schoolSpecialityReg/schoolSpecialit";
	}
	@ResponseBody
	@GetMapping("/listSchoolSpeciality")
	@RequiresPermissions("school:schoolSpecialityReg:schoolSpecialityReg")
	public PageUtils listSchoolSpeciality(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<SchoolSpecialityRegDO> schoolSpecialityRegList = schoolSpecialityRegService.listSchoolSpeciality(query);
		for (SchoolSpecialityRegDO item : schoolSpecialityRegList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = schoolSpecialityRegService.listSchoolcount(query);
		PageUtils pageUtils = new PageUtils(schoolSpecialityRegList, total);
		return pageUtils;
	}
}
