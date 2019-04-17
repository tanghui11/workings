package com.hxy.nzxy.stexam.school.student.controller;

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
import com.hxy.nzxy.stexam.school.student.service.ScoreStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
/**
 * 考生成绩表_历史表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:43
 */
 
@Controller
@RequestMapping("/student/scoreStudent")
public class ScoreStudentController extends SystemBaseController{
	@Autowired
	private ScoreStudentService scoreStudentService;
    @Autowired
    private CommonService commonService;
    @Autowired
	private StudentCourseDao studentCourseDao;
	@GetMapping()
	@RequiresPermissions("student:scoreStudent:scoreStudent")
	String ScoreStudent(){
	    return "school/student/scoreStudent/scoreStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:scoreStudent:scoreStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ScoreDO> scoreStudentList = scoreStudentService.list(query);
        for (ScoreDO item : scoreStudentList) {
			item.setType(FieldDictUtil.get(getAppName(), "stu_score", "type", item.getType()));
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
			item.setUseStatus(FieldDictUtil.get(getAppName(), "stu_score", "use_status", item.getUseStatus()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = scoreStudentService.count(query);
		PageUtils pageUtils = new PageUtils(scoreStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:scoreStudent:add")
	String add(Model model){
	    return "school/student/scoreStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:scoreStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ScoreDO scoreStudent = scoreStudentService.get(id);
		model.addAttribute("scoreStudent", scoreStudent);
	    return "school/student/scoreStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:scoreStudent:add")
	public R save( ScoreDO scoreStudent){
        scoreStudent.setOperator(ShiroUtils.getUserId().toString());
		if(scoreStudentService.save(scoreStudent)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:scoreStudent:edit")
	public R update( ScoreDO scoreStudent){
	    scoreStudent.setOperator(ShiroUtils.getUserId().toString());
		scoreStudentService.update(scoreStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:scoreStudent:remove")
	public R remove( Long id){
		if(scoreStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:scoreStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		scoreStudentService.batchRemove(ids);
		return R.ok();
	}

	@RequestMapping("/megerZkz")//合并准考证
	@ResponseBody
	@RequiresPermissions("student:scoreStudent:add")
	public R megerZkz(String oldStudentid, String newStudentid){
		if(studentCourseDao.megerZkz(oldStudentid, newStudentid)>0) {
			return R.ok();
		}
		return R.error();
	}


	@RequestMapping("/reMegerZkz")//取消合并准考证
	@ResponseBody
	@RequiresPermissions("student:scoreStudent:add")
	public R reMegerZkz(String oldStudentid, String newStudentid){
		if (studentCourseDao.reMegerZkz(oldStudentid, newStudentid) > 0) {
			return R.ok();
		}
		return R.error();
	}
}

