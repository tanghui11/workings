package com.hxy.nzxy.stexam.school.student.controller;

import com.hxy.nzxy.stexam.center.exam.service.ExamCourseService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.ExamCourseDO;
import com.hxy.nzxy.stexam.domain.StudentCourseDO;
import com.hxy.nzxy.stexam.domain.TaskDO;
import com.hxy.nzxy.stexam.school.student.service.StudentCourseStudentStudentService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 考生报考表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */

@Controller
@RequestMapping("/student/studentCourseStudentStudent")
public class StudentCourseStudentStudentController extends SystemBaseController{
	@Autowired
	private StudentCourseStudentStudentService studentCourseStudentStudentService;
    @Autowired
    private CommonService commonService;
    @Autowired
	private  ExamCourseService examCourseService;
/*	@GetMapping()
	@RequiresPermissions("student:studentCourseStudentStudent:studentCourseStudentStudent")
	String StudentCourseStudent(){
	    return "school/student/studentCourseStudentStudent/studentCourseStudentStudent";
	}*/
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCourseStudentStudent:studentCourseStudentStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCourseDO> studentCourseStudentList = studentCourseStudentStudentService.list(query);
        for (StudentCourseDO item : studentCourseStudentList) {

            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCourseStudentStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentCourseStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentCourseStudentStudent:add")
	String add(Model model){
	    return "school/student/studentCourseStudentStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCourseStudentStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentCourseDO studentCourseStudent = studentCourseStudentStudentService.get(id);
		model.addAttribute("studentCourseStudent", studentCourseStudent);
	    return "school/student/studentCourseStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("student:studentCourseStudentStudent:add")
	public R save(String[] examCourseid,String childRegionid,String regionid,String studentid , HttpServletRequest request){

		if(studentCourseStudentStudentService.saveCourse(examCourseid,childRegionid,regionid,studentid)>0){
		return R.ok();
		}else{
			return R.error();
		}

	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCourseStudentStudent:edit")
	public R update( StudentCourseDO studentCourseStudent){
	    studentCourseStudent.setOperator(ShiroUtils.getUserId().toString());
		studentCourseStudentStudentService.update(studentCourseStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseStudentStudent:remove")
	public R remove( Long id){
		if(studentCourseStudentStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseStudentStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentCourseStudentStudentService.batchRemove(ids);
		return R.ok();
	}
    /**
     * 考试任务
     */
	@ResponseBody
	@RequestMapping("/listTask")
	public List listTask(@RequestParam Map<String, Object> params){
		//查询列表数据
		//Query query = new Query(params);
		List<TaskDO> taskList = studentCourseStudentStudentService.listTask(params);
		for (TaskDO item : taskList) {
			item.setExamMonth(FieldDictUtil.get(getAppName(), "exam_task", "exam_month", item.getExamMonth()));
			item.setExamName(item.getExamYear()+"  "+item.getExamMonth());

		}
		return taskList;
	}
    /**
     * 考生页面 +课程页面
     */
    @GetMapping("")
    String studentCourse()
    {
        return "school/student/studentCourseStudentStudent/studentRegStudent";
    }

    @ResponseBody
    @GetMapping("/listCourse")
    public PageUtils listCourse(@RequestParam Map<String, Object> params){
        //查询列表数据

            Query query = new Query(params);
            List<ExamCourseDO> examCourseList = studentCourseStudentStudentService.listCourse(query);
            for (ExamCourseDO item : examCourseList) {
				item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
                item.setType(FieldDictUtil.get(getAppName(), "exa_exam_course", "type", item.getType()));
                item.setClassify(FieldDictUtil.get(getAppName(), "exa_exam_course", "classify", item.getClassify()));
                item.setCardType(FieldDictUtil.get(getAppName(), "exa_exam_course", "card_type", item.getCardType()));
                item.setSegment(FieldDictUtil.get(getAppName(), "exa_exam_time", "segment", item.getSegment()));
                item.setOperator(UserUtil.getName(item.getOperator()));
                item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
            }
            int total = studentCourseStudentStudentService.listCourseCount(query);
            PageUtils pageUtils = new PageUtils(examCourseList, total);
            return pageUtils;
    }
	/**
	 * 未报考人员
	 *
	 * */
	@GetMapping("studentNoExam")
	String studentNoExam(){

    	return "school/student/studentCourseStudentStudent/studentNoExam";
	}
	/**
	 * 未报考人员
	 *
	 * */
	@ResponseBody
	@GetMapping("/listStudentNoExam")
	@RequiresPermissions("student:studentCourseStudentStudent:studentCourseStudent")
	public List<StudentCourseDO> listStudentNoExam(@RequestParam Map<String, Object> params,HttpServletRequest request){
		List<StudentCourseDO> ListNoExam=(List<StudentCourseDO>)request.getSession().getAttribute("studentNoExamList");
		for (StudentCourseDO item : ListNoExam) {
			item.setCourseName("("+item.getCourseid()+")"+FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
		}
		return ListNoExam;
	}
}
