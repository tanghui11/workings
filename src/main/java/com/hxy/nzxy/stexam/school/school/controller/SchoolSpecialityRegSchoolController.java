package com.hxy.nzxy.stexam.school.school.controller;

import com.hxy.nzxy.stexam.center.exam.service.EducateLengthService;
import com.hxy.nzxy.stexam.center.plan.service.SpecialityRecordService;
import com.hxy.nzxy.stexam.center.plan.service.SpecialityService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.EducateLengthDO;
import com.hxy.nzxy.stexam.domain.SchoolSpecialityRegDO;
import com.hxy.nzxy.stexam.domain.SpecialityDO;
import com.hxy.nzxy.stexam.domain.SpecialityRecordDO;
import com.hxy.nzxy.stexam.school.school.service.SchoolSpecialityRegSchoolService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 专业招生备案
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:41
 */
 
@Controller
@RequestMapping("/school/schoolSpecialityRegSchool")
public class SchoolSpecialityRegSchoolController extends SystemBaseController{
	@Autowired
	private SchoolSpecialityRegSchoolService schoolSpecialityRegSchoolService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private EducateLengthService educateLengthService;
	@Autowired
	private SpecialityRecordService specialityRecordService;
	@Autowired
	private SpecialityService specialityService;
	@GetMapping()
	@RequiresPermissions("school:schoolSpecialityRegSchool:schoolSpecialityRegSchool")
	String SchoolSpecialityRegSchool(){
		return "school/school/schoolSpecialityRegSchool/schoolSpecialityRegSchool";
	}
	/**
	 * 申请来源
	 */
	@GetMapping("/SchoolSpecialityRegSchoolForm")
	String SchoolSpecialityRegSchoolForm(){
		return "school/school/schoolSpecialityRegSchool/schoolSpecialityRegSchoolForm";
	}
	/**
	 * 学生报名选择
	 */
	@GetMapping("/SchoolSpecialityRegSchoolStudent")
	String SchoolSpecialityRegSchoolStudent(Model model){

		Map map= new HashMap();
		map.put("status","a");
		map.put("type","b");
		String type = ShiroUtils.getUser().getType();
		if(type.equals("3")){
			map.put("schoolid",ShiroUtils.getUser().getWorkerid());
		}else if(type.equals("4")){
			map.put("collegeid",ShiroUtils.getUser().getWorkerid());
		}
		//当前用户的开设专业
		List<SpecialityRecordDO> specialityRecordList = specialityRecordService.list(map);
		//查询对应的专业
		List<String> list=specialityRecordList.stream().map(SpecialityRecordDO::getSpecialityid).collect(Collectors.toList());
		List<SpecialityDO> specialityList=specialityService.listSpeciality(list);
		model.addAttribute("specialityList", specialityList);
		return "school/school/schoolSpecialityRegSchool/schoolSpecialityRegSchoolStudent";
	}
	@ResponseBody
	@GetMapping("/list")
/*
	@RequiresPermissions("school:schoolSpecialityRegSchool:schoolSpecialityRegSchool")
*/
	public PageUtils list(@RequestParam Map<String, Object> params){

		//查询列表数据
        Query query = new Query(params);
		List<SchoolSpecialityRegDO> schoolSpecialityRegSchoolList = schoolSpecialityRegSchoolService.list(query);
        for (SchoolSpecialityRegDO item : schoolSpecialityRegSchoolList) {
			item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityId())+item.getDirection());
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
		int total = schoolSpecialityRegSchoolService.count(query);
		PageUtils pageUtils = new PageUtils(schoolSpecialityRegSchoolList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("school:schoolSpecialityRegSchool:add")
	String add(Model model , HttpServletRequest request){
		model.addAttribute("sch_school_speciality_reg_status", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "status"));
		model.addAttribute("sch_school_speciality_reg_audit_status", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "audit_status"));
		model.addAttribute("sch_school_speciality_reg_classify", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "classify"));
		model.addAttribute("sch_school_speciality_reg_type", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "type"));
		model.addAttribute("sch_school_speciality_reg_method", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "method"));
		model.addAttribute("sch_school_speciality_reg_educate_length", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "educate_length"));
		model.addAttribute("sch_school_speciality_reg_reg_season", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "reg_season"));
	    return "school/school/schoolSpecialityRegSchool/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:schoolSpecialityRegSchool:edit")
	String edit(@PathVariable("id") Long id,Model model){

		SchoolSpecialityRegDO schoolSpecialityRegSchool = schoolSpecialityRegSchoolService.get(id);
		Long sourceRegid = schoolSpecialityRegSchool.getSourceRegid();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id",sourceRegid);
		List<SchoolSpecialityRegDO> list = schoolSpecialityRegSchoolService.list(map);
		SchoolSpecialityRegDO schoolSpecialityRegSchoolSourceReg=new SchoolSpecialityRegDO();
		if(list.size()>0){
		schoolSpecialityRegSchoolSourceReg=list.get(0);
		schoolSpecialityRegSchoolSourceReg.setStatus(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "status", schoolSpecialityRegSchoolSourceReg.getStatus()));
		schoolSpecialityRegSchoolSourceReg.setAuditStatus(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "audit_status", schoolSpecialityRegSchoolSourceReg.getAuditStatus()));
		schoolSpecialityRegSchoolSourceReg.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", schoolSpecialityRegSchoolSourceReg.getClassify()));
		schoolSpecialityRegSchoolSourceReg.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", schoolSpecialityRegSchoolSourceReg.getType()));
		schoolSpecialityRegSchoolSourceReg.setMethod(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "method", schoolSpecialityRegSchoolSourceReg.getMethod()));
		schoolSpecialityRegSchoolSourceReg.setEducateLength(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "educate_length", schoolSpecialityRegSchoolSourceReg.getEducateLength()));
		schoolSpecialityRegSchoolSourceReg.setRegSeason(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "reg_season", schoolSpecialityRegSchoolSourceReg.getRegSeason()));
			;
		schoolSpecialityRegSchoolSourceReg.setOperator(schoolSpecialityRegSchoolSourceReg.getSpecialityId()+schoolSpecialityRegSchoolSourceReg.getSpecialityName()+"["+schoolSpecialityRegSchoolSourceReg.getRegYear()+schoolSpecialityRegSchoolSourceReg.getRegSeason()+"]");
		}
		model.addAttribute("schoolSpecialityRegSchoolSourceReg", schoolSpecialityRegSchoolSourceReg);

		model.addAttribute("schoolSpecialityRegSchool", schoolSpecialityRegSchool);
		model.addAttribute("sch_school_speciality_reg_status", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "status"));
		model.addAttribute("sch_school_speciality_reg_audit_status", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "audit_status"));
		model.addAttribute("sch_school_speciality_reg_classify", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "classify"));
		model.addAttribute("sch_school_speciality_reg_type", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "type"));
		model.addAttribute("sch_school_speciality_reg_method", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "method"));
		model.addAttribute("sch_school_speciality_reg_educate_length", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "educate_length"));
		model.addAttribute("sch_school_speciality_reg_reg_season", commonService.listFieldDict(getAppName(), "sch_school_speciality_reg", "reg_season"));
	    return "school/school/schoolSpecialityRegSchool/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:schoolSpecialityRegSchool:add")
	public R save( SchoolSpecialityRegDO schoolSpecialityRegSchool){
		schoolSpecialityRegSchool.setSchoolid(Long.valueOf(ShiroUtils.getUser().getWorkerid()));
		schoolSpecialityRegSchool.setAuditStatus("a");
		schoolSpecialityRegSchool.setStatus("a");
        schoolSpecialityRegSchool.setOperator(ShiroUtils.getUserId().toString());
		if(schoolSpecialityRegSchoolService.save(schoolSpecialityRegSchool)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:schoolSpecialityRegSchool:edit")
	public R update( SchoolSpecialityRegDO schoolSpecialityRegSchool){
	    schoolSpecialityRegSchool.setOperator(ShiroUtils.getUserId().toString());
		schoolSpecialityRegSchoolService.update(schoolSpecialityRegSchool);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:schoolSpecialityRegSchool:remove")
	public R remove( Long id){
		if(schoolSpecialityRegSchoolService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:schoolSpecialityRegSchool:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		schoolSpecialityRegSchoolService.batchRemove(ids);
		return R.ok();
	}
	/**
	 * 学制定义接口
	 */

	@ResponseBody
	@GetMapping("/educate")
	public List<EducateLengthDO>  educate(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<EducateLengthDO> educateLengthList = educateLengthService.list(params);
		for (EducateLengthDO item : educateLengthList) {
			item.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", item.getClassify()));
			item.setLength(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "educate_length", item.getLength()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		return educateLengthList;
	}
	@ResponseBody
	@GetMapping("/listBk")
/*
	@RequiresPermissions("school:schoolSpecialityRegSchool:schoolSpecialityRegSchool")
*/
	public PageUtils listBk(@RequestParam Map<String, Object> params){

		//查询列表数据
		Query query = new Query(params);
		List<SchoolSpecialityRegDO> schoolSpecialityRegSchoolList = schoolSpecialityRegSchoolService.listBk(query);
		for (SchoolSpecialityRegDO item : schoolSpecialityRegSchoolList) {
			item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityId()));
			item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid()+""));

			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = schoolSpecialityRegSchoolService.countBk(query);
		PageUtils pageUtils = new PageUtils(schoolSpecialityRegSchoolList, total);
		return pageUtils;
	}
//获取专业
	@GetMapping("/intoPage")
	String intoPage(){
		return "school/student/schoolScoreStudent/schoolSpeciality";
	}
}
