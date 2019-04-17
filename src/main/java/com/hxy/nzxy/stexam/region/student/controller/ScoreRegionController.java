package com.hxy.nzxy.stexam.region.student.controller;

import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.student.dao.StudentCourseDao;
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

import com.hxy.nzxy.stexam.domain.ScoreDO;
import com.hxy.nzxy.stexam.region.student.service.ScoreRegionService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生成绩表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-22 20:06:13
 */
 
@Controller
@RequestMapping("/student/scoreRegion")
public class ScoreRegionController extends SystemBaseController{
	@Autowired
	private ScoreRegionService scoreRegionService;
	@Autowired
	private StudentCourseDao studentCourseDao;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:scoreRegion:scoreRegion")
	String ScoreRegion(){
	    return "region/student/scoreRegion/scoreRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreRegion:scoreRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreDO> scoreRegionList = scoreRegionService.list(query);
        for (ScoreDO item : scoreRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
			item.setType(FieldDictUtil.get(getAppName(), "stu_score", "type", item.getType()));

		}
		int total = scoreRegionService.count(query);
		PageUtils pageUtils = new PageUtils(scoreRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreRegion:add")
	String add(Model model){
	    return "region/student/scoreRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreDO scoreRegion = scoreRegionService.get(id);
		model.addAttribute("scoreRegion", scoreRegion);
	    return "region/student/scoreRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreRegion:add")
	public R save( ScoreDO scoreRegion){
        scoreRegion.setOperator(ShiroUtils.getUserId().toString());
		if(scoreRegionService.save(scoreRegion)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreRegion:edit")
	public R update( ScoreDO scoreRegion){
	    scoreRegion.setOperator(ShiroUtils.getUserId().toString());
		scoreRegionService.update(scoreRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreRegion:remove")
	public R remove( Long id){
		if(scoreRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreRegionService.batchRemove(ids);
		return R.ok();
	}

	@ResponseBody
	@RequestMapping("/megerZkz")//合并准考证
	@RequiresPermissions("student:scoreStudent:add")
		public R megerZkz(String oldStudentid, String newStudentid){
		if(studentCourseDao.megerZkz(oldStudentid, newStudentid)>0) {
			return R.ok();
		}
			return R.error();
		}
		@ResponseBody
		@RequestMapping("/reMegerZkz")//取消合并准考证
		@RequiresPermissions("student:scoreStudent:add")
		public R reMegerZkz(String oldStudentid, String newStudentid) {
			if (studentCourseDao.reMegerZkz(oldStudentid, newStudentid) > 0) {
				return R.ok();
			}
			return R.error();
		}
	}
