package com.hxy.nzxy.stexam.center.exam.controller;

import com.hxy.nzxy.stexam.center.exam.dao.TaskExamDao;
import com.hxy.nzxy.stexam.center.exam.service.ExamCourseService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.ExamCourseDO;
import com.hxy.nzxy.stexam.domain.TaskDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 命题计划
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
 
@Controller
@RequestMapping("/exam/examProposition")
public class ExamPropositionController extends SystemBaseController{
	@Autowired
	private ExamCourseService examCourseService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("exam:examProposition:examProposition")
	String ExamCourse(Model model){
		model.addAttribute("exa_exam_course_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "type"));
		model.addAttribute("exa_exam_course_classify", commonService.listFieldDict(getAppName(), "exa_exam_course", "classify"));
		model.addAttribute("exa_exam_course_card_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "card_type"));
	    return "center/exam/examProposition/examProposition";
	}
	@GetMapping("/book")
	@RequiresPermissions("exam:examProposition:book")
	String book(Model model,String courseid){
		model.addAttribute("courseid", courseid);
		return "center/exam/examProposition/book";
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exam:examProposition:examProposition")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		String examTaskid= (String)params.get("examTaskid");
		if(examTaskid!=null&&!"".equals(examTaskid)) {
			Query query = new Query(params);
			List<ExamCourseDO> examCourseList = examCourseService.listProposition(query);
			for (ExamCourseDO item : examCourseList) {
				item.setClassify(FieldDictUtil.get(getAppName(), "exa_exam_course", "classify", item.getClassify()));
				item.setCardType(FieldDictUtil.get(getAppName(), "exa_exam_course", "card_type", item.getCardType()));
				item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
				item.setSegment(FieldDictUtil.get(getAppName(), "exa_exam_time", "segment", item.getSegment()));
				item.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", item.getType()));
				item.setCourseType(FieldDictUtil.get(getAppName(), "exa_exam_course", "type", item.getCourseType()));
				item.setOperator(UserUtil.getName(item.getOperator()));
				item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			}
			int total = examCourseService.countProposition(query);
			PageUtils pageUtils = new PageUtils(examCourseList, total);
			return pageUtils;
		}else
		{
			PageUtils pageUtils = new PageUtils(new ArrayList(), 0);
			return pageUtils;
		}
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exam:examProposition:add")
	String add(Model model){
		model.addAttribute("exa_exam_course_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "type"));
		model.addAttribute("exa_exam_course_classify", commonService.listFieldDict(getAppName(), "exa_exam_course", "classify"));
		model.addAttribute("exa_exam_course_card_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "card_type"));

		return "center/exam/examProposition/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:examProposition:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExamCourseDO examCourse = examCourseService.get(id);
		model.addAttribute("examCourse", examCourse);
		model.addAttribute("exa_exam_course_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "type"));
		model.addAttribute("exa_exam_course_classify", commonService.listFieldDict(getAppName(), "exa_exam_course", "classify"));
		model.addAttribute("exa_exam_course_card_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "card_type"));

		return "center/exam/examProposition/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:examProposition:add")
	public R save( ExamCourseDO examCourse){
        examCourse.setOperator(ShiroUtils.getUserId().toString());
		if(examCourseService.save(examCourse)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exam:examProposition:edit")
	public R update( ExamCourseDO examCourse){
	    examCourse.setOperator(ShiroUtils.getUserId().toString());
		examCourseService.update(examCourse);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exam:examProposition:remove")
	public R remove( Long id){
		if(examCourseService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exam:examProposition:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		examCourseService.batchRemove(ids);
		return R.ok();
	}
	/**
	 * 全部默认
	 */
	@PostMapping("/allDefault")
	@ResponseBody
	@RequiresPermissions("exam:examProposition:allDefault")
	public R allDefault( ExamCourseDO examCourse){
		examCourse.setOperator(ShiroUtils.getUserId().toString());
		examCourseService.updateAllDefault(examCourse);
		return R.ok();
	}
	/**
	 * 生成序号
	 */
	@PostMapping("/sequence")
	@ResponseBody
	@RequiresPermissions("exam:examProposition:sequence")
	public R sequence( ExamCourseDO examCourse){
		examCourse.setOperator(ShiroUtils.getUserId().toString());
		examCourseService.updateSequence(examCourse);
		return R.ok();
	}
	/**
	 * 考试任务确认
	 */
	@PostMapping("/confirm")
	@ResponseBody
	@RequiresPermissions("exam:examProposition:confirm")
	public R 	confirm(TaskDO task){
		task.setAuditStatus("b");
		examCourseService.updateTask(task);
		return R.ok();
	}
	/**
	 * 取消考试任务确认
	 */
	@PostMapping("/cancel")
	@ResponseBody
	@RequiresPermissions("exam:examProposition:cancel")
	public R 	cancel(TaskDO task){
		task.setAuditStatus("a");
		examCourseService.updateTask(task);
		return R.ok();
	}
}
