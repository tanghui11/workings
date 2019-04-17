package com.hxy.nzxy.stexam.center.region.controller;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.exam.service.FoulPunishService;
import com.hxy.nzxy.stexam.domain.FoulPunishDO;
import com.hxy.nzxy.stexam.domain.StudentWGDO;
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

import com.hxy.nzxy.stexam.domain.StudentFoulDO;
import com.hxy.nzxy.stexam.center.region.service.StudentFoulService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考试违规
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 18:39:19
 */
 
@Controller
@RequestMapping("/region/studentFoul")
public class StudentFoulController extends SystemBaseController{
	@Autowired
	private StudentFoulService studentFoulService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private FoulPunishService foulPunishService;
	@GetMapping()
	@RequiresPermissions("region:studentFoul:studentFoul")
	String StudentFoul(){
	    return "center/region/studentFoul/studentFoul";
	}
	@GetMapping("/studentFoul")
	@RequiresPermissions("region:studentFoul:studentFoul")
	String FoulPunish(Model model){
		model.addAttribute("exa_foul_punish_type", commonService.listFieldDict(getAppName(), "exa_foul_punish", "type"));
		model.addAttribute("exa_foul_punish_flag", commonService.listFieldDict(getAppName(), "exa_foul_punish", "flag"));

		return "center/region/studentFoul/foulPunish";
	}
	@ResponseBody
	@GetMapping("/listFoul")
	@RequiresPermissions("region:studentFoul:studentFoul")
	public PageUtils listFoul(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<FoulPunishDO> foulPunishList = foulPunishService.list(query);
		for (FoulPunishDO item : foulPunishList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setType(FieldDictUtil.get(getAppName(), "exa_foul_punish", "type", item.getType()));
			item.setFlag(FieldDictUtil.get(getAppName(), "exa_foul_punish", "flag", item.getFlag()));
		}
		int total = foulPunishService.count(query);
		PageUtils pageUtils = new PageUtils(foulPunishList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("region:studentFoul:studentFoul")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentFoulDO> studentFoulList = studentFoulService.list(query);
        for (StudentFoulDO item : studentFoulList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentFoulService.count(query);
		PageUtils pageUtils = new PageUtils(studentFoulList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/listStudent")
	@RequiresPermissions("region:studentFoul:studentFoul")
	public PageUtils listStudent(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<StudentWGDO> studentFoulList = studentFoulService.listStudent(query);
		for (StudentWGDO item : studentFoulList) {
			item.setSegment(FieldDictUtil.get(getAppName(), "exa_exam_time", "segment", item.getSegment()));
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));

		}
		int total = studentFoulService.countStudent(query);
		PageUtils pageUtils = new PageUtils(studentFoulList, total);
		return pageUtils;
	}
	@GetMapping("/add")
	@RequiresPermissions("region:studentFoul:add")
	String add(Model model){
	    return "center/region/studentFoul/add";
	}

	@GetMapping("/edit/{studentCourseid}")
	@RequiresPermissions("region:studentFoul:edit")
	String edit(@PathVariable("studentCourseid") Long studentCourseid,Model model){
		StudentFoulDO studentFoul = studentFoulService.get(studentCourseid);
		model.addAttribute("studentFoul", studentFoul);
	    return "center/region/studentFoul/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("region:studentFoul:add")
	public R save( StudentFoulDO studentFoul){
        studentFoul.setOperator(ShiroUtils.getUserId().toString());
		if(studentFoulService.save(studentFoul)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("region:studentFoul:edit")
	public R update( StudentFoulDO studentFoul){
	    studentFoul.setOperator(ShiroUtils.getUserId().toString());
		studentFoulService.update(studentFoul);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("region:studentFoul:remove")
	public R remove( Long studentCourseid){
		if(studentFoulService.remove(studentCourseid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("region:studentFoul:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] studentCourseids){
		studentFoulService.batchRemove(studentCourseids);
		return R.ok();
	}

	@GetMapping("/conf")
	@RequiresPermissions("region:studentFoul:studentFoulConf")
	String StudentFoulConf(){
		return "center/region/studentFoul/studentFoulConf";
	}

	@ResponseBody
	@GetMapping("/listFoulConf")
	@RequiresPermissions("region:studentFoul:studentFoulConf")
	public PageUtils listFoulConf(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<FoulPunishDO> foulPunishList = foulPunishService.list(query);
		for (FoulPunishDO item : foulPunishList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setType(FieldDictUtil.get(getAppName(), "exa_foul_punish", "type", item.getType()));
			item.setFlag(FieldDictUtil.get(getAppName(), "exa_foul_punish", "flag", item.getFlag()));
		}
		int total = foulPunishService.count(query);
		PageUtils pageUtils = new PageUtils(foulPunishList, total);
		return pageUtils;
	}
}
