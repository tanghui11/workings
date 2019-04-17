package com.hxy.nzxy.stexam.center.student.controller;

import com.hxy.nzxy.stexam.center.student.service.StudentService;
import com.hxy.nzxy.stexam.center.student.service.StudentSpecialityService;
import com.hxy.nzxy.stexam.common.config.FtpConfig;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.StudentDO;
import com.hxy.nzxy.stexam.domain.StudentSpecialityDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.commons.configuration.Configuration;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 考生基本信息表审核
 *
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:07:25
 */
 
@Controller
@RequestMapping("/student/studentBLXX")
public class StudentBLController extends SystemBaseController{
	@Autowired
	private StudentService studentService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private StudentSpecialityService studentSpecialityService;
	@Autowired
	FtpConfig ftpConfig;

	@GetMapping()
	@RequiresPermissions("student:studentBLXX:student")
	String Student(Model model){
		model.addAttribute("stu_student_status", commonService.listFieldDict(getAppName(), "stu_student", "status"));
		model.addAttribute("stu_student_classify" ,commonService.listFieldDict(getAppName(), "stu_student", "classify"));
		model.addAttribute("stu_student_audit_status" ,commonService.listFieldDict(getAppName(), "stu_student", "audit_status"));
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
		return "center/student/studentBL/studentTemp";
		}

		@GetMapping("/studentBL")
		@RequiresPermissions("student:studentBLXX:student")
		String StudentBL(Model model){
			model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
			return "center/student/studentBL/studentTemp";
		}
		@ResponseBody
		@RequestMapping("/list")
		@RequiresPermissions("student:studentBLXX:student")
		public List list(@RequestParam(value="filename") MultipartFile file, @RequestParam(value = "type") String type , HttpServletRequest request, HttpServletResponse response,
							  HttpSession session, Model model, MultipartFile multipartFiles, boolean havePhoto) throws IOException, ParseException {
		//@RequestParam(value = "photoName")
			//获取文件名
			String fileName=file.getOriginalFilename();
			request.setCharacterEncoding("UTF-8");
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			List list = studentService.readExcelValueBLList(fileName, file, type, multipartRequest, multipartFiles, havePhoto);
			//PageUtils pageUtils = new PageUtils(studentService.readExcelValueBLList(fileName,file, type, multipartRequest, multipartFiles, havePhoto), list.size());
		return list;
	}

	/**
	 *审核页面
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/showAudit/{id}")
	@RequiresPermissions("student:studentBLXX:add")
	String showAudit(@PathVariable("id") String id,Model model){
		StudentDO student = studentService.get(id);
		model.addAttribute("student", student);
		model.addAttribute("stu_student_gender", commonService.listFieldDict(getAppName(), "stu_student", "gender"));
		model.addAttribute("stu_student_home_type", commonService.listFieldDict(getAppName(), "stu_student", "home_type"));
		model.addAttribute("stu_student_politics", commonService.listFieldDict(getAppName(), "stu_student", "politics"));
		model.addAttribute("stu_student_nation", commonService.listFieldDict(getAppName(), "stu_student", "nation"));
		model.addAttribute("stu_student_profession", commonService.listFieldDict(getAppName(), "stu_student", "profession"));
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
		model.addAttribute("stu_student_certificate_type", commonService.listFieldDict(getAppName(), "stu_student", "certificate_type"));
		model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));
		return "center/student/student/add";
	}

	/**
	 * 查询学生相应的专业信息
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/seachStuSubjectlist")

	public List seachStuSubjectlist(@RequestParam Map<String, Object> params){

		List<StudentSpecialityDO> studentList = studentSpecialityService.seachStuSubjectlist(params);
		for (StudentSpecialityDO item : studentList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", item.getTeachSiteid()+""));
			item.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", item.getCollegeid()+""));
			item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid()+""));
            item.setClassify(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "classify", item.getClassify()));
            item.setType(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "type", item.getType()));
            item.setEducateLength(FieldDictUtil.get(getAppName(), "sch_school_speciality_reg", "educate_length", item.getEducateLength()));
			item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
			item.setStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "status", item.getStatus()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "stu_student_speciality", "audit_status", item.getAuditStatus()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		return studentList;
	}
	/**
	 * 修改学生信息基本信息
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/edit/{id}")
	@RequiresPermissions("student:studentBLXX:edit")
	String edit(@PathVariable("id") String id,Model model){
		StudentDO student = studentService.get(id);
		student.setGroupName(FieldDictUtil.get(getAppName(), "reg_groups_nzxy", "id", student.getGroupid()+""));
		model.addAttribute("student", student);
		model.addAttribute("stu_student_gender", commonService.listFieldDict(getAppName(), "stu_student", "gender"));
		model.addAttribute("stu_student_home_type", commonService.listFieldDict(getAppName(), "stu_student", "home_type"));
		model.addAttribute("stu_student_politics", commonService.listFieldDict(getAppName(), "stu_student", "politics"));
		model.addAttribute("stu_student_nation", commonService.listFieldDict(getAppName(), "stu_student", "nation"));
		model.addAttribute("stu_student_profession", commonService.listFieldDict(getAppName(), "stu_student", "profession"));
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
		model.addAttribute("stu_student_certificate_type", commonService.listFieldDict(getAppName(), "stu_student", "certificate_type"));
		model.addAttribute("stu_student_speciality_education", commonService.listFieldDict(getAppName(), "stu_student_speciality", "education"));
	    return "center/student/studentBL/edit";
	}

	/**
	 * 修改学生信息
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("student:studentBLXX:edit")
	public R update( StudentDO studentStudent){
		studentStudent.setOperator(ShiroUtils.getUserId().toString());
		studentService.update(studentStudent);
		return R.ok();
	}

	/**
	 * 删除学生档案
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("student:studentBLXX:remove")
	public R remove( String id){
		//删除学生档案
		Map regMap= new HashMap();
		regMap.put("tableName","stu_student_speciality");
		regMap.put("filedName1","studentid");
		regMap.put("filedValue1",id);
		Map bkMap= new HashMap();
		bkMap.put("tableName","stu_student_course");
		bkMap.put("filedName1","studentid");
		bkMap.put("filedValue1",id);
		if(commonService.searchIfExist(regMap)>0)
		{
			return R.ok("对不起！您已经报考专业了，不能删除学生档案");
		}else if(commonService.searchIfExist(regMap)>0)
		{
			return R.ok("对不起！您已经报考课程了，不能删除学生档案");
		}else if(studentService.remove(id)>0){
			return R.ok("删除学生档案成功！");
		}

		return R.error();
	}
	/**
	 * 单个审核/取消审核
	 */
	@PostMapping( "/updateAudit")
	@ResponseBody
	@RequiresPermissions("student:studentBLXX:audit")
	public R updateAudit( @RequestParam Map<String, Object> params ){
		String auditStatus=(String)params.get("auditStatus");
		String messages="审核成功！";
		if("a".equals(auditStatus))
		{
			messages="取消审核成功！";
		}
		params.put("operator",ShiroUtils.getUserId().toString());
		if(studentService.updateAudit(params)>0){
			return R.ok(messages);
		}
		return R.error();
	}

	/**
	 * 批量审核/取消审核
	 */
	@PostMapping( "/batchUpdateAudit")
	@ResponseBody
	@RequiresPermissions("student:studentBLXX:batchAudit")
	public R batchUpdateAudit(@RequestParam("ids[]") Long[] ids,@RequestParam("auditStatus") String auditStatus){

		String messages="批量审核成功！";
		if("a".equals(auditStatus))
		{
			messages="批量取消审核成功！";
		}

		if(studentService.batchUpdateAudit(ids,auditStatus)>0) {
			return R.ok(messages);
		}else
		{
			return R.error();
		}
	}
	/**
	 * 批量退学/恢复退学
	 */
	@PostMapping( "/batchUpdateTx")
	@ResponseBody
	@RequiresPermissions("student:studentBLXX:batchTx")
	public R batchUpdateTx(@RequestParam("ids[]") Long[] ids,@RequestParam("status") String status){

		String messages="退学成功！";
		if("a".equals(status))
		{
			messages="恢复学籍成功！";
		}

		if(studentService.batchUpdateTx(ids,status)>0) {
			return R.ok(messages);
		}else
		{
			return R.error();
		}
	}

	/**
	 * 批量导入
	 */
	@PostMapping("/savBathData")
	@RequiresPermissions("student:studentBLXX:savBathData")
	public String savBathData(@RequestParam(value="filename") MultipartFile file,
							  HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/student/studentBL/studentTemp";
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
			return "center/student/studentBL/studentTemp";
		}

		//批量导入
		String message = studentService.batchImport(fileName,file,"zhuxue");
		request.setAttribute("msg",message);
		return "center/student/studentBL/studentTemp";
	}
	@RequestMapping("/searchOutExcel")
	@RequiresPermissions("student:studentBLXX:searchOutExcel")
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
		List<StudentDO> studentList = studentService.list(params);
		request.getSession().setAttribute("totalCount", studentList.size());
		for (StudentDO item : studentList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setTeachName(FieldDictUtil.get(getAppName(), "sch_teach_site_nzxy", "id", item.getTeachSiteid()+""));
			item.setCollegeName(FieldDictUtil.get(getAppName(), "sch_college_nzxy", "id", item.getCollegeid()+""));
			item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid()+""));
			item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
			item.setGroupName(FieldDictUtil.get(getAppName(), "reg_groups", "id", item.getGroupid()+""));
			item.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", item.getType()));
			item.setGender(FieldDictUtil.get(getAppName(), "stu_student", "gender", item.getGender()));
			item.setHomeType(FieldDictUtil.get(getAppName(), "stu_student", "home_type", item.getHomeType()));
			item.setPolitics(FieldDictUtil.get(getAppName(), "stu_student", "politics", item.getPolitics()));
			item.setNation(FieldDictUtil.get(getAppName(), "stu_student", "nation", item.getNation()));
			item.setProfession(FieldDictUtil.get(getAppName(), "stu_student", "profession", item.getProfession()));
			item.setCertificateType(FieldDictUtil.get(getAppName(), "stu_student", "certificate_type", item.getCertificateType()));
			item.setStatus(FieldDictUtil.get(getAppName(), "stu_student", "status", item.getStatus()));
			item.setClassify(FieldDictUtil.get(getAppName(), "stu_student", "classify", item.getClassify()));
			item.setAuditStatus(FieldDictUtil.get(getAppName(), "stu_student", "audit_status", item.getAuditStatus()));
			item.setConfirmStatus(FieldDictUtil.get(getAppName(), "stu_student", "confirm_status", item.getConfirmStatus()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		if (studentList != null && studentList.size() > 0) {
			String[][] result = new String[studentList.size() + 1][14];

			result[0] = new String[] { "序号", "准考证号", "身份证号", "姓名", "性别", "民族", "移动电话", "现报专业","学院","教学点","组织名称","状态","审核状态","确认状态"};
			if (studentList != null && studentList.size() > 0) {
				for (int i = 0; i < studentList.size(); i++) {
					StudentDO studentDO = studentList.get(i);
					result[i + 1][0] = String.valueOf(i + 1);
					result[i + 1][1] = String.valueOf(studentDO.getId());
					result[i + 1][2] = String.valueOf(studentDO.getCertificateNo());
					result[i + 1][3] = String.valueOf(studentDO.getName());
					result[i + 1][4] = String.valueOf(studentDO.getGender());
					result[i + 1][5] = String.valueOf(studentDO.getNation());
					result[i + 1][6] = String.valueOf(studentDO.getMphone());
					result[i + 1][7] = String.valueOf(studentDO.getSpecialityName());
					result[i + 1][8] = String.valueOf(studentDO.getCollegeName());
					result[i + 1][9] = String.valueOf(studentDO.getTeachName());
					result[i + 1][10] = String.valueOf(studentDO.getSchoolName());
					result[i + 1][11] = String.valueOf(studentDO.getStatus());
					result[i + 1][12] = String.valueOf(studentDO.getAuditStatus());
					result[i + 1][13] = String.valueOf(studentDO.getConfirmStatus());
					double dPercent=(double)(i)/studentList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%
				}
			}
			String tempFileName="学生档案信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="学生档案信息" +date+".zip";
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
			return "center/student/studentBL/studentTemp";
		}

		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", studentList.size());
		return null;
	}
	@RequestMapping("/studentPhoto")
	@RequiresPermissions("student:studentBLXX:studentPhoto")
	public  String uploadPhoto(String yearNumber,String regionCode,@RequestParam(value="filename") MultipartFile file,Model model)throws IOException{
		Integer size=0;
		try {
		ZipInputStream Zin=new ZipInputStream(file.getInputStream());//输入源zip路径
		BufferedInputStream Bin=new BufferedInputStream(Zin);
		String Parent="F:\\ziptest\\"; //输出路径（文件夹目录）

		File Fout=null;
		ZipEntry entry;

		InputStream inputStream;
		try {
			while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){
				Fout=new File(Parent,entry.getName());
				if(!Fout.exists()){
					(new File(Fout.getParent())).mkdirs();
				}
				FileOutputStream out=new FileOutputStream(Fout);
				BufferedOutputStream Bout=new BufferedOutputStream(out);
				int b;
				while((b=Bin.read())!=-1){
					Bout.write(b);
				}
				Bout.close();
				out.close();

				inputStream  = new FileInputStream(Fout);
				String[] strings = FtpUtil.pictureUploadByConfig(ftpConfig, entry.getName() , yearNumber + "/" + regionCode, inputStream);
				if(strings!=null){
					size++;
				}
			}
			Bin.close();
			Zin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	if(size>0){
		model.addAttribute("msg","导入成功,共"+size+"条数据");
	}else{
		model.addAttribute("msg","导入数据失败");
	}

	return"center/student/studentBL/studentTemp";
}
	//学生信息导入页面
	@GetMapping("/photoImport")
	String photoImport( ){
		return "center/student/studentBL/studentTemp";
	}



	//考生信息补录页面
	@GetMapping("/leadingPage")

	String leadingInPage(Model model ){
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
		return "center/student/studentBL/studentTemp";
	}

	/**
	 * 考生信息补录
	 * @param file 导入的dbf文件
	 * @param type 考生类型
	 * @param request
	 * @param response
	 * @param session
	 * @param model
	 * @param phonoFile 存放照片的文件夹
	 * @param havePhoto 是否只存储照片存在的考生信息到考生表
	 * @return
	 * @throws IOException       0404
	 */
	@PostMapping("/BLsavBathData")
	@RequiresPermissions("student:studentBLXX:add")
	public String BLsavBathData(@RequestParam(value="filename") MultipartFile file, @RequestParam(value = "type") String type , HttpServletRequest request, HttpServletResponse response,
								HttpSession session, Model model, MultipartFile multipartFiles, boolean havePhoto) throws IOException, ParseException {
		//@RequestParam(value = "photoName")
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
			return "center/student/studentBL/studentTemp";
		}

		//获取文件名
		String fileName=file.getOriginalFilename();

		//验证文件名是否合格
		if(!ExcelImportUtils.validatedbf(fileName)){
			session.setAttribute("msg","文件必须是dbf格式！");
			model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "type", "type"));
			return "redirect:center/student/studentBL/studentTemp";
		}

		//进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
		long size=file.getSize();
		if(StringUtils.isEmpty(fileName) || size==0){
			request.setAttribute("msg","文件不能为空！");
			model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
			return "center/student/studentBL/studentTemp";
		}

		request.setCharacterEncoding("UTF-8");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		//批量导入
		String message = studentService.importInfoBl(fileName,file, type, multipartRequest, multipartFiles, havePhoto);
		request.setAttribute("msg",message);
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
		return "center/student/studentBL/studentTemp";
	}

}
