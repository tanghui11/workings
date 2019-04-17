package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.CertificateReplaceItemService;
import com.hxy.nzxy.stexam.center.plan.service.CertificateReplaceService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.CertificateReplaceDO;
import com.hxy.nzxy.stexam.domain.CommonCourseReplaceItemNewDO;
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
 * 证书顶替
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
 
@Controller
@RequestMapping("/plan/certificateReplace")
public class CertificateReplaceController extends SystemBaseController{
	@Autowired
	private CertificateReplaceService certificateReplaceService;
    @Autowired
    private CommonService commonService;

    @Autowired
	private CertificateReplaceItemService certificateReplaceItemService;
	@GetMapping()
	@RequiresPermissions("plan:certificateReplace:certificateReplace")
	String CertificateReplace(){
	    return "center/plan/certificateReplace/certificateReplace";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:certificateReplace:certificateReplace")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CertificateReplaceDO> certificateReplaceList = certificateReplaceService.list(query);
        for (CertificateReplaceDO item : certificateReplaceList) {
			item.setCourseClass(FieldDictUtil.get(getAppName(), "pla_old_course", "type", item.getCourseClass()));
            item.setOperator(UserUtil.getName(item.getOperator()));
			item.setOldCourseName(FieldDictUtil.get(getAppName(), "pla_old_course_nzxy", "id", item.getOldCourseid())+"("+item.getOldCourseid()+")");
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = certificateReplaceService.count(query);
		PageUtils pageUtils = new PageUtils(certificateReplaceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:certificateReplace:add")
	String add(Model model){
		model.addAttribute("pla_old_course_type", commonService.listFieldDict(getAppName(), "pla_old_course", "type"));
		return "center/plan/certificateReplace/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:certificateReplace:edit")
	String edit(@PathVariable("id") Long id,Model model){
		CertificateReplaceDO certificateReplace = certificateReplaceService.get(id);
		model.addAttribute("pla_old_course_type", commonService.listFieldDict(getAppName(), "pla_old_course", "type"));
		model.addAttribute("certificateReplace", certificateReplace);
	    return "center/plan/certificateReplace/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:certificateReplace:add")
	public R save( CertificateReplaceDO certificateReplace){
        certificateReplace.setOperator(ShiroUtils.getUserId().toString());
		if(certificateReplaceService.save(certificateReplace)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:certificateReplace:edit")
	public R update( CertificateReplaceDO certificateReplace){
	    certificateReplace.setOperator(ShiroUtils.getUserId().toString());
		certificateReplaceService.update(certificateReplace);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:certificateReplace:remove")
	public R remove( Long id){
		if(certificateReplaceService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:certificateReplace:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		certificateReplaceService.batchRemove(ids);
		return R.ok();
	}

	//通用课程顶替导入页面
	@GetMapping("/ZSimportData")

	String ZSimportData( ){

		return "center/plan/certificateReplace/ZSimportData";
	}


	/**
	 * 批量导入
	 */
	@PostMapping("/ZSsavBathData")
	@RequiresPermissions("plan:certificateReplace:add")
	public String ZSsavBathData(@RequestParam(value="filename") MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/plan/certificateReplace/ZSimportData";
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
			return "center/plan/certificateReplace/ZSimportData";
		}

		//批量导入
		String message = certificateReplaceService.ZSbatchImport(fileName,file);
		request.setAttribute("msg",message);
		return "center/plan/certificateReplace/ZSimportData";
	}

	/**
	 * 证书顶替课程批量导出
	 * @param params
	 * @param response
	 * @return
	 */
	@RequestMapping("/searchOutZSExcel")
	public String searchOutTYExcel(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request){
		request.getSession().removeAttribute("totalCount");
		request.getSession().removeAttribute("curCount");
		request.getSession().removeAttribute("percent");
		request.getSession().removeAttribute("percentText");

		//查询列表数据
		Configuration config = getConfig("config.properties");
		String configsrc =config.getString("url");
		String Filepath = configsrc+ "certificateReplace/";
		String strZipPath=configsrc+"certificateReplaceZip/";
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
		List<CertificateReplaceDO> courseFreeList = certificateReplaceService.list(params);
		request.getSession().setAttribute("totalCount", courseFreeList.size());
		for (CertificateReplaceDO item : courseFreeList) {
			item.setOldCourseName(FieldDictUtil.get(getAppName(), "pla_old_course_nzxy", "id", item.getOldCourseid()));
			item.setCourseClass(FieldDictUtil.get(getAppName(), "pla_old_course", "type", item.getCourseClass()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}

		if (courseFreeList != null && courseFreeList.size() > 0) {
			String[][] result = new String[courseFreeList.size() + 1][5];

			result[0] = new String[] { "证书编号","证书名称","课程层次","操作人","操作时间"};
			if (courseFreeList != null && courseFreeList.size() > 0) {
				for (int i = 0; i < courseFreeList.size(); i++) {
					CertificateReplaceDO courseFree = courseFreeList.get(i);
					result[i + 1][0] = String.valueOf(courseFree.getOldCourseid());
					result[i + 1][1] = String.valueOf(courseFree.getOldCourseName());
					result[i + 1][2] = String.valueOf(courseFree.getCourseClass());
					result[i + 1][3] = String.valueOf(courseFree.getOperator());
					result[i + 1][4] = String.valueOf(DateUtils.format(courseFree.getUpdateDate(), DateUtils.DATE_PATTERN));
					double dPercent=(double)(i)/courseFreeList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%
				}
			}
			String tempFileName="证书顶替课程导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="证书顶替课程导出信息" +date+".zip";
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
			return "center/plan/certificateReplace/certificateReplace";
		}
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", courseFreeList.size());
		return null;
	}

	/********************************************************************************************
	 /**
	 * 顶替课程批量导出
	 * @param params
	 * @param response
	 * @return
	 */

	@RequestMapping("/searchOutZSIExcel")
	public String searchOutZSIExcel(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request){
		request.getSession().removeAttribute("totalCount");
		request.getSession().removeAttribute("curCount");
		request.getSession().removeAttribute("percent");
		request.getSession().removeAttribute("percentText");
		//查询列表数据
		Configuration config = getConfig("config.properties");
		String configsrc =config.getString("url");
		String Filepath = configsrc+ "certificateReplaceItem/";
		String strZipPath=configsrc+"certificateReplaceItemZip/";
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
		List<CommonCourseReplaceItemNewDO> courseFreeList = certificateReplaceItemService.itemList(params);
		request.getSession().setAttribute("totalCount", courseFreeList.size());

		String courseReplaceName ="";
		for (CommonCourseReplaceItemNewDO item : courseFreeList) {
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));//课程名称
			courseReplaceName = certificateReplaceService.getFatherCourse(item.getCourseReplaceid());
			item.setCourseReplaceName(courseReplaceName);//顶替课程名称
			item.setType(FieldDictUtil.get(getAppName(), "pla_course", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "pla_course", "classify", item.getClassify()));
			item.setAttribute(FieldDictUtil.get(getAppName(), "pla_course", "attribute", item.getAttribute()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));

		}

		if (courseFreeList != null && courseFreeList.size() > 0) {
			String[][] result = new String[courseFreeList.size() + 1][10];

			result[0] = new String[] { "顶替课程代码","顶替课程名称","课程代码","课程名称","学分","类别","分类","属性","操作人","操作时间"};
			if (courseFreeList != null && courseFreeList.size() > 0) {
				for (int i = 0; i < courseFreeList.size(); i++) {
					CommonCourseReplaceItemNewDO courseFree = courseFreeList.get(i);
					result[i + 1][0] = String.valueOf(courseFree.getCourseReplaceid());
					result[i + 1][1] = String.valueOf(courseFree.getCourseReplaceName());
					result[i + 1][2] = String.valueOf(courseFree.getCourseid());
					result[i + 1][3] = String.valueOf(courseFree.getCourseName());
					//result[i + 1][4] = String.valueOf(courseFree.getRemark());
					result[i + 1][4] = String.valueOf(courseFree.getScore());
					result[i + 1][5] = String.valueOf(courseFree.getType());
					result[i + 1][6] = String.valueOf(courseFree.getClassify());
					result[i + 1][7] = String.valueOf(courseFree.getAttribute());
					result[i + 1][8] = String.valueOf(courseFree.getOperator());
					result[i + 1][9] = String.valueOf(DateUtils.format(courseFree.getUpdateDate(), DateUtils.DATE_PATTERN));
					double dPercent=(double)(i)/courseFreeList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%
				}
			}
			String tempFileName="顶替课程子页面导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="顶替课程子页面导出信息" +date+".zip";
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
			return "center/plan/certificateReplace/commonCourseReplace";
		}
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", courseFreeList.size());
		return null;
	}
}