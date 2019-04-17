package com.hxy.nzxy.stexam.center.exam.controller;

import com.hxy.nzxy.stexam.center.exam.service.ExamCourseService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.ExamCourseDO;
import com.hxy.nzxy.stexam.domain.ListPlaceDO;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 开考课程
 *
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 15:58:19
 */

@Controller
@RequestMapping("/exam/examCourse")
public class ExamCourseController extends SystemBaseController{
	@Autowired
	private ExamCourseService examCourseService;
	@Autowired
	private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("exam:examCourse:examCourse")
	String ExamCourse(Model model,HttpServletRequest request,String id ,String examYear,String examMonth,String type){

		request.setAttribute("id",id);
		request.setAttribute("examYear",examYear);
		request.setAttribute("examMonth",examMonth);
		request.setAttribute("type",type);

		model.addAttribute("exa_exam_course_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "type"));
		model.addAttribute("exa_exam_course_classify", commonService.listFieldDict(getAppName(), "exa_exam_course", "classify"));
		model.addAttribute("exa_exam_course_card_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "card_type"));
		model.addAttribute("exam_task_type", commonService.listFieldDict(getAppName(), "exam_task", "type"));
		model.addAttribute("exa_secret_office_audit_status", commonService.listFieldDict(getAppName(), "exa_secret_office", "audit_status"));
		return "center/exam/examCourse/examCourse";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exam:examCourse:examCourse")
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
				item.setSchoolName(FieldDictUtil.get(getAppName(), "sch_school_nzxy", "id", item.getSchoolid()+""));
				item.setAuditStatus(FieldDictUtil.get(getAppName(), "exa_secret_office", "audit_status", item.getAuditStatus()));
				item.setCourseType(FieldDictUtil.get(getAppName(), "exam_task", "type", item.getCourseType()));
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

	@ResponseBody
	@RequestMapping("/coursePlaceT")
	//@RequiresPermissions("exam:examCourse:place")
	public List<ListPlaceDO> listPlace(@RequestParam Map<String, Object> params){
		List<ListPlaceDO> list = new ArrayList<ListPlaceDO>();
		list = examCourseService.listPlace(params.get("courseid").toString());
		for (ListPlaceDO key : list){
			key.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", key.getType()));
		}
		return list;
	}
	@GetMapping("/add")
	@RequiresPermissions("exam:examCourse:add")
	String add(Model model){
		model.addAttribute("exa_exam_course_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "type"));
		model.addAttribute("exa_exam_course_classify", commonService.listFieldDict(getAppName(), "exa_exam_course", "classify"));
		model.addAttribute("exa_exam_course_card_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "card_type"));
		model.addAttribute("exam_task_type", commonService.listFieldDict(getAppName(), "exam_task", "type"));

		return "center/exam/examCourse/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exam:examCourse:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExamCourseDO examCourse = examCourseService.get(id);
		model.addAttribute("examCourse", examCourse);
		model.addAttribute("exa_exam_course_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "type"));
		model.addAttribute("exa_exam_course_classify", commonService.listFieldDict(getAppName(), "exa_exam_course", "classify"));
		model.addAttribute("exa_exam_course_card_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "card_type"));
		model.addAttribute("exam_task_type", commonService.listFieldDict(getAppName(), "exam_task", "type"));

		return "center/exam/examCourse/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exam:examCourse:add")
	public R save( ExamCourseDO examCourse){
		//设置题卡类型默认为通卡
		if (examCourse.getCardType().equals("")||examCourse.getCardType()==null){
			Configuration config = getConfig("config.properties");
			String card_type =config.getString("card_type");
			examCourse.setCourseType(card_type);
		}
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
	@RequiresPermissions("exam:examCourse:edit")
	public R update( ExamCourseDO examCourse){
		examCourse.setOperator(ShiroUtils.getUserId().toString());
		examCourseService.update(examCourse);
		return R.ok();
	}

	/**
	 * 修改2
	 */
	@ResponseBody
	@RequestMapping("/updateAuait")
	@RequiresPermissions("exam:examCourse:edit")
	public R updateAuait( ExamCourseDO examCourse){
		examCourse.setOperator(ShiroUtils.getUserId().toString());
		examCourseService.updateAudit(examCourse);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exam:examCourse:remove")
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
	@RequiresPermissions("exam:examCourse:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		examCourseService.batchRemove(ids);
		return R.ok();
	}


	//导出
        @RequestMapping("/searchOutExcel")
        public String searchOutEXcel(@RequestParam Map<String, Object> params,HttpServletRequest request, HttpServletResponse response, Model model){

			//进度条清session
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
		ZipOutputStream out = null;
	//	params.put("examTaskid",params.get("searchName"));
			if(params.get("examTaskid")!=null&&!"".equals(params.get("examTaskid"))) {
				List<ExamCourseDO> examCourseList = examCourseService.list(params);

				if (examCourseList.size() == 0) {
					model.addAttribute("exa_exam_course_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "type"));
					model.addAttribute("exa_exam_course_classify", commonService.listFieldDict(getAppName(), "exa_exam_course", "classify"));
					model.addAttribute("exa_exam_course_card_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "card_type"));
					model.addAttribute("exam_task_type", commonService.listFieldDict(getAppName(), "exam_task", "type"));
					model.addAttribute("exa_secret_office_audit_status", commonService.listFieldDict(getAppName(), "exa_secret_office", "audit_status"));
					return "center/exam/examCourse/examCourse";
				}
				request.getSession().setAttribute("totalCount", examCourseList.size());
				for (ExamCourseDO item : examCourseList) {
					item.setType(FieldDictUtil.get(getAppName(), "exa_exam_course", "type", item.getType()));
					item.setClassify(FieldDictUtil.get(getAppName(), "exa_exam_course", "classify", item.getClassify()));
					item.setCardType(FieldDictUtil.get(getAppName(), "exa_exam_course", "card_type", item.getCardType()));
					item.setOperator(UserUtil.getName(item.getOperator()));
					item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
				}

				if (examCourseList != null && examCourseList.size() > 0) {
					String[][] result = new String[examCourseList.size() + 1][10];
					result[0] = new String[]{"序号", "课程代码", "课程名称", "教材名称", "类别", "命题类别", "题卡类别", "操作员", "操作日期"};
					if (examCourseList != null && examCourseList.size() > 0) {
						for (int i = 0; i < examCourseList.size(); i++) {
							ExamCourseDO examCourseDO = examCourseList.get(i);
							result[i + 1][0] = String.valueOf(i + 1);
							result[i + 1][1] = String.valueOf(examCourseDO.getCourseid());
							result[i + 1][2] = String.valueOf(examCourseDO.getCourseName());
							result[i + 1][3] = String.valueOf(examCourseDO.getBookName());
							result[i + 1][4] = String.valueOf(examCourseDO.getType());
							result[i + 1][5] = String.valueOf(examCourseDO.getClassify());
							result[i + 1][6] = String.valueOf(examCourseDO.getCardType());
							result[i + 1][7] = String.valueOf(examCourseDO.getOperator());
							result[i + 1][8] = String.valueOf(examCourseDO.getUpdateDate());

							//进度条写入进度
							double dPercent = (double) (i) / examCourseList.size();   //将计算出来的数转换成double
							int percent = (int) (dPercent * 100);               //再乘上100取整
							request.getSession().setAttribute("curCount", i);
							request.getSession().setAttribute("percent", percent);    //比如这里是50
							request.getSession().setAttribute("percentText", percent + "%");//这里是50%
						}
					}

					String tempFileName = "考试课程设置导出信息" + ".xlsx";
					ExcelUtil.writeExcelOs(result, Filepath + tempFileName);
					BufferedInputStream buffer1 = null;
					String date = "";
					try {
						date = DateUtil.formatDate(new Date());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					OutputStream out1 = null;
					String zipName = "考试课程设置导出信息" + date + ".zip";
					ZipUtils.createZip(Filepath, strZipPath + zipName);
					try {
						File fs = new File(strZipPath + zipName);
						//检查该文件是否存在
						if (!fs.exists()) {
							return null;
						}
						buffer1 = new BufferedInputStream(new FileInputStream(fs));
						byte[] buf = new byte[1024];
						int len = 0;
						response.reset();
						response.setContentType("application/x-msdownload");
						response.setHeader("Content-disposition", "attachment;filename=" + new String(fs.getName().getBytes("gbk"), "iso8859-1"));
						out1 = response.getOutputStream();
						while ((len = buffer1.read(buf)) > 0) {
							out1.write(buf, 0, len);
						}
					} catch (Throwable e) {
					} finally {
						try {
							buffer1.close();
							out1.close();
						} catch (Throwable e) {

							e.printStackTrace();
						}
					}
				} else {
					model.addAttribute("exa_exam_course_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "type"));
					model.addAttribute("exa_exam_course_classify", commonService.listFieldDict(getAppName(), "exa_exam_course", "classify"));
					model.addAttribute("exa_exam_course_card_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "card_type"));
					model.addAttribute("exam_task_type", commonService.listFieldDict(getAppName(), "exam_task", "type"));
					model.addAttribute("exa_secret_office_audit_status", commonService.listFieldDict(getAppName(), "exa_secret_office", "audit_status"));
					return "center/exam/examCourse/examCourse";
				}
				//进度条，走到100%
				request.getSession().setAttribute("percent", 100);    //比如这里是50
				request.getSession().setAttribute("percentText", 100 + "%");//这里是50%
				request.getSession().setAttribute("curCount", examCourseList.size());
			}
			return null;
	}

	@GetMapping("place")
	String place(){
		return "center/exam/examCourse/place";
	}

	@ResponseBody
	@RequestMapping(value = "/placeCourse", method =RequestMethod.POST)
	public int addPlaceCourse(@RequestParam("courseid") String courseid,@RequestParam("id") String id){
		int myRes = 0;
		String operator = ShiroUtils.getUserId().toString();
		int ii = examCourseService.ifPlaceCourse(Long.valueOf(courseid), Long.valueOf(id), operator);
		if(ii == 0){
			Integer res = examCourseService.addPlaceCourse(Long.valueOf(courseid), Long.valueOf(id), operator);
			if (res > 0){
				myRes = 1;
			}
		}
		return myRes;
	}
	@ResponseBody
	@RequestMapping(value = "/deletePlaceCourse")
	public int deletePlaceCourse( String courseid, String id){
		int i =0;
		String operator = ShiroUtils.getUserId().toString();
		Integer res = examCourseService.deletePlaceCourse(Long.parseLong(courseid), Long.valueOf(id),operator);
		if (res > 0){
			i = 1;
		}
		return i;
	}

	@GetMapping("/coursePlace")
	String coursePlace(){
		return "/center/exam/examCourse/coursePlace";
	}

	@ResponseBody
	@RequestMapping(value = "/placeCourse", method =RequestMethod.GET)
	public List<ExamCourseDO> getPlaceCourse(@RequestParam("courseid") String courseid){
		List<ExamCourseDO> examCourseList = examCourseService.listPl(courseid);

		for (ExamCourseDO item : examCourseList) {
			item.setSpecialityName(FieldDictUtil.get(getAppName(), "pla_speciality_nzxy", "id", item.getSpecialityid()));
			item.setType(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", item.getType()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		return examCourseList;
	}

	@GetMapping("placeCourses")
	String placeCourse(){
		return  "/center/exam/examCourse/placeCourse";
	}

	//弹出导入页面
	@GetMapping("/importData")
	String importData(String examTaskid,String examTimeid,Model model){
		model.addAttribute("examTaskid", examTaskid);
		model.addAttribute("examTimeid", examTimeid);
		return "center/exam/examCourse/importData";
	}

	// 批量导入
	@PostMapping("/savBathData")
	@RequiresPermissions("exam:examCourse:add")
	public String savBathData(@RequestParam(value="filename") MultipartFile file,
							  HttpServletRequest request, HttpServletResponse response, HttpSession session, String examTaskid, String examTimeid) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/exam/examCourse/importData";
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
			return "center/exam/examCourse/importData";
		}

		//批量导入
		String message = examCourseService.batchImport(fileName,file,examTaskid,examTimeid);
		request.setAttribute("msg",message);
		return "center/exam/examCourse/importData";
	}

}
