package com.hxy.nzxy.stexam.school.student.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import com.hxy.nzxy.stexam.domain.CourseFreeDO;
import com.hxy.nzxy.stexam.domain.StudentDO;
import com.hxy.nzxy.stexam.domain.StudentSpecialityDO;
import org.apache.commons.configuration.Configuration;
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

import com.hxy.nzxy.stexam.domain.StudentRegDO;
import com.hxy.nzxy.stexam.school.student.service.StudentRegStudentService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;

import javax.servlet.http.HttpServletResponse;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 考生注册
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:45
 */
 
@Controller
@RequestMapping("/student/studentRegStudent")
public class StudentRegStudentController extends SystemBaseController{
	@Autowired
	private StudentRegStudentService studentRegStudentService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	String StudentRegStudent(Model model,String id){
		model.addAttribute("studentSpecialityid",id);
	    return "school/student/studentRegStudent/studentRegStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentRegDO> studentRegStudentList = studentRegStudentService.list(query);
        for (StudentRegDO item : studentRegStudentList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setStatus(FieldDictUtil.get(getAppName(), "stu_student_reg", "status", item.getStatus()));

		}
		int total = studentRegStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentRegStudentList, total);
		return pageUtils;
	}
	/**
	 * 学生信息批量注册
	 *
	 * */
	@ResponseBody
	@GetMapping("/listStudent")
	@RequiresPermissions("student:studentRegStudent:studentRegStudent")
	public PageUtils listStudent(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<StudentSpecialityDO> studentStudentList = studentRegStudentService.listStudent(query);
		for (StudentSpecialityDO item : studentStudentList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = studentRegStudentService.countStudent(query);
		PageUtils pageUtils = new PageUtils(studentStudentList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("student:studentRegStudent:add")
	String add(Model model){
	    return "school/student/studentRegStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentRegStudent:edit")
	String edit(@PathVariable("id") Long id,Model model){
		StudentRegDO studentRegStudent = studentRegStudentService.get(id);
		model.addAttribute("studentRegStudent", studentRegStudent);
	    return "school/student/studentRegStudent/edit";
	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( StudentRegDO studentRegStudent){
		String currentYear = DateUtil.getCurrentYear();
		studentRegStudent.setRegBeginDate(DateUtil.getCurrYearFirst(Integer.valueOf(currentYear)+1));
		studentRegStudent.setRegEndDate(DateUtil.getCurrYearLast(Integer.valueOf(currentYear)+1));
		if(studentRegStudent.getCreateDate()!=null){
			currentYear=studentRegStudent.getCreateDate();
			studentRegStudent.setRegBeginDate(DateUtil.getCurrYearFirst(Integer.valueOf(currentYear)));
			studentRegStudent.setRegEndDate(DateUtil.getCurrYearLast(Integer.valueOf(currentYear)));
		}
		//考生注册信息是否需要审核
		Configuration config = getConfig("config.properties");
		String educate_length =config.getString("educate_length");
		if(educate_length.equals("true")){
			studentRegStudent.setStatus("a");
		}else{
			studentRegStudent.setStatus("b");
		}



        studentRegStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentRegStudentService.save(studentRegStudent)>0){

			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentRegStudent:edit")
	public R update( StudentRegDO studentRegStudent){
	    studentRegStudent.setOperator(ShiroUtils.getUserId().toString());
		studentRegStudentService.update(studentRegStudent);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		if(studentRegStudentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentRegStudent:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		studentRegStudentService.batchRemove(ids);
		return R.ok();
	}
	/**
	 * 审核
	 */
	@PostMapping( "/batchAudit")
	@ResponseBody
	public R batchAudit(@RequestParam("ids[]") Long[] ids,String flag){
		if(flag.equals("true")){
			studentRegStudentService.batchAuditPass(ids);
		}else{
			studentRegStudentService.batchAuditNoPass(ids);
		}

		return R.ok();
	}

	/**
	 * 同一年不能注册两次接口
	 * 1为已存在 0为为存在
	 */
	@RequestMapping("/selectYear")
	@ResponseBody
	public int selectYear(StudentRegDO studentReg){
		String currentYear = DateUtil.getCurrentYear();
		studentReg.setRegBeginDate(DateUtil.getCurrYearFirst(Integer.valueOf(currentYear)+1));
		studentReg.setRegEndDate(DateUtil.getCurrYearLast(Integer.valueOf(currentYear)+1));
		if(studentReg.getCreateDate()!=null){
			currentYear=studentReg.getCreateDate();
			studentReg.setRegBeginDate(DateUtil.getCurrYearFirst(Integer.valueOf(currentYear)));
			studentReg.setRegEndDate(DateUtil.getCurrYearLast(Integer.valueOf(currentYear)));
		}
		int total =studentRegStudentService.selectYear(studentReg);
		return total;
	}
/**
 *
 * 学生信息单个注册
 * */

	@ResponseBody
	@GetMapping("/listStudentStudent")
	public PageUtils listStudentStudent(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<StudentSpecialityDO> studentStudentList = studentRegStudentService.listStudentStudent(query);
		for (StudentSpecialityDO item : studentStudentList) {
			item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = studentRegStudentService.countStudentStudent(query);
		PageUtils pageUtils = new PageUtils(studentStudentList, total);
		return pageUtils;
	}


	public static Date parse(String str, String pattern, Locale locale) {

		if (str == null || pattern == null) {

			return null;

		}

		try {

			return (Date) new SimpleDateFormat(pattern, locale).parse(str);

		} catch (ParseException e) {

			e.printStackTrace();

		}

		return null;

	}


	public static String format(Date date, String pattern, Locale locale) {

		if (date == null || pattern == null) {

			return null;

		}

		return new SimpleDateFormat(pattern, locale).format(date);

	}
	/**
	 * 导出
	 */
	@RequestMapping(value= "searchOutSFExcel" )
	public String searchOutEXcel(@RequestParam Map<String, Object> params, HttpServletResponse response) throws ParseException {
		//查询列表数据
		Configuration config = getConfig("config.properties");
		String configsrc =config.getString("url");
		String Filepath = configsrc+ "studengReg/";
		String strZipPath=configsrc+"studengRegZip/";
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
//		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
		Date date1 = null;
		Date date2 = null;
		List<StudentRegDO> studentRegStudentList = studentRegStudentService.list(params);
		for (StudentRegDO item : studentRegStudentList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setStatus(FieldDictUtil.get(getAppName(), "stu_student_reg", "status", item.getStatus()));
		}

		if (studentRegStudentList != null && studentRegStudentList.size() > 0) {
			String[][] result = new String[studentRegStudentList.size() + 1][10];

			result[0] = new String[] { "编号","学生报考专业编号","注册开始日期","注册结束日期","状态","操作员","操作日期","注册日期","数据标记","识别码"};
			if (studentRegStudentList != null && studentRegStudentList.size() > 0) {
				for (int i = 0; i < studentRegStudentList.size(); i++) {
					StudentRegDO courseFree = studentRegStudentList.get(i);
					result[i + 1][0] = String.valueOf(courseFree.getId());
					result[i + 1][1] = String.valueOf(courseFree.getStudentSpecialityid());
					result[i + 1][2] = String.valueOf(DateUtils.format(courseFree.getRegBeginDate(), DateUtils.DATE_PATTERN));
					result[i + 1][3] = String.valueOf(DateUtils.format(courseFree.getRegEndDate(), DateUtils.DATE_PATTERN));
					result[i + 1][4] = String.valueOf(courseFree.getStatus());
					result[i + 1][5] = String.valueOf(courseFree.getOperator());
					date1 = new SimpleDateFormat("yyyyMMdd").parse(courseFree.getUpdateDate());
					date2 = new SimpleDateFormat("yyyyMMdd").parse(courseFree.getCreateDate());
					result[i + 1][6] =  DateFormat.getDateInstance().format(date1);
					result[i + 1][7] =  DateFormat.getDateInstance().format(date2);
					result[i + 1][8] = String.valueOf(courseFree.getDbFlag());
					result[i + 1][9] = String.valueOf(courseFree.getKeyValue());
				}
			}
			String tempFileName="考生注册导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="考生注册导出信息" +date+".zip";
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
			return "center/student/studentFileStudent/studentRegStudent";
		}
		return null;
	}
}
