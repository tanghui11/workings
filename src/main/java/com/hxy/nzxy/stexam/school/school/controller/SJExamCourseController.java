package com.hxy.nzxy.stexam.school.school.controller;

import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.ExamCourseDO;
import com.hxy.nzxy.stexam.school.school.service.ZXExamCourseService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import org.apache.commons.configuration.Configuration;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
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
@RequestMapping("/school/examCourseSJ")
public class SJExamCourseController extends SystemBaseController{
	@Autowired
	private ZXExamCourseService zxexamCourseService;
    @Autowired
    private CommonService commonService;

	@GetMapping()
	@RequiresPermissions("school:examCourseSJ:examCourse")
	String ExamCourse(Model model){
		model.addAttribute("exa_exam_course_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "type"));
		model.addAttribute("exa_exam_course_classify", commonService.listFieldDict(getAppName(), "exa_exam_course", "classify"));
		model.addAttribute("exa_exam_course_card_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "card_type"));
	    return "school/school/examCourseSJ/examCourse";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:examCourseSJ:examCourse")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		String examTaskid= (String)params.get("examTaskid");
		if(examTaskid!=null&&!"".equals(examTaskid)) {
			Query query = new Query(params);
			List<ExamCourseDO> examCourseList = zxexamCourseService.list(query);
			for (ExamCourseDO item : examCourseList) {
				item.setType(FieldDictUtil.get(getAppName(), "exa_exam_course", "type", item.getType()));
				item.setClassify(FieldDictUtil.get(getAppName(), "exa_exam_course", "classify", item.getClassify()));
				item.setCardType(FieldDictUtil.get(getAppName(), "exa_exam_course", "card_type", item.getCardType()));
				item.setAuditStatus(FieldDictUtil.get(getAppName(), "exa_exam_course", "auditStatus", item.getAuditStatus()));
				item.setAuditStatus(FieldDictUtil.get(getAppName(), "exa_exam_course_nzxy", "type", item.getCourse_type()));
				item.setOperator(UserUtil.getName(item.getOperator()));
				item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			}
			int total = zxexamCourseService.count(query);
			PageUtils pageUtils = new PageUtils(examCourseList, total);
			return pageUtils;
		}else
		{
			PageUtils pageUtils = new PageUtils(new ArrayList(), 0);
			return pageUtils;
		}
	}


	@GetMapping("/add")
	@RequiresPermissions("school:examCourseSJ:add")
	String add(Model model){
		model.addAttribute("exa_exam_course_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "type"));
		model.addAttribute("exa_exam_course_classify", commonService.listFieldDict(getAppName(), "exa_exam_course", "classify"));
		model.addAttribute("exa_exam_course_card_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "card_type"));

		return "school/school/examCourseSJ/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:examCourseSJ:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ExamCourseDO examCourse = zxexamCourseService.get(id);
		model.addAttribute("examCourse", examCourse);
		model.addAttribute("exa_exam_course_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "type"));
		model.addAttribute("exa_exam_course_classify", commonService.listFieldDict(getAppName(), "exa_exam_course", "classify"));
		model.addAttribute("exa_exam_course_card_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "card_type"));

		return "school/school/examCourseSJ/edit";
	}
	
	/**
	 * 保存
	 */
		@ResponseBody
		@PostMapping("/save")
		@RequiresPermissions("school:examCourseSJ:add")
		public R save( ExamCourseDO examCourse){
			//设置题卡类型默认为通卡
			if (examCourse.getCardType().equals("")||examCourse.getCardType()==null){
				Configuration config = getConfig("config.properties");
				String card_type =config.getString("card_type");
				examCourse.setCourseType(card_type);
			}
			String schoolid = ShiroUtils.getUser().getWorkerid();
		examCourse.setSchoolid(Long.valueOf(schoolid));
		examCourse.setCourseType("c");
        examCourse.setOperator(ShiroUtils.getUserId().toString());
		if(zxexamCourseService.save(examCourse)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:examCourseSJ:edit")
	public R update( ExamCourseDO examCourse){
	    examCourse.setOperator(ShiroUtils.getUserId().toString());
		zxexamCourseService.update(examCourse);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:examCourseSJ:remove")
	public R remove( Long id){
		if(zxexamCourseService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("SCHOOL:examCourseSJ:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		zxexamCourseService.batchRemove(ids);
		return R.ok();
	}


	//导出
	@RequestMapping("/searchOutExcel")
	public String searchOutEXcel(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request, Model model){
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
		params.put("type",'c');
		List<ExamCourseDO> examCourseList = zxexamCourseService.list(params);
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
			result[0] = new String[] { "序号","课程代码", "课程名称","教材名称","类别","命题类别","题卡类别","操作员","操作日期"};
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
					double dPercent=(double)(i)/examCourseList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%;
				}
			}
			String tempFileName="考试课程设置信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="考试课程设置信息" +date+".zip";
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
			model.addAttribute("exa_exam_course_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "type"));
			model.addAttribute("exa_exam_course_classify", commonService.listFieldDict(getAppName(), "exa_exam_course", "classify"));
			model.addAttribute("exa_exam_course_card_type", commonService.listFieldDict(getAppName(), "exa_exam_course", "card_type"));
			return "school/school/examCourseSJ/examCourse";
		}
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", examCourseList.size());
		return null;
	}


}
