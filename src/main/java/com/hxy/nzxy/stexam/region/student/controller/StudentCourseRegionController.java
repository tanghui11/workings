package com.hxy.nzxy.stexam.region.student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.hxy.nzxy.stexam.center.plan.service.SpecialityRecordService;
import com.hxy.nzxy.stexam.center.plan.service.SpecialityService;
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

import com.hxy.nzxy.stexam.region.student.service.StudentCourseRegionService;
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
 * @date 2018-08-22 20:06:14
 */
 
@Controller
@RequestMapping("/student/studentCourseRegion")
public class StudentCourseRegionController extends SystemBaseController{
	@Autowired
	private StudentCourseRegionService studentCourseRegionService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private SpecialityRecordService specialityRecordService;
	@GetMapping()
	@RequiresPermissions("student:studentCourseRegion:studentCourseRegion")
	String StudentCourseRegion(){
	    return "region/student/studentCourseRegion/studentCourseRegion";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCourseRegion:studentCourseRegion")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentCourseDO> studentCourseRegionList = studentCourseRegionService.list(query);
        for (StudentCourseDO item : studentCourseRegionList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentCourseRegionService.count(query);
		PageUtils pageUtils = new PageUtils(studentCourseRegionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentCourseRegion:add")
	String add(Model model){
	    return "region/student/studentCourseRegion/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCourseRegion:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentCourseDO studentCourseRegion = studentCourseRegionService.get(id);
		model.addAttribute("studentCourseRegion", studentCourseRegion);
	    return "region/student/studentCourseRegion/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentCourseRegion:add")
	public R save(String[] examCourseid,String childRegionid,String regionid,String studentid , HttpServletRequest request){
		//验证是否存在 同一时间已报考的课程

			int count=studentCourseRegionService.selectSameExamTime(examCourseid,studentid);
			if(count>0){
				return R.ok("有相同考试时间课程存在，不能报考！");
			}

		if(studentCourseRegionService.saveCourse(examCourseid,childRegionid,regionid,studentid)>0){
			return R.ok("报考成功！");
		}else{
			return R.error();
		}

	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCourseRegion:edit")
	public R update( StudentCourseDO studentCourseRegion){
	    studentCourseRegion.setOperator(ShiroUtils.getUserId().toString());
		studentCourseRegionService.update(studentCourseRegion);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseRegion:remove")
	public R remove( Long id){
		if(studentCourseRegionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseRegion:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentCourseRegionService.batchRemove(ids);
		return R.ok();
	}
	/**
	 * 学生信息
	 */

	@ResponseBody
	@GetMapping("/listStudent")
	public PageUtils listStudentStudent(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<StudentSpecialityDO> studentStudentList = studentCourseRegionService.listStudent(query);
		for (StudentSpecialityDO item : studentStudentList) {
			item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = studentCourseRegionService.countStudent(query);
		PageUtils pageUtils = new PageUtils(studentStudentList, total);
		return pageUtils;
	}

	/**
	 * 开考课程
	 */

	@ResponseBody
	@GetMapping("/listCourse")
	public PageUtils listCourse(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ExamCourseDO> examCourseList = studentCourseRegionService.listCourse(query);
		for (ExamCourseDO item : examCourseList) {
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
			item.setType(FieldDictUtil.get(getAppName(), "exa_exam_course", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "exa_exam_course", "classify", item.getClassify()));
			item.setCardType(FieldDictUtil.get(getAppName(), "exa_exam_course", "card_type", item.getCardType()));
			item.setSegment(FieldDictUtil.get(getAppName(), "exa_exam_time", "segment", item.getSegment()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = studentCourseRegionService.listCourseCount(query);
		PageUtils pageUtils = new PageUtils(examCourseList, total);
		return pageUtils;
	}
	/**
	 * 开考课程
	 */

	@ResponseBody
	@GetMapping("/listCourseNew")
	public PageUtils listCourseNew(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<ExamCourseDO> examCourseList = studentCourseRegionService.listCourseNew(query);
		for (ExamCourseDO item : examCourseList) {
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
			item.setType(FieldDictUtil.get(getAppName(), "exa_exam_course", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "exa_exam_course", "classify", item.getClassify()));
			item.setCardType(FieldDictUtil.get(getAppName(), "exa_exam_course", "card_type", item.getCardType()));
			item.setSegment(FieldDictUtil.get(getAppName(), "exa_exam_time", "segment", item.getSegment()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = studentCourseRegionService.listCourseCountNew(query);
		PageUtils pageUtils = new PageUtils(examCourseList, total);
		return pageUtils;
	}

	/**
	 * 考试任务
	 */
	@ResponseBody
	@RequestMapping("/listTask")
	public List listTask(@RequestParam Map<String, Object> params){
		//查询列表数据
		//Query query = new Query(params);
		List<TaskDO> taskList = studentCourseRegionService.listTask(params);
		for (TaskDO item : taskList) {
			item.setExamMonth(FieldDictUtil.get(getAppName(), "exam_task", "exam_month", item.getExamMonth()));
			item.setExamName(item.getExamYear()+"  "+item.getExamMonth());

		}
		return taskList;
	}

	/**
	 * 未报考人员
	 *
	 * */
	@GetMapping("studentNoExam")
	String studentNoExam(){

		return "region/student/studentCourseRegion/studentNoExam";
	}
	/**
	 * 未报考人员
	 *
	 * */
	@ResponseBody
	@GetMapping("/listStudentNoExam")
	@RequiresPermissions("student:studentCourseRegion:studentCourseRegion")
	public List<StudentCourseDO> listStudentNoExam(@RequestParam Map<String, Object> params,HttpServletRequest request){
		List<StudentCourseDO> ListNoExam=(List<StudentCourseDO>)request.getSession().getAttribute("studentNoExamList");
		for (StudentCourseDO item : ListNoExam) {
			item.setCourseName("("+item.getCourseid()+")"+FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
		}
		return ListNoExam;
	}

	//学生信息导入页面
	@GetMapping("/importData")
	@RequiresPermissions("student:studentCourseRegion:savBathData")
	String importData( ){
		return "region/student/studentCourseRegion/importData";
	}
	/**
	 * 批量导入
	 */
	@PostMapping("/savBathData")
	@RequiresPermissions("student:studentCourseRegion:savBathData")
	public String savBathData(@RequestParam(value="filename") MultipartFile file,
							  HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "region/student/studentCourseRegion/importData";
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
			return "region/student/studentCourseRegion/importData";
		}

		//批量导入
		String message = studentCourseRegionService.batchImport(fileName,file,request);
		request.setAttribute("msg",message);
		return "region/student/studentCourseRegion/importData";
	}

	//获取专业备案信息根据id
	@ResponseBody
	@GetMapping("/getSpecialityRecord")
	@RequiresPermissions("student:studentCourseRegion:studentCourseRegion")
	public SpecialityRecordDO getSpecialityRecord(@RequestParam Map<String, Object> params,HttpServletRequest request){
		SpecialityRecordDO specialityRecord = specialityRecordService.get(Long.valueOf(params.get("id").toString()));
		specialityRecord.setNewSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", specialityRecord.getNewSpecialityid())+specialityRecord.getNewDirection());
		return specialityRecord;
	}
}
