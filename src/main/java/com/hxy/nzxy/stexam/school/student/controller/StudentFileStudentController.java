package com.hxy.nzxy.stexam.school.student.controller;

import com.hxy.nzxy.stexam.center.plan.service.SpecialityService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.SchoolSpecialityRegDO;
import com.hxy.nzxy.stexam.domain.SpecialityDO;
import com.hxy.nzxy.stexam.domain.StudentDO;
import com.hxy.nzxy.stexam.domain.StudentSpecialityDO;
import com.hxy.nzxy.stexam.school.school.service.SchoolSpecialityRegSchoolService;
import com.hxy.nzxy.stexam.school.student.service.StudentFileStudentService;
import com.hxy.nzxy.stexam.school.student.service.StudentSpecialityStudentService;
import com.hxy.nzxy.stexam.school.student.service.StudentStudentService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.configuration.Configuration;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 考生基本信息表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-11 18:17:44
 */
 
@Controller
@RequestMapping("/student/studentFileStudent")
public class StudentFileStudentController extends SystemBaseController{
	@Autowired
	private StudentFileStudentService studentFileStudentService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private SpecialityService specialityService;
	@Autowired
	private StudentSpecialityStudentService studentSpecialityStudentService;
	@Autowired
	private SchoolSpecialityRegSchoolService schoolSpecialityRegSchoolService;

	@GetMapping()
	@RequiresPermissions("student:studentFileStudent:studentStudent")
	String StudentStudent(Model model){
		model.addAttribute("stu_student_status", commonService.listFieldDict(getAppName(), "stu_student", "status"));
		model.addAttribute("stu_student_audit_status", commonService.listFieldDict(getAppName(), "stu_student", "audit_status"));
		model.addAttribute("stu_student_confirm_status", commonService.listFieldDict(getAppName(), "stu_student", "confirm_status"));
		model.addAttribute("stu_student_audit_status", commonService.listFieldDict(getAppName(), "stu_student", "audit_status"));

		return "school/student/studentFileStudent/studentFileStudent";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("student:studentFileStudent:studentStudent")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudentDO> studentStudentList = studentFileStudentService.list(query);
        for (StudentDO item : studentStudentList) {

			item.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", item.getTeachSiteid()+""));
			item.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", item.getCollegeid()+""));
			item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid()+""));
			item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
			item.setStatus(FieldDictUtil.get(getAppName(), "stu_student", "status", item.getStatus()));

			item.setGroupName(FieldDictUtil.get(getAppName(), "reg_groups", "id", item.getGroupid()+""));
			item.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", item.getType()));
			item.setGender(FieldDictUtil.get(getAppName(), "stu_student", "gender", item.getGender()));
			item.setHomeType(FieldDictUtil.get(getAppName(), "stu_student", "home_type", item.getHomeType()));
			item.setPolitics(FieldDictUtil.get(getAppName(), "stu_student", "politics", item.getPolitics()));
			item.setNation(FieldDictUtil.get(getAppName(), "stu_student", "nation", item.getNation()));
			item.setProfession(FieldDictUtil.get(getAppName(), "stu_student", "profession", item.getProfession()));
			item.setCertificateType(FieldDictUtil.get(getAppName(), "stu_student", "certificate_type", item.getCertificateType()));
			item.setSpecialityName(item.getSpecialityid()+item.getSpecialityName());
			item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = studentFileStudentService.count(query);
		PageUtils pageUtils = new PageUtils(studentStudentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("student:studentFileStudent:add")
	String add(Model model){
		model.addAttribute("stu_student_gender", commonService.listFieldDict(getAppName(), "stu_student", "gender"));
		model.addAttribute("stu_student_home_type", commonService.listFieldDict(getAppName(), "stu_student", "home_type"));
		model.addAttribute("stu_student_politics", commonService.listFieldDict(getAppName(), "stu_student", "politics"));
		model.addAttribute("stu_student_nation", commonService.listFieldDict(getAppName(), "stu_student", "nation"));
		model.addAttribute("stu_student_profession", commonService.listFieldDict(getAppName(), "stu_student", "profession"));
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
		model.addAttribute("stu_student_certificate_type", commonService.listFieldDict(getAppName(), "stu_student", "certificate_type"));
		model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));



		return "school/student/studentFileStudent/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentFileStudent:edit")
	String edit(@PathVariable("id") String id,Model model){
		//报考专业
		StudentSpecialityDO studentSpecialityStudent = studentSpecialityStudentService.get(Long.valueOf(id));
	//	StudentDO studentStudent = studentStudentService.get(studentSpecialityStudent.getStudentid());
		StudentDO studentStudent = studentFileStudentService.get(studentSpecialityStudent.getStudentid());
		studentStudent.setGroupName(FieldDictUtil.get(getAppName(), "reg_groups_nzxy", "id", studentStudent.getGroupid()+""));
		model.addAttribute("studentStudent", studentStudent);
		model.addAttribute("stu_student_gender", commonService.listFieldDict(getAppName(), "stu_student", "gender"));
		model.addAttribute("stu_student_home_type", commonService.listFieldDict(getAppName(), "stu_student", "home_type"));
		model.addAttribute("stu_student_politics", commonService.listFieldDict(getAppName(), "stu_student", "politics"));
		model.addAttribute("stu_student_nation", commonService.listFieldDict(getAppName(), "stu_student", "nation"));
		model.addAttribute("stu_student_profession", commonService.listFieldDict(getAppName(), "stu_student", "profession"));
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
		model.addAttribute("stu_student_certificate_type", commonService.listFieldDict(getAppName(), "stu_student", "certificate_type"));
		model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));
		//报考专业
	//	StudentSpecialityDO studentSpecialityStudent = studentSpecialityStudentService.getByStudentId(id);
		/*SchoolSpecialityRegDO schoolSpecialityRegDO = schoolSpecialityRegSchoolService.getSelect(studentSpecialityStudent.getSchoolSpecialityRegid());
		schoolSpecialityRegDO.setStatus(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "status", schoolSpecialityRegDO.getStatus()));
		schoolSpecialityRegDO.setAuditStatus(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "audit_status", schoolSpecialityRegDO.getAuditStatus()));
		schoolSpecialityRegDO.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", schoolSpecialityRegDO.getClassify()));
		schoolSpecialityRegDO.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", schoolSpecialityRegDO.getType()));
		schoolSpecialityRegDO.setMethod(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "method", schoolSpecialityRegDO.getMethod()));
		schoolSpecialityRegDO.setEducateLength(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "educate_length", schoolSpecialityRegDO.getEducateLength()));
		schoolSpecialityRegDO.setRegSeason(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "reg_season", schoolSpecialityRegDO.getRegSeason()));
		studentSpecialityStudent.setSchoolSpecialityRegName(schoolSpecialityRegDO.getSpecialityId()+schoolSpecialityRegDO.getSpecialityName()+"["+schoolSpecialityRegDO.getRegYear()+" "+schoolSpecialityRegDO.getRegSeason()+"]"+"["+schoolSpecialityRegDO.getClassify()+" "+schoolSpecialityRegDO.getType()+" "+schoolSpecialityRegDO.getEducateLength()+"]");*/
		studentSpecialityStudent.setSchoolSpecialityRegName(FieldDictUtil.get(getAppName(), "pla_speciality_record_nzxy", "specialityid_direction", studentSpecialityStudent.getSpecialityRecordid()+""));
		SpecialityDO specialityDO = specialityService.get(studentSpecialityStudent.getGradSpecialityid());
		studentSpecialityStudent.setGradSpecialityName("("+specialityDO.getId()+")"+specialityDO.getName());
		model.addAttribute("studentSpecialityStudent", studentSpecialityStudent);

		return "school/student/studentFileStudent/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("student:studentFileStudent:add")
	public R save( StudentDO studentStudent,StudentSpecialityDO studentSpeciality){
        studentStudent.setOperator(ShiroUtils.getUserId().toString());
		if(studentFileStudentService.save(studentStudent,studentSpeciality)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentStudent:edit")
	public R update( StudentDO studentStudent,StudentSpecialityDO studentSpeciality){
	    studentStudent.setOperator(ShiroUtils.getUserId().toString());
		studentFileStudentService.update(studentStudent,studentSpeciality);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentStudent:remove")
	public R remove( String id){
		//报考专业
		if(studentSpecialityStudentService.remove(Long.valueOf(id))>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("student:studentFileStudent:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		studentFileStudentService.batchRemove(ids);
		return R.ok();
	}


	/**
	 * 显示弹出页面
	 * @param
	 * @return
	 */
	@GetMapping("/showSubject")

	String showSubject( ){

		return "school/student/studentStudent/showSubject";
	}

	@ResponseBody
	@GetMapping("/listshowSubject")
	public PageUtils listshowSubject (@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<SpecialityDO> specialityList = specialityService.list(query);
		for (SpecialityDO item : specialityList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_speciality", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "pla_speciality", "classify", item.getClassify()));
			item.setFlag(FieldDictUtil.get(getAppName(), "pla_speciality", "flag", item.getFlag()));
			item.setGrantType(FieldDictUtil.get(getAppName(), "pla_speciality", "grant_type", item.getGrantType()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}

		int total = specialityService.count(query);
		PageUtils pageUtils = new PageUtils(specialityList, total);
		return pageUtils;
	}

	/**
	 *退学
	 */
	@PostMapping( "/updateTx")
	@ResponseBody
	@RequiresPermissions("student:studentStudent:remove")
	public R tx(@RequestParam("ids[]") String[] ids){
		if(studentFileStudentService.updateTx(ids)>0){
			return R.ok();
		}
		return R.error();
	}
	@RequestMapping("/searchOutExcel")
	public String searchOutEXcel(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request,Model model){
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
		List<StudentDO> studentStudentList = studentFileStudentService.list(params);
			request.getSession().setAttribute("totalCount", studentStudentList.size());
		for (StudentDO item : studentStudentList) {
			item.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", item.getTeachSiteid()+""));
			item.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", item.getCollegeid()+""));
			item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid()+""));
			item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
			item.setStatus(FieldDictUtil.get(getAppName(), "stu_student", "status", item.getStatus()));
			item.setGroupName(FieldDictUtil.get(getAppName(), "reg_groups", "id", item.getGroupid()+""));
			item.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", item.getType()));
			item.setGender(FieldDictUtil.get(getAppName(), "stu_student", "gender", item.getGender()));
			item.setHomeType(FieldDictUtil.get(getAppName(), "stu_student", "home_type", item.getHomeType()));
			item.setPolitics(FieldDictUtil.get(getAppName(), "stu_student", "politics", item.getPolitics()));
			item.setNation(FieldDictUtil.get(getAppName(), "stu_student", "nation", item.getNation()));
			item.setProfession(FieldDictUtil.get(getAppName(), "stu_student", "profession", item.getProfession()));
			item.setCertificateType(FieldDictUtil.get(getAppName(), "stu_student", "certificate_type", item.getCertificateType()));
			item.setSpecialityName(item.getSpecialityid()+item.getSpecialityName());
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}

		if (studentStudentList != null && studentStudentList.size() > 0) {
			String[][] result = new String[studentStudentList.size() + 1][12];

			result[0] = new String[] { "序号", "准考证号", "身份证号", "姓名", "籍贯", "移动电话", "学院", "教学点","现报专业","注册时间","状态","注册次数"};
			if (studentStudentList != null && studentStudentList.size() > 0) {
				for (int i = 0; i < studentStudentList.size(); i++) {
					StudentDO student = studentStudentList.get(i);
					result[i + 1][0] = String.valueOf(i + 1);
					result[i + 1][1] = String.valueOf(student.getId());
					result[i + 1][2] = String.valueOf(student.getCertificateNo());
					result[i + 1][3] = String.valueOf(student.getName());
					result[i + 1][4] = String.valueOf(student.getNativePlace());
					result[i + 1][5] = String.valueOf(student.getMphone());
					result[i + 1][6] = String.valueOf(student.getCollegeName());
					result[i + 1][7] = String.valueOf(student.getTeachName());
					result[i + 1][8] = String.valueOf(student.getSpecialityName());
					result[i + 1][9] = String.valueOf(student.getCreateDate());
					result[i + 1][10] = String.valueOf(student.getStatus());
					result[i + 1][11] = String.valueOf(student.getRegCount());
					double dPercent=(double)(i)/studentStudentList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%
				}
			}
			String tempFileName="学生信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="学生信息" +date+".zip";
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
			model.addAttribute("stu_student_status", commonService.listFieldDict(getAppName(), "stu_student", "status"));
			model.addAttribute("stu_student_audit_status", commonService.listFieldDict(getAppName(), "stu_student", "audit_status"));
			model.addAttribute("stu_student_confirm_status", commonService.listFieldDict(getAppName(), "stu_student", "confirm_status"));
			model.addAttribute("stu_student_audit_status", commonService.listFieldDict(getAppName(), "stu_student", "audit_status"));
			return "school/student/studentFileStudent/studentFileStudent";
		}
			request.getSession().setAttribute("percent", 100);    //比如这里是50
			request.getSession().setAttribute("percentText",100+"%");//这里是50%
			request.getSession().setAttribute("curCount", studentStudentList.size());
		return null;
	}

	@ResponseBody
	@GetMapping("/listQu")
	@RequiresPermissions("student:studentFileStudent:studentStudent")
	public PageUtils listQu(@RequestParam Map<String, Object> params){
		//查询列表数据
		String schoolid = ShiroUtils.getUser().getWorkerid();
		Query query = new Query(params);
		query.put("schoolid",schoolid);
		List<StudentDO> studentStudentList = studentFileStudentService.listQu(query);

		int total = studentFileStudentService.countQu(query);
		PageUtils pageUtils = new PageUtils(studentStudentList, total);
		return pageUtils;
	}
}
