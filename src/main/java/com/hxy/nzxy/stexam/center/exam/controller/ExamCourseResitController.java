package com.hxy.nzxy.stexam.center.exam.controller;

import com.hxy.nzxy.stexam.center.exam.service.ExamCourseResitService;
import com.hxy.nzxy.stexam.center.exam.service.ExamCourseService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.ExamCourseDO;
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
 * 课程补考
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */
 
@Controller
@RequestMapping("/exam/examCourseResit")
public class ExamCourseResitController extends SystemBaseController{
	@Autowired
	private ExamCourseResitService examCourseService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("exam:examCourseResit:examCourseResit")
	String ExamCourse(Model model){
		model.addAttribute("exa_exam_course_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "type"));
		model.addAttribute("exa_exam_course_classify", commonService.listFieldDict(getAppName(), "exa_exam_course", "classify"));
		model.addAttribute("exa_exam_course_card_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "card_type"));
	    return "center/exam/examCourseResit/examCourseResit";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exam:examCourseResit:examCourse")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		String examTaskid= (String)params.get("examTaskid");
		if(examTaskid!=null&&!"".equals(examTaskid)) {
			Query query = new Query(params);
			List<ExamCourseDO> examCourseList = examCourseService.list(query);
			for (ExamCourseDO item : examCourseList) {
				item.setType(FieldDictUtil.get(getAppName(), "exa_exam_course", "type", item.getType()));
				item.setClassify(FieldDictUtil.get(getAppName(), "exa_exam_course", "classify", item.getClassify()));
				item.setCardType(FieldDictUtil.get(getAppName(), "exa_exam_course", "card_type", item.getCardType()));
				item.setOperator(UserUtil.getName(item.getOperator()));
				item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			}
			int total = examCourseService.count(query);
			PageUtils pageUtils = new PageUtils(examCourseList, total);
			return pageUtils;
		}else
		{
			PageUtils pageUtils = new PageUtils(new ArrayList(), 0);
			return pageUtils;
		}
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exam:examCourseResit:add")
	String add(Model model){
		model.addAttribute("exa_exam_course_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "type"));
		model.addAttribute("exa_exam_course_classify", commonService.listFieldDict(getAppName(), "exa_exam_course", "classify"));
		model.addAttribute("exa_exam_course_card_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "card_type"));

		return "center/exam/examCourseResit/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:examCourseResit:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExamCourseDO examCourse = examCourseService.get(id);
		model.addAttribute("examCourse", examCourse);
		model.addAttribute("exa_exam_course_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "type"));
		model.addAttribute("exa_exam_course_classify", commonService.listFieldDict(getAppName(), "exa_exam_course", "classify"));
		model.addAttribute("exa_exam_course_card_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "card_type"));

		return "center/exam/examCourseResit/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:examCourseResit:add")
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
	@RequiresPermissions("exam:examCourseResit:edit")
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
	@RequiresPermissions("exam:examCourseResit:remove")
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
	@RequiresPermissions("exam:examCourseResit:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		examCourseService.batchRemove(ids);
		return R.ok();
	}
	
}
