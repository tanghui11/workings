package com.hxy.nzxy.stexam.school.student.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.hxy.nzxy.stexam.center.exam.service.ExamCourseService;
import com.hxy.nzxy.stexam.center.student.service.StudentCourseRepaireService;
import com.hxy.nzxy.stexam.domain.*;
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

import com.hxy.nzxy.stexam.school.student.service.StudentCourseStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 考生报考表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */

@Controller
@RequestMapping("/student/studentCourseStudent")
public class StudentCourseStudentController extends SystemBaseController{
	@Autowired
	private StudentCourseStudentService studentCourseStudentService;
	@Autowired
	private StudentCourseRepaireService studentCourseService;
	@Autowired
    private CommonService commonService;
    @Autowired
	private  ExamCourseService examCourseService;
	@GetMapping()
	@RequiresPermissions("student:studentCourseStudent:studentCourseStudent")
	String StudentCourseStudent(){
	    return "school/student/studentCourseStudent/studentCourseStudent";
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCourseStudent:studentCourseStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCourseDO> studentCourseStudentList = studentCourseStudentService.list(query);
        for (StudentCourseDO item : studentCourseStudentList) {

            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCourseStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentCourseStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentCourseStudent:add")
	String add(Model model){
	    return "school/student/studentCourseStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCourseStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentCourseDO studentCourseStudent = studentCourseStudentService.get(id);
		model.addAttribute("studentCourseStudent", studentCourseStudent);
	    return "school/student/studentCourseStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("student:studentCourseStudent:add")
	public R save(String stuList,String courseList,String teachid , HttpServletRequest request){
	request.getSession().setAttribute("studentNoExamList",studentCourseStudentService.saveCourse(stuList,courseList,teachid,request));
		//return	studentCourseStudentService.saveCourse(stuList,courseList,teachid);
		String studentBk = request.getAttribute("StudentBk").toString();
		if(studentBk.equals("success")){
			return R.ok();
		}else{
			return R.ok("已报考");
		}
		//if(studentCourseStudentService.save(stuList)>0){

	//	}

	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCourseStudent:edit")
	public R update( StudentCourseDO studentCourseStudent){
	    studentCourseStudent.setOperator(ShiroUtils.getUserId().toString());
		studentCourseStudentService.update(studentCourseStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseStudent:remove")
	public R remove( Long id){
		if(studentCourseStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentCourseStudentService.batchRemove(ids);
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
		List<TaskDO> taskList = studentCourseStudentService.listTask(params);
		for (TaskDO item : taskList) {
			item.setExamMonth(FieldDictUtil.get(getAppName(), "exam_task", "exam_month", item.getExamMonth()));
			item.setExamName(item.getExamYear()+"  "+item.getExamMonth());

		}
		return taskList;
	}
    /**
     * 考生页面 +开考课程页面
     */
    @GetMapping("/studentCourse")
    String studentCourse(Model model,@RequestParam String specialityRecordid)
    {
        model.addAttribute("specialityRecordid",specialityRecordid);
        return "school/student/studentCourseStudent/studentRegStudent";
    }
    //已报考课程接口
	@ResponseBody
	@GetMapping("/ybkList")
	public PageUtils ybkList(@RequestParam Map<String, Object> params){
		//查询列表数据


		Query query = new Query(params);
		List<StudentCourseDO> studentCourseList = studentCourseService.list(query);
		for (StudentCourseDO item : studentCourseList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setType(FieldDictUtil.get(getAppName(), "stu_student_course", "type", item.getType()));
			item.setStatus(FieldDictUtil.get(getAppName(), "stu_student_course", "status", item.getStatus()));
			item.setArrangeStatus(FieldDictUtil.get(getAppName(), "stu_student_course", "arrange_status", item.getArrangeStatus()));
			item.setRegionName(FieldDictUtil.get(getAppName(), "reg_region_nzxy_a", "id", String.valueOf(item.getRegionid())));
			item.setChildRegionName(FieldDictUtil.get(getAppName(), "reg_region_nzxy_b", "id", String.valueOf(item.getChildRegionid())));
			item.setSegment(FieldDictUtil.get(getAppName(), "exa_exam_time", "segment", item.getSegment()));
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));

		}
		int total = studentCourseService.count(query);
		PageUtils pageUtils = new PageUtils(studentCourseList, total);
		return pageUtils;
	}


	//开考课程
    @ResponseBody
    @GetMapping("/listCourse")
    public PageUtils listCourse(@RequestParam Map<String, Object> params){
        //查询列表数据
        String examTaskid= (String)params.get("examTaskid");
        if(examTaskid!=null&&!"".equals(examTaskid)) {
            Query query = new Query(params);
            List<ExamCourseDO> examCourseList = studentCourseStudentService.listCourse(query);
            for (ExamCourseDO item : examCourseList) {
				item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
				item.setType(FieldDictUtil.get(getAppName(), "exa_exam_course", "type", item.getType()));
                item.setClassify(FieldDictUtil.get(getAppName(), "exa_exam_course", "classify", item.getClassify()));
                item.setCardType(FieldDictUtil.get(getAppName(), "exa_exam_course", "card_type", item.getCardType()));
                item.setSegment(FieldDictUtil.get(getAppName(), "exa_exam_time", "segment", item.getSegment()));
                item.setOperator(UserUtil.getName(item.getOperator()));
                item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
            }
            int total = studentCourseStudentService.listCourseCount(query);
            PageUtils pageUtils = new PageUtils(examCourseList, total);
            return pageUtils;
        }else
        {
            PageUtils pageUtils = new PageUtils(new ArrayList(), 0);
            return pageUtils;
        }
    }
	/**
	 * 未报考人员
	 *
	 * */
	@GetMapping("studentNoExam")
	String studentNoExam(){

    	return "school/student/studentCourseStudent/studentNoExam";
	}
	/**
	 * 未报考人员
	 *
	 * */
	@ResponseBody
	@GetMapping("/listStudentNoExam")
	@RequiresPermissions("student:studentCourseStudent:studentCourseStudent")
	public List<StudentCourseDO> listStudentNoExam(@RequestParam Map<String, Object> params,HttpServletRequest request){
		List<StudentCourseDO> ListNoExam=(List<StudentCourseDO>)request.getSession().getAttribute("studentNoExamList");
		for (StudentCourseDO item : ListNoExam) {
			item.setCourseName("("+item.getCourseid()+")"+FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
		}
		return ListNoExam;
	}

	//学生信息导入页面
	@GetMapping("/importData")

	String importData( ){
		return "school/student/studentCourseStudent/importData";
	}
	/**
	 * 批量导入
	 */
	@PostMapping("/savBathData")
	public String savBathData(@RequestParam(value="filename") MultipartFile file,
							  HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "school/student/studentCourseStudent/importData";
		}

		//获取文件名
		String fileName=file.getOriginalFilename();

		//验证文件名是否合格
	/*	if(!ExcelImportUtils.validateExcel(fileName)){
			session.setAttribute("msg","文件必须是excel格式！");
			return "redirect:toUserKnowledgeImport";
		}*/

		//进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
		long size=file.getSize();
		if(StringUtils.isEmpty(fileName) || size==0){
			request.setAttribute("msg","文件不能为空！");
			return "school/student/studentCourseStudent/importData";
		}

		//批量导入
		String message = studentCourseStudentService.batchImport(fileName,file,request);
		request.setAttribute("msg",message);
		return "school/student/studentCourseStudent/importData";
	}
}
