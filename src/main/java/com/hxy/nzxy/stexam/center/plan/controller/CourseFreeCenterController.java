package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.domain.SpecialityDO;
import com.hxy.nzxy.stexam.system.service.FieldDictService;
import org.apache.commons.configuration.Configuration;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hxy.nzxy.stexam.domain.CourseFreeDO;
import com.hxy.nzxy.stexam.center.plan.service.CourseFreeCenterService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;

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
 * 课程免考规则
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-10-16 10:13:51
 */
 
@Controller
@RequestMapping("/plan/courseFreeCenter")
public class CourseFreeCenterController extends SystemBaseController{
	@Autowired
	private CourseFreeCenterService courseFreeCenterService;
    @Autowired
    private CommonService commonService;

	@GetMapping()
	@RequiresPermissions("plan:courseFreeCenter:courseFreeCenter")
	String CourseFreeCenter(Model model){
		model.addAttribute("pla_course_free_type", commonService.listFieldDict(getAppName(), "pla_course_free", "type"));
		return "center/plan/courseFreeCenter/courseFreeCenter";
	}
	@GetMapping("/courseFreeOpen")
	String CourseFreeOpen(Model model){
		model.addAttribute("pla_course_free_type", commonService.listFieldDict(getAppName(), "pla_course_free", "type"));
		return "center/plan/courseFreeCenter/courseFreeCenterOpen";
	}

	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CourseFreeDO> courseFreeCenterList = courseFreeCenterService.list(query);
        for (CourseFreeDO item : courseFreeCenterList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setType(FieldDictUtil.get(getAppName(), "pla_course_free", "type", item.getType()));

			item.setStatus(FieldDictUtil.get(getAppName(), "pla_course_free", "status", item.getStatus()));
        }
		int total = courseFreeCenterService.count(query);
		PageUtils pageUtils = new PageUtils(courseFreeCenterList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:courseFreeCenter:add")
	String add(Model model){

		model.addAttribute("pla_course_free_type", commonService.listFieldDict(getAppName(), "pla_course_free", "type"));
		model.addAttribute("pla_course_free_status", commonService.listFieldDict(getAppName(), "pla_course_free", "status"));

		return "center/plan/courseFreeCenter/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:courseFreeCenter:edit")
	String edit(@PathVariable("id") String id,Model model){
		CourseFreeDO courseFreeCenter = courseFreeCenterService.get(id);
		model.addAttribute("pla_course_free_type", commonService.listFieldDict(getAppName(), "pla_course_free", "type"));
		model.addAttribute("pla_course_free_status", commonService.listFieldDict(getAppName(), "pla_course_free", "status"));
		model.addAttribute("courseFreeCenter", courseFreeCenter);
	    return "center/plan/courseFreeCenter/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:courseFreeCenter:add")
	public R save( CourseFreeDO courseFreeCenter){
        courseFreeCenter.setOperator(ShiroUtils.getUserId().toString());
		if(courseFreeCenterService.save(courseFreeCenter)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:courseFreeCenter:edit")
	public R update( CourseFreeDO courseFreeCenter){
	    courseFreeCenter.setOperator(ShiroUtils.getUserId().toString());
		courseFreeCenterService.update(courseFreeCenter);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:courseFreeCenter:remove")
	public R remove( String id){
		if(courseFreeCenterService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:courseFreeCenter:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		courseFreeCenterService.batchRemove(ids);
		return R.ok();
	}

	//课程免考规则导入页面
	@GetMapping("/MKimportData")

	String MKimportData( ){

		return "center/plan/courseFreeCenter/MKimportData";
	}

	/**
	 * 批量导入
	 */
	@PostMapping("/MKsavBathData")
	@RequiresPermissions("plan:courseFreeCenter:add")
	public String MKsavBathData(@RequestParam(value="filename") MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/plan/courseFreeCenter/MKimportData";
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
			return "center/plan/courseFreeCenter/MKimportData";
		}

		//批量导入
		String message = courseFreeCenterService.MKbatchImport(fileName,file);
		request.setAttribute("msg",message);
		return "center/plan/courseFreeCenter/MKimportData";
	}

	//课程免考规则批量导出
	@RequestMapping("/searchOutMKExcel")
	public String searchOutEXcel(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request, Model model){
		request.getSession().removeAttribute("totalCount");
		request.getSession().removeAttribute("curCount");
		request.getSession().removeAttribute("percent");
		request.getSession().removeAttribute("percentText");
		//查询列表数据
		Configuration config = getConfig("config.properties");
		String configsrc =config.getString("url");
		String Filepath = configsrc+ "courseFreeCenter/";
		String strZipPath=configsrc+"courseFreeCenterZip/";
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
		//******************************************************************************************************************************************
		ZipOutputStream out=null;
		List<CourseFreeDO> courseFreeList = courseFreeCenterService.list(params);
		request.getSession().setAttribute("totalCount", courseFreeList.size());
		for (CourseFreeDO item : courseFreeList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_course_free", "type", item.getType()));
			item.setStatus(FieldDictUtil.get(getAppName(), "pla_course_free", "status", item.getStatus()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}

		if (courseFreeList != null && courseFreeList.size() > 0) {
			String[][] result = new String[courseFreeList.size() + 1][8];

			result[0] = new String[] { "序号", "名称", "备注", "状态", "操作人", "操作时间", "顶替类型"};
			if (courseFreeList != null && courseFreeList.size() > 0) {
				for (int i = 0; i < courseFreeList.size(); i++) {
					CourseFreeDO courseFree = courseFreeList.get(i);
					result[i + 1][0] = String.valueOf(courseFree.getId());
					result[i + 1][1] = String.valueOf(courseFree.getName());
					result[i + 1][2] = String.valueOf(courseFree.getRemark());
					result[i + 1][3] = String.valueOf(courseFree.getStatus());
					result[i + 1][4] = String.valueOf(courseFree.getOperator());
					result[i + 1][5] = String.valueOf(DateUtils.format(courseFree.getUpdateDate(), DateUtils.DATE_PATTERN));
					result[i + 1][6] = String.valueOf(courseFree.getType());
					double dPercent=(double)(i)/courseFreeList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%;
				}
			}
			String tempFileName="免考规则导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="免考规则导出信息" +date+".zip";
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
			model.addAttribute("pla_course_free_type", commonService.listFieldDict(getAppName(), "pla_course_free", "type"));
			return "center/plan/courseFreeCenter/courseFreeCenter";
		}
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", courseFreeList.size());

		return null;
	}
}
