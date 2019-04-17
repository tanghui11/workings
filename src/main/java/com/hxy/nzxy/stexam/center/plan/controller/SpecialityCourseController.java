package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.SpecialityCourseService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.SpecialityCourseDO;
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
 * 专业课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:50
 */
 
@Controller
@RequestMapping("/plan/specialityCourse")
public class SpecialityCourseController extends SystemBaseController{
	@Autowired
	private SpecialityCourseService specialityCourseService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("plan:specialityCourse:specialityCourse")
	String SpecialityCourse(){

		return "center/plan/specialityCourse/specialityCourse";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<SpecialityCourseDO> specialityCourseList = specialityCourseService.list(query);
        for (SpecialityCourseDO item : specialityCourseList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setType( FieldDictUtil.get(getAppName(), "pla_speciality_course", "type",item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(),"pla_speciality_course",  "classify",item.getClassify()));
			item.setStatus(FieldDictUtil.get(getAppName(), "pla_speciality_course", "status",item.getStatus()));
            item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
            item.setBookName(FieldDictUtil.get(getAppName(), "pla_book_nzxy", "id", String.valueOf(item.getBookid())));
		}
		int total = specialityCourseService.count(query);
		PageUtils pageUtils = new PageUtils(specialityCourseList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("plan:specialityCourse:add")
	String add(Model model){
		model.addAttribute("pla_speciality_course_type", commonService.listFieldDict(getAppName(), "pla_speciality_course", "type"));
		model.addAttribute("pla_speciality_course_classify", commonService.listFieldDict(getAppName(), "pla_speciality_course", "classify"));
		model.addAttribute("pla_speciality_course_status", commonService.listFieldDict(getAppName(), "pla_speciality_course", "status"));

		return "center/plan/specialityCourse/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:specialityCourse:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SpecialityCourseDO specialityCourse = specialityCourseService.get(id);
		model.addAttribute("specialityCourse", specialityCourse);
		model.addAttribute("pla_speciality_course_type", commonService.listFieldDict(getAppName(), "pla_speciality_course", "type"));
		model.addAttribute("pla_speciality_course_classify", commonService.listFieldDict(getAppName(), "pla_speciality_course", "classify"));
		model.addAttribute("pla_speciality_course_status", commonService.listFieldDict(getAppName(), "pla_speciality_course", "status"));

		return "center/plan/specialityCourse/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:specialityCourse:add")
	public R save( SpecialityCourseDO specialityCourse){
        specialityCourse.setOperator(ShiroUtils.getUserId().toString());
		if(specialityCourseService.save(specialityCourse)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:specialityCourse:edit")
	public R update( SpecialityCourseDO specialityCourse){
	    specialityCourse.setOperator(ShiroUtils.getUserId().toString());
		specialityCourseService.update(specialityCourse);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:specialityCourse:remove")
	public R remove( Long id){
		if(specialityCourseService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:specialityCourse:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		specialityCourseService.batchRemove(ids);
		return R.ok();
	}

	//弹出导入页面
	@GetMapping("/importData")
	String importData(String specialityRecord,Model model){
		model.addAttribute("specialityRecordid",specialityRecord);
		return "center/plan/specialityCourse/importData";
	}

	/**
	 * 批量导入
	 */
	@PostMapping("/savBathData")
	@RequiresPermissions("plan:specialityCourse:add")
	public String savBathData(@RequestParam(value="filename") MultipartFile file,
							  String specialityRecordid, HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/plan/specialityCourse/importData";
		}
System.out.println(specialityRecordid);
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
			return "center/plan/specialityCourse/importData";
		}

		//导入
		String message = specialityCourseService.batchImport(fileName,file,specialityRecordid);
		request.setAttribute("msg",message);
		return "center/plan/specialityCourse/importData";
	}


	//导出
	@RequestMapping("/searchOutExcel")
	public String searchOutEXcel(@RequestParam Map<String, Object> params,HttpServletRequest request, HttpServletResponse response){
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
		params.put("specialityRecordid",params.get("specialityRecord"));
		ZipOutputStream out = null;
		List<SpecialityCourseDO> specialityCourseList = specialityCourseService.list(params);
		request.getSession().setAttribute("totalCount", specialityCourseList.size());
		for (SpecialityCourseDO item : specialityCourseList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setType( FieldDictUtil.get(getAppName(), "pla_speciality_course", "type",item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(),"pla_speciality_course",  "classify",item.getClassify()));
			item.setStatus(FieldDictUtil.get(getAppName(), "pla_speciality_course", "status",item.getStatus()));
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
			item.setBookName(FieldDictUtil.get(getAppName(), "pla_book_nzxy", "id", String.valueOf(item.getBookid())));
		}
		if (specialityCourseList != null && specialityCourseList.size() > 0) {
			String[][] result = new String[specialityCourseList.size() + 1][9];
			result[0] = new String[] { "序号","课程代码", "课程名称","教材名称", "类别",  "分类","序号","状态"};
			if (specialityCourseList != null && specialityCourseList.size() > 0) {
				for (int i = 0; i < specialityCourseList.size(); i++) {
					SpecialityCourseDO specialityCourseDO = specialityCourseList.get(i);
					result[i + 1][0] = String.valueOf(i + 1);
					result[i + 1][1] = String.valueOf(specialityCourseDO.getCourseid());
					result[i + 1][2] = String.valueOf(specialityCourseDO.getCourseName());
					result[i + 1][3] = String.valueOf(specialityCourseDO.getBookName());
					result[i + 1][4] = String.valueOf(specialityCourseDO.getType());
					result[i + 1][5] = String.valueOf(specialityCourseDO.getClassify());
					result[i + 1][6] = String.valueOf(specialityCourseDO.getSeq());
					result[i + 1][7] = String.valueOf(specialityCourseDO.getStatus());
					//进度条写入进度
					double dPercent=(double)(i)/specialityCourseList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%
				}
			}
			String tempFileName="开考专业课程设置导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="开考专业课程设置导出信息" +date+".zip";
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
			return "center/plan/specialityCourse/specialityCourse";
		}
		//进度条，走到100%
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", specialityCourseList.size());
		return null;
	}

	@ResponseBody
	@GetMapping("/scoreInput")
	@RequiresPermissions("plan:specialityCourse:specialityCourse")
	public PageUtils scoreInput(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<SpecialityCourseDO> specialityCourseList = specialityCourseService.list(query);
		for (SpecialityCourseDO item : specialityCourseList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setType( FieldDictUtil.get(getAppName(), "pla_speciality_course", "type",item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(),"pla_speciality_course",  "classify",item.getClassify()));
			item.setStatus(FieldDictUtil.get(getAppName(), "pla_speciality_course", "status",item.getStatus()));
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
			item.setBookName(FieldDictUtil.get(getAppName(), "pla_book_nzxy", "id", String.valueOf(item.getBookid())));
		}
		int total = specialityCourseService.count(query);
		PageUtils pageUtils = new PageUtils(specialityCourseList, total);
		return pageUtils;
	}
}