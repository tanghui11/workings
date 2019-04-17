package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.student.service.StudentCourseRepaireService;
import com.hxy.nzxy.stexam.center.student.service.StudentCourseService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.StudentCourseDO;
import com.hxy.nzxy.stexam.domain.StudentDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.commons.configuration.Configuration;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 考生课程补报(修改)
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentCourseRepaire")
public class StudentCourseRepaireController extends SystemBaseController{
	@Autowired
	private StudentCourseRepaireService studentCourseService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("student:studentCourseRepaire:studentCourseRepaire")
	String StudentCourse(){
	    return "center/student/studentCourseRepaire/studentCourseRepaire";
	}
	@GetMapping("/studentCourseUpdate")
	@RequiresPermissions("student:studentCourseRepaire:studentCourse")
	String studentCourseUpdate(){
		return "center/student/studentCourseRepaire/studentCourseUpdate";
	}
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentCourseRepaire:studentCourseRepaire")
	public PageUtils list(@RequestParam Map<String, Object> params){
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
			item.setChildRegionName(FieldDictUtil.get(getAppName(), "reg_region_nzxy_a", "id", String.valueOf(item.getChildRegionid())));
            item.setSegment(FieldDictUtil.get(getAppName(), "exa_exam_time", "segment", item.getSegment()));
            item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));

		}
		int total = studentCourseService.count(query);
		PageUtils pageUtils = new PageUtils(studentCourseList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentCourseRepaire:add")
	String add(Model model){
		model.addAttribute("reg_student_out_cause", commonService.listFieldDict(getAppName(), "reg_student_out", "cause"));
	    return "center/student/studentCourseRepaire/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentCourseRepaire:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentCourseDO studentCourse = studentCourseService.get(id);
		model.addAttribute("studentCourse", studentCourse);
	    return "center/student/studentCourseRepaire/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentCourseRepaire:add")
	public R save( StudentCourseDO studentCourse){
	    //中心端
        studentCourse.setType("d");
        //待确认
        studentCourse.setStatus("a");
        //待编排
        studentCourse.setArrangeStatus("a");
        studentCourse.setOperator(ShiroUtils.getUserId().toString());
		if(studentCourseService.save(studentCourse)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentCourseRepaire:edit")
	public R update( StudentCourseDO studentCourse){
//中心端
		studentCourse.setType("d");
		//待确认
		studentCourse.setStatus("a");

	    studentCourse.setOperator(ShiroUtils.getUserId().toString());
		studentCourseService.update(studentCourse);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseRepaire:remove")
	public R remove( Long id){
		if(studentCourseService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentCourseRepaire:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentCourseService.batchRemove(ids);
		return R.ok();
	}
	@GetMapping("/bkCourse")
	String bkCourse(){
		return "center/student/studentCourseRepaire/bkCourse";
	}
	@ResponseBody
	@GetMapping("/listBkCourse")
	public PageUtils listBkCourse(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<StudentCourseDO> studentCourseList = studentCourseService.listBkCourse(query);
		for (StudentCourseDO item : studentCourseList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setType(FieldDictUtil.get(getAppName(), "stu_student_course", "type", item.getType()));
			item.setStatus(FieldDictUtil.get(getAppName(), "stu_student_course", "status", item.getStatus()));
			item.setArrangeStatus(FieldDictUtil.get(getAppName(), "stu_student_course", "arrange_status", item.getArrangeStatus()));
			item.setSegment(FieldDictUtil.get(getAppName(), "exa_exam_time", "segment", item.getSegment()));
            item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));

		}
		int total = studentCourseService.countBkCourse(query);
		PageUtils pageUtils = new PageUtils(studentCourseList, total);
		return pageUtils;
	}
	@GetMapping("/bkStudent")
	String bkStudent(){
		return "center/student/studentCourseRepaire/bkStudent";
	}
	//传出学生
	@GetMapping("/zcStudent")
	String zcStudent(){
		return "center/student/studentCourseRepaire/zcStudent";
	}
	@GetMapping("/sjbkStudent")
	String sjbkStudent(){
		return "center/student/studentCourseRepaire/sjbkStudent";
	}
	//考区
	@GetMapping("/hbStudent")
	String hbStudent(){
		return "center/student/studentCourseRepaire/hbStudent";
	}
	//院校
	@GetMapping("/hbStudent1")
	String hbStudent2(){
		return "center/student/studentCourseRepaire/hbStudent1";
	}
	@ResponseBody
	@GetMapping("/listBkStudent")
	public PageUtils listBkStudent(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("status","a");
		params.put("auditStatus","b");
		if (params.get("regionid")!=null){
			params.put("regionid",ShiroUtils.getUser().getWorkerid());
		}	if (params.get("schoolid")!=null){
			params.put("schoolid",ShiroUtils.getUser().getWorkerid());
		}
		Query query = new Query(params);
		List<StudentCourseDO> studentCourseList = studentCourseService.listBkStudent(query);
		for (StudentCourseDO item : studentCourseList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setType(FieldDictUtil.get(getAppName(), "stu_student_course", "type", item.getType()));
			item.setStatus(FieldDictUtil.get(getAppName(), "stu_student_course", "status", item.getStatus()));
			item.setArrangeStatus(FieldDictUtil.get(getAppName(), "stu_student_course", "arrange_status", item.getArrangeStatus()));
			item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
            item.setGender(FieldDictUtil.get(getAppName(), "stu_student", "gender", item.getGender()));


        }
		int total = studentCourseService.countBkStudent(query);
		PageUtils pageUtils = new PageUtils(studentCourseList, total);
		return pageUtils;
	}





	//学生信息导入页面
	@GetMapping("/importData")

	String importData( ){
		return "center/student/studentCourseRepaire/importData";
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
			return "center/student/studentCourseRepaire/importData";
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
			return "center/student/studentCourseRepaire/importData";
		}

		//批量导入
		String message = studentCourseService.batchImport(fileName,file);
		request.setAttribute("msg",message);
		return "center/student/studentCourseRepaire/importData";
	}
	@RequestMapping("/searchOutExcel")
	public String searchOutEXcel(@RequestParam Map<String, Object> params, HttpServletResponse response,HttpServletRequest request){
		request.getSession().removeAttribute("totalCount");
		request.getSession().removeAttribute("curCount");
		request.getSession().removeAttribute("percent");
		request.getSession().removeAttribute("percentText");
		//查询列表数据
		Configuration config = getConfig("config.properties");
		String configsrc =config.getString("url");
		String Filepath = configsrc+ "Student/";
		String strZipPath=configsrc+"StudentZip/";
		File file = new File(Filepath);
		if (!file.exists()) {
			file.mkdirs();
		}else {
			String[] tempList = file.list();
			File tempFile = null;
			for (int i = 0; i < tempList.length; i++) {
				if (Filepath.endsWith("/")) {
					tempFile = new File(Filepath+tempList[i]);
				}else {
					tempFile=new File(Filepath+"/"+tempList[i]);
				}
				if (tempFile.isFile()) {
					tempFile.delete();
				}
			}
		}
		File fileZip = new File(strZipPath);
		if (!fileZip.exists()) {
			fileZip.mkdirs();
		}else {
			String[] tempList = fileZip.list();
			File tempFile = null;
			for (int i = 0; i < tempList.length; i++) {
				if (Filepath.endsWith("/")) {
					tempFile = new File(Filepath+tempList[i]);
				}else {
					tempFile=new File(Filepath+"/"+tempList[i]);
				}
				if (tempFile.isFile()) {
					tempFile.delete();
				}
			}
		}
		ZipOutputStream out=null;
		List<StudentCourseDO> studentCourseList = studentCourseService.list(params);
		request.getSession().setAttribute("totalCount", studentCourseList.size());
		for (StudentCourseDO item : studentCourseList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setType(FieldDictUtil.get(getAppName(), "stu_student_course", "type", item.getType()));
			item.setStatus(FieldDictUtil.get(getAppName(), "stu_student_course", "status", item.getStatus()));
			item.setArrangeStatus(FieldDictUtil.get(getAppName(), "stu_student_course", "arrange_status", item.getArrangeStatus()));
			item.setRegionName(FieldDictUtil.get(getAppName(), "reg_region_nzxy_a", "id", String.valueOf(item.getRegionid())));
			item.setChildRegionName(FieldDictUtil.get(getAppName(), "reg_region_nzxy_a", "id", String.valueOf(item.getChildRegionid())));
			item.setSegment(FieldDictUtil.get(getAppName(), "exa_exam_time", "segment", item.getSegment()));
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));

		}
		if (studentCourseList != null && studentCourseList.size() > 0) {
			String[][] result = new String[studentCourseList.size() + 1][14];

			result[0] = new String[] { "序号", "准考证号", "考生姓名", "课程代码", "课程名称", "考试时间", "类别", "考试地州","考试县区","时段","状态","编排状态","操作员","操作日期"};
			if (studentCourseList != null && studentCourseList.size() > 0) {
				for (int i = 0; i < studentCourseList.size(); i++) {
					StudentCourseDO studentDO = studentCourseList.get(i);
					result[i + 1][0] = String.valueOf(i + 1);
					result[i + 1][1] = String.valueOf(studentDO.getStudentid());
					result[i + 1][2] = String.valueOf(studentDO.getName());
					result[i + 1][3] = String.valueOf(studentDO.getCourseid());
					result[i + 1][4] = String.valueOf(studentDO.getCourseName());
					result[i + 1][5] = String.valueOf(studentDO.getExamDate());
					result[i + 1][6] = String.valueOf(studentDO.getType());
					result[i + 1][7] = String.valueOf(studentDO.getRegionName());
					result[i + 1][8] = String.valueOf(studentDO.getChildRegionName());
					result[i + 1][9] = String.valueOf(studentDO.getSegment());
					result[i + 1][10] = String.valueOf(studentDO.getStatus());
					result[i + 1][11] = String.valueOf(studentDO.getArrangeStatus());
					result[i + 1][12] = String.valueOf(studentDO.getOperator());
					result[i + 1][13] = String.valueOf(studentDO.getUpdateDate());
					double dPercent=(double)(i)/studentCourseList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%
				}
			}
			String tempFileName="课程报考信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="课程报考信息" +date+".zip";
			ZipUtils.createZip(Filepath,strZipPath+zipName);
			try {
				File fs = new File(strZipPath+zipName);
				//检查该文件是否存在
				if(!fs.exists()){
					return null;
				}
				buffer1 = new BufferedInputStream(new FileInputStream(fs));
				byte[] buf = new byte[1024];
				int len = 0;
				response.reset();
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-disposition", "attachment;filename="+new String(fs.getName().getBytes("gbk"), "iso8859-1"));
				out1 = response.getOutputStream();
				while((len = buffer1.read(buf)) >0){
					out1.write(buf,0,len);
				}
			}catch(Throwable e)
			{
			}finally
			{
				try
				{
					buffer1.close();
					out1.close();
				}catch(Throwable e)
				{

					e.printStackTrace();
				}
			}
		}
		else
		{
			return "center/student/studentCourseRepaire/student";
		}
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", studentCourseList.size());

		return null;
	}



}
