package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.CourseClassifyService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.CourseClassifyDO;
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
 * 课程分类
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-07-30 10:05:51
 */
 
@Controller
@RequestMapping("/plan/courseClassify")
public class CourseClassifyController extends SystemBaseController {
	@Autowired
	private CourseClassifyService courseClassifyService;
	@Autowired
	private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("plan:courseClassify:courseClassify")
	String CourseClassify(Model model){
		model.addAttribute("pla_course_classify_type", commonService.listFieldDict(getAppName(), "pla_course_classify", "type"));

	    return "center/plan/courseClassify/courseClassify";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:courseClassify:courseClassify")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CourseClassifyDO> courseClassifyList = courseClassifyService.list(query);
		for (CourseClassifyDO item : courseClassifyList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_course_classify", "type", item.getType()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));

			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = courseClassifyService.count(query);
		PageUtils pageUtils = new PageUtils(courseClassifyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:courseClassify:add")
	String add(Model model){
		model.addAttribute("pla_course_classify_type", commonService.listFieldDict(getAppName(), "pla_course_classify", "type"));

		return "center/plan/courseClassify/add";
	}

	@GetMapping("/edit/{type}/{courseid}")
	@RequiresPermissions("plan:courseClassify:edit")
	String edit(@PathVariable("type") String type,@PathVariable("courseid") String courseid,Model model){
		if(type.equals("本科")){
			type="5";
		} else if(type.equals("专科")){
			type="6";
		}
		CourseClassifyDO courseClassify = courseClassifyService.get(type, courseid);
		model.addAttribute("pla_course_classify_type", commonService.listFieldDict(getAppName(), "pla_course_classify", "type"));

		model.addAttribute("courseClassify", courseClassify);
	    return "center/plan/courseClassify/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:courseClassify:add")
	public R save( CourseClassifyDO courseClassify){
		courseClassify.setOperator(ShiroUtils.getUserId().toString());
		if(courseClassifyService.save(courseClassify)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:courseClassify:edit")
	public R update( CourseClassifyDO courseClassify){
		courseClassify.setOperator(ShiroUtils.getUserId().toString());
		courseClassifyService.update(courseClassify);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:courseClassify:remove")
	public R remove( String type,String courseid){
		if(type.equals("本科")){
			type="5";
		} else if(type.equals("专科")){
			type="6";
		}
		if(courseClassifyService.remove(type,courseid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 批量删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:courseClassify:batchRemove")
	public R remove(@RequestParam("ids[]") String[] types){
		courseClassifyService.batchRemove(types);
		return R.ok();
	}



	//弹出导入页面
	@GetMapping("/importData")

	String importData( ){

		return "center/plan/courseClassify/importData";
	}
	/**
	 * 批量导入
     * /stexam/plan/courseclassify/savBathData
	 */
	@PostMapping("/savBathData")
	@RequiresPermissions("plan:courseClassify:add")
	public String savBathData(@RequestParam(value="filename") MultipartFile file,
							  HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException{
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/plan/courseClassify/importData";
		}

		//获取文件名
		String fileName=file.getOriginalFilename();

		//验证文件名是否合格
		if(!ExcelImportUtils.validateExcel(fileName)){
			session.setAttribute("msg","文件必须是excel格式！");
			return "redirect:toUserKnowledgeImport";
		}

		//进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
		long size=file.getSize();
		if(StringUtils.isEmpty(fileName) || size==0){
			request.setAttribute("msg","文件不能为空！");
			return "center/plan/courseClassify/importData";
		}

		//批量导入
		String message = courseClassifyService.batchImport(fileName,file);
		request.setAttribute("msg",message);
        return "center/plan/courseClassify/importData";
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

		ZipOutputStream out = null;
		List<CourseClassifyDO> courseClassifyList = courseClassifyService.list(params);
		request.getSession().setAttribute("totalCount", courseClassifyList.size());
		for (CourseClassifyDO item : courseClassifyList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_course_classify", "type", item.getType()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		if (courseClassifyList != null && courseClassifyList.size() > 0) {
			String[][] result = new String[courseClassifyList.size() + 1][6];
			result[0] = new String[] { "序号","类别", "课程名称", "操作员",  "操作日期"};
			if (courseClassifyList != null && courseClassifyList.size() > 0) {
				for (int i = 0; i < courseClassifyList.size(); i++) {
					CourseClassifyDO courseClassifyDO = courseClassifyList.get(i);
					result[i + 1][0] = String.valueOf(i + 1);
					result[i + 1][1] = String.valueOf(courseClassifyDO.getType());
					result[i + 1][2] = String.valueOf(courseClassifyDO.getCourseid());
					result[i + 1][3] = String.valueOf(courseClassifyDO.getOperator());
					result[i + 1][4] = String.valueOf(courseClassifyDO.getUpdateDate());

					//进度条写入进度
					double dPercent=(double)(i)/courseClassifyList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%
				}
			}
			String tempFileName="课程分类信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="课程分类信息" +date+".zip";
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
			return "center/plan/courseClassify/courseClassify";
		}


		//进度条，走到100%
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", courseClassifyList.size());

		return null;
	}
}
