package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.CommonCourseReplaceItemService;
import com.hxy.nzxy.stexam.center.plan.service.CommonCourseReplaceService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.CommonCourseReplaceDO;
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
 * 通用课程顶替
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
 
@Controller
@RequestMapping("/plan/commonCourseReplace")
public class CommonCourseReplaceController extends SystemBaseController{
	@Autowired
	private CommonCourseReplaceService commonCourseReplaceService;

	@Autowired
	private CommonCourseReplaceItemService commonCourseReplaceItemService;

    @Autowired
    private CommonService commonService;

	@GetMapping()
	@RequiresPermissions("plan:commonCourseReplace:commonCourseReplace")
	String CommonCourseReplace(){
	    return "center/plan/commonCourseReplace/commonCourseReplace";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:commonCourseReplace:commonCourseReplace")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CommonCourseReplaceDO> commonCourseReplaceList = commonCourseReplaceService.list(query);

        for (CommonCourseReplaceDO item : commonCourseReplaceList) {
			String A ="";
			String B ="";
			String C ="";
			String D ="";
            item.setOperator(UserUtil.getName(item.getOperator()));
			item.setFlag(FieldDictUtil.get(getAppName(), "pla_course_replace", "flag", item.getFlag()));
			item.setCourseClass(FieldDictUtil.get(getAppName(), "pla_course_replace", "course_class", item.getCourseClass()));
			item.setType(FieldDictUtil.get(getAppName(), "pla_course_replace", "type", item.getType()));
			item.setSpecialityClass(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", item.getSpecialityClass()));
			item.setCourseid(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid())+"("+item.getCourseid()+")");
			 A =FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getAppendCourseid1())+"("+item.getAppendCourseid1()+")";
			 B =FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getAppendCourseid2())+"("+item.getAppendCourseid2()+")";
			 C =FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getAppendCourseid3())+"("+item.getAppendCourseid3()+")";
			 D =FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getAppendCourseid4())+"("+item.getAppendCourseid4()+")";
			if (item.getAppendCourseid1() != null && !"".equals(item.getAppendCourseid1())){
				item.setAppendCourseid1(A);
			}
			if (item.getAppendCourseid2() != null && !"".equals(item.getAppendCourseid2())){
				item.setAppendCourseid2(B);
			}
			if (item.getAppendCourseid3()!= null && !"".equals(item.getAppendCourseid3())){
				item.setAppendCourseid3(C);
			}
			if (item.getAppendCourseid4() != null && !"".equals(item.getAppendCourseid4())){
				item.setAppendCourseid4(D);
			}

            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = commonCourseReplaceService.count(query);
		PageUtils pageUtils = new PageUtils(commonCourseReplaceList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("plan:commonCourseReplace:add")
	String add(Model model){
		model.addAttribute("pla_course_replace_flag", commonService.listFieldDict(getAppName(), "pla_course_replace", "flag"));
		model.addAttribute("pla_course_replace_course_class", commonService.listFieldDict(getAppName(), "pla_course_replace", "course_class"));
		model.addAttribute("pla_course_replace_type", commonService.listFieldDict(getAppName(), "pla_course_replace", "type"));
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
	    return "center/plan/commonCourseReplace/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:commonCourseReplace:edit")
	String edit(@PathVariable("id") Long id,Model model){
		CommonCourseReplaceDO commonCourseReplace = commonCourseReplaceService.get(id);
		model.addAttribute("commonCourseReplace", commonCourseReplace);
		model.addAttribute("pla_speciality_record_type", commonService.listFieldDict(getAppName(), "pla_speciality_record", "type"));
		model.addAttribute("pla_course_replace_flag", commonService.listFieldDict(getAppName(), "pla_course_replace", "flag"));
		model.addAttribute("pla_course_replace_course_class", commonService.listFieldDict(getAppName(), "pla_course_replace", "course_class"));
		model.addAttribute("pla_course_replace_type", commonService.listFieldDict(getAppName(), "pla_course_replace", "type"));
	    return "center/plan/commonCourseReplace/edit";
	}

	//通用课程顶替导入页面
		@GetMapping("/TYimportData")

		String TYimportData( ){

			return "center/plan/commonCourseReplace/TYimportData";
		}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:commonCourseReplace:add")
	public R save( CommonCourseReplaceDO commonCourseReplace){
        commonCourseReplace.setOperator(ShiroUtils.getUserId().toString());
		if(commonCourseReplaceService.save(commonCourseReplace)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:commonCourseReplace:edit")
	public R update( CommonCourseReplaceDO commonCourseReplace){
	    commonCourseReplace.setOperator(ShiroUtils.getUserId().toString());
		commonCourseReplaceService.update(commonCourseReplace);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:commonCourseReplace:remove")
	public R remove( Long id){
		if(commonCourseReplaceService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:commonCourseReplace:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		commonCourseReplaceService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 批量导入
	 */
	@PostMapping("/TYsavBathData")
	@RequiresPermissions("plan:commonCourseReplace:add")
	public String MKsavBathData(@RequestParam(value="filename") MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/plan/commonCourseReplace/TYimportData";
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
			return "center/plan/commonCourseReplace/TYimportData";
		}

		//批量导入
		String message = commonCourseReplaceService.TYbatchImport(fileName,file);
		request.setAttribute("msg",message);
		return "center/plan/commonCourseReplace/TYimportData";
	}

    /**
     * 通用课程顶替批量导出
     * @param params
     * @param response
     * @return
     */
	@RequestMapping("/searchOutTYExcel")
	public String searchOutTYExcel(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request){
		request.getSession().removeAttribute("totalCount");
		request.getSession().removeAttribute("curCount");
		request.getSession().removeAttribute("percent");
		request.getSession().removeAttribute("percentText");
		//查询列表数据
		Configuration config = getConfig("config.properties");
		String configsrc =config.getString("url");
		String Filepath = configsrc+ "commonCourseReplace/";
		String strZipPath=configsrc+"commonCourseReplaceZip/";
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
		List<CommonCourseReplaceDO> courseFreeList = commonCourseReplaceService.list(params);
		request.getSession().setAttribute("totalCount", courseFreeList.size());
		for (CommonCourseReplaceDO item : courseFreeList) {
			item.setCourseid(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
			item.setFlag(FieldDictUtil.get(getAppName(), "pla_course_replace", "flag", item.getFlag()));
			item.setCourseClass(FieldDictUtil.get(getAppName(), "pla_course_replace", "course_class", item.getCourseClass()));

			item.setType(FieldDictUtil.get(getAppName(), "pla_course_replace", "type", item.getType()));
			item.setSpecialityClass(FieldDictUtil.get(getAppName(), "pla_speciality_record", "type", item.getSpecialityClass()));
			item.setAppendCourseid1(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getAppendCourseid1()));
			item.setAppendCourseid2(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getAppendCourseid2()));
			item.setAppendCourseid3(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getAppendCourseid3()));
			item.setAppendCourseid4(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getAppendCourseid4()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		if (courseFreeList != null && courseFreeList.size() > 0) {
			String[][] result = new String[courseFreeList.size() + 1][14];
			result[0] = new String[] { "课程名称","顶替类别","课程层次","类别","共几门课程","至少选几门","最少学分","附加课程1","附加课程2","附加课程3","附加课程4","专业类别","操作人","操作时间"};
			if (courseFreeList != null && courseFreeList.size() > 0) {
				for (int i = 0; i < courseFreeList.size(); i++) {
					CommonCourseReplaceDO courseFree = courseFreeList.get(i);
					result[i + 1][0] = String.valueOf(courseFree.getCourseid());
					result[i + 1][1] = String.valueOf(courseFree.getFlag());
					result[i + 1][2] = String.valueOf(courseFree.getCourseClass());
					result[i + 1][3] = String.valueOf(courseFree.getType());
					result[i + 1][4] = String.valueOf(courseFree.getCourseNum());
					result[i + 1][5] = String.valueOf(courseFree.getLeastNum());
					result[i + 1][6] = String.valueOf(courseFree.getLeastScore());
					result[i + 1][7] = String.valueOf(courseFree.getAppendCourseid1());
					result[i + 1][8] = String.valueOf(courseFree.getAppendCourseid2());
					result[i + 1][9] = String.valueOf(courseFree.getAppendCourseid3());
					result[i + 1][10] = String.valueOf(courseFree.getAppendCourseid4());
					result[i + 1][11] = String.valueOf(courseFree.getSpecialityClass());
					result[i + 1][12] = String.valueOf(courseFree.getOperator());
					result[i + 1][13] = String.valueOf(DateUtils.format(courseFree.getUpdateDate(), DateUtils.DATE_PATTERN));
					double dPercent=(double)(i)/courseFreeList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%;
				}
			}
			String tempFileName="通用顶替课程导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="通用顶替课程导出信息" +date+".zip";
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
			return "center/plan/commonCourseReplace/commonCourseReplace";
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

	@RequestMapping("/searchOutTYIExcel")
	public String searchOuttyiExcel(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request){
		request.getSession().removeAttribute("totalCount");
		request.getSession().removeAttribute("curCount");
		request.getSession().removeAttribute("percent");
		request.getSession().removeAttribute("percentText");
		//查询列表数据
		Configuration config = getConfig("config.properties");
		String configsrc =config.getString("url");
		String Filepath = configsrc+ "commonCourseReplaceItem/";
		String strZipPath=configsrc+"commonCourseReplaceItemZip/";
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
		List<CommonCourseReplaceItemNewDO> courseFreeList = commonCourseReplaceItemService.itemList(params);

		request.getSession().setAttribute("totalCount", courseFreeList.size());

		String courseReplaceName ="";
		for (CommonCourseReplaceItemNewDO item : courseFreeList) {
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
			courseReplaceName = commonCourseReplaceService.getFatherCourse(item.getCourseReplaceid());
			item.setCourseReplaceName(courseReplaceName);
			item.setType(FieldDictUtil.get(getAppName(), "pla_course", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "pla_course", "classify", item.getClassify()));
			item.setAttribute(FieldDictUtil.get(getAppName(), "pla_course", "attribute", item.getAttribute()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}

		if (courseFreeList != null && courseFreeList.size() > 0) {
			String[][] result = new String[courseFreeList.size() + 1][11];

			result[0] = new String[] { "课程代码","课程名称","备注","学分","类别","分类","属性","操作人","操作时间"};
			if (courseFreeList != null && courseFreeList.size() > 0) {
				for (int i = 0; i < courseFreeList.size(); i++) {
					CommonCourseReplaceItemNewDO courseFree = courseFreeList.get(i);

					result[i + 1][0] = String.valueOf(courseFree.getCourseid());
					result[i + 1][1] = String.valueOf(courseFree.getCourseName());
					result[i + 1][2] = String.valueOf(courseFree.getRemark());
					result[i + 1][3] = String.valueOf(courseFree.getScore());
					result[i + 1][4] = String.valueOf(courseFree.getType());
					result[i + 1][5] = String.valueOf(courseFree.getClassify());
					result[i + 1][6] = String.valueOf(courseFree.getAttribute());
					result[i + 1][7] = String.valueOf(courseFree.getOperator());
					result[i + 1][8] = String.valueOf(DateUtils.format(courseFree.getUpdateDate(), DateUtils.DATE_PATTERN));
					double dPercent=(double)(i)/courseFreeList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%
				}
			}
			String tempFileName="顶替课程导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="顶替课程导出信息" +date+".zip";
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
			return "center/plan/commonCourseReplace/commonCourseReplace";
		}
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", courseFreeList.size());
		return null;
	}
}
