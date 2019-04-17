package com.hxy.nzxy.stexam.center.exam.controller;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.hxy.nzxy.stexam.center.school.service.SchoolService;
import com.hxy.nzxy.stexam.domain.SchoolDO;
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

import com.hxy.nzxy.stexam.domain.PaperSchoolDO;
import com.hxy.nzxy.stexam.center.exam.service.PaperSchoolService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * 阅卷点设置
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
 
@Controller
@RequestMapping("/exam/paperSchool")
public class PaperSchoolController extends SystemBaseController{
	@Autowired
	private PaperSchoolService paperSchoolService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private SchoolService schoolService;
	@GetMapping()
	@RequiresPermissions("exam:paperSchool:paperSchool")
	String PaperSchool(){
	    return "center/exam/paperSchool/paperSchool";
	}
	@GetMapping("/school")
	//@RequiresPermissions("exam:paperSchool:school")
	String school(){
		return "center/exam/paperSchool/school";
	}
	@ResponseBody
	@GetMapping("/listSchool")
	//@RequiresPermissions("exam:paperSchool:school")
	public PageUtils listSchool(@RequestParam Map<String, Object> params, HttpServletRequest request){
		//查询列表数据

		Query query = new Query(params);
		List<SchoolDO> schoolList = schoolService.list(query);
		for (SchoolDO item : schoolList) {
			item.setType(FieldDictUtil.get(getAppName(), "sch_school", "type", item.getType()));
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_school", "status", item.getStatus()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = schoolService.count(query);
		PageUtils pageUtils = new PageUtils(schoolList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exam:paperSchool:paperSchool")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PaperSchoolDO> paperSchoolList = paperSchoolService.list(query);
        for (PaperSchoolDO item : paperSchoolList) {
			item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid()+""));
			item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = paperSchoolService.count(query);
		PageUtils pageUtils = new PageUtils(paperSchoolList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exam:paperSchool:add")
	String add(Model model){
	    return "center/exam/paperSchool/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:paperSchool:edit")
	String edit(@PathVariable("id") Long id,Model model){
		PaperSchoolDO paperSchool = paperSchoolService.get(id);
		model.addAttribute("paperSchool", paperSchool);
	    return "center/exam/paperSchool/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:paperSchool:add")
	public R save( PaperSchoolDO paperSchool){
        paperSchool.setOperator(ShiroUtils.getUserId().toString());
		if(paperSchoolService.save(paperSchool)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exam:paperSchool:edit")
	public R update( PaperSchoolDO paperSchool){
	    paperSchool.setOperator(ShiroUtils.getUserId().toString());
		paperSchoolService.update(paperSchool);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exam:paperSchool:remove")
	public R remove( Long id){
		if(paperSchoolService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exam:paperSchool:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		paperSchoolService.batchRemove(ids);
		return R.ok();
	}
	@GetMapping("plan")
	@RequiresPermissions("exam:paperSchoolplan:paperSchoolplan")
	String plan(){
		return "center/exam/paperSchool/paperSchoolPlan";
	}

	@ResponseBody
	@GetMapping("/listPlan")
	@RequiresPermissions("exam:paperSchoolplan:paperSchoolplan")
	public PageUtils listPlan(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<PaperSchoolDO> paperSchoolList = paperSchoolService.listPlan(query);
		for (PaperSchoolDO item : paperSchoolList) {
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
			item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid()+""));

		}
		int total = paperSchoolService.countPlan(query);
		PageUtils pageUtils = new PageUtils(paperSchoolList, total);
		return pageUtils;
	}
	/**
	 * 设置
	 */
	@PostMapping( "/set")
	@ResponseBody
	public R set(@RequestParam String schoolid,@RequestParam String conditions){
		List<PaperSchoolDO> dataList =JSON.parseArray(conditions, PaperSchoolDO.class);
		paperSchoolService.set(schoolid,dataList);
		return R.ok();
	}
}
