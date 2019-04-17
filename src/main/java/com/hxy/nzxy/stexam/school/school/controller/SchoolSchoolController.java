package com.hxy.nzxy.stexam.school.school.controller;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.system.domain.DeptWorkerDO;
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

import com.hxy.nzxy.stexam.domain.SchoolDO;
import com.hxy.nzxy.stexam.school.school.service.SchoolSchoolService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * 助学组织
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:41
 */
 
@Controller
@RequestMapping("/school/schoolSchool")
public class SchoolSchoolController extends SystemBaseController{
	@Autowired
	private SchoolSchoolService schoolSchoolService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("school:schoolSchool:schoolSchool")
	String SchoolSchool(){
	    return "school/school/schoolSchool/schoolSchool";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:schoolSchool:schoolSchool")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据

        Query query = new Query(params);
		List<SchoolDO> schoolSchoolList = schoolSchoolService.list(query);
        for (SchoolDO item : schoolSchoolList) {
			item.setType(FieldDictUtil.get(getAppName(), "sch_school", "type", item.getType()));
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_school", "status", item.getStatus()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = schoolSchoolService.count(query);
		PageUtils pageUtils = new PageUtils(schoolSchoolList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("school:schoolSchool:add")
	String add(Model model){
		model.addAttribute("sch_school_type", commonService.listFieldDict(getAppName(), "sch_school", "type"));
		model.addAttribute("sch_school_status", commonService.listFieldDict(getAppName(), "sch_school", "status"));

		return "school/school/schoolSchool/add";
	}

	@GetMapping("/edit")
	@RequiresPermissions("school:schoolSchool:edit")
	String edit(Model model, HttpServletRequest request){
		//String workerid = ShiroUtils.getUser().getWorkerid();
	//	DeptWorkerDO dept= schoolSchoolService.getDept(workerid);
	//	DeptWorkerDO  dept= (DeptWorkerDO) request.getSession().getAttribute("school");
		SchoolDO schoolSchool = schoolSchoolService.get(Long.valueOf(ShiroUtils.getUser().getWorkerid()));
		model.addAttribute("sch_school_type", commonService.listFieldDict(getAppName(), "sch_school", "type"));
		model.addAttribute("sch_school_status", commonService.listFieldDict(getAppName(), "sch_school", "status"));
	model.addAttribute("schoolSchool", schoolSchool);
	    return "school/school/schoolSchool/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:schoolSchool:add")
	public R save( SchoolDO schoolSchool){
        schoolSchool.setOperator(ShiroUtils.getUserId().toString());
		if(schoolSchoolService.save(schoolSchool)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:schoolSchool:edit")
	public R update( SchoolDO schoolSchool){
	    schoolSchool.setOperator(ShiroUtils.getUserId().toString());
		schoolSchoolService.update(schoolSchool);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:schoolSchool:remove")
	public R remove( Long id){
		if(schoolSchoolService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:schoolSchool:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		schoolSchoolService.batchRemove(ids);
		return R.ok();
	}
	
}
