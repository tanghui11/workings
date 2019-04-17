package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.OldCourseService;
import com.hxy.nzxy.stexam.common.config.Constant;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.OldCourseDO;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import com.hxy.nzxy.stexam.system.domain.FieldDictDO;
import com.hxy.nzxy.stexam.system.service.FieldDictService;
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
 * 老课程管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-07-30 10:05:51
 */
 
@Controller
@RequestMapping("/plan/oldCourse")
public class OldCourseController extends SystemBaseController {
	@Autowired
	private OldCourseService oldCourseService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private FieldDictService fieldDictService;
	@GetMapping()
	@RequiresPermissions("plan:oldCourse:oldCourse")
	String OldCourse(){
	    return "center/plan/oldCourse/oldCourse";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:oldCourse:oldCourse")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OldCourseDO> oldCourseList = oldCourseService.list(query);
		for (OldCourseDO item : oldCourseList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_old_course", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "pla_old_course", "classify", item.getClassify()));
            item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = oldCourseService.count(query);
		PageUtils pageUtils = new PageUtils(oldCourseList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:oldCourse:add")
	String add(Model model){

		model.addAttribute("pla_old_course_type", commonService.listFieldDict(getAppName(), "pla_old_course", "type"));
		model.addAttribute("pla_old_course_classify", commonService.listFieldDict(getAppName(), "pla_old_course", "classify"));
	    return "center/plan/oldCourse/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:oldCourse:edit")
	String edit(@PathVariable("id") String id,Model model){
		OldCourseDO oldCourse = oldCourseService.get(id);
		model.addAttribute("pla_old_course_type", commonService.listFieldDict(getAppName(), "pla_old_course", "type"));
		model.addAttribute("pla_old_course_classify", commonService.listFieldDict(getAppName(), "pla_old_course", "classify"));
		model.addAttribute("oldCourse", oldCourse);
	    return "center/plan/oldCourse/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:oldCourse:add")
	public R save( OldCourseDO oldCourse){
		oldCourse.setOperator(ShiroUtils.getUserId().toString());
		if(oldCourseService.save(oldCourse)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("pla_old_course_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(oldCourse.getId()+"");
			fieldDict.setFieldMean(oldCourse.getName());
			fieldDict.setSeq(0);
			if(fieldDictService.saveCache(fieldDict)>0) {
				return R.ok();
			}else{
				return R.error("添加数据成功，添加缓存失败！");
			}
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:oldCourse:edit")
	public R update( OldCourseDO oldCourse){
		oldCourse.setOperator(ShiroUtils.getUserId().toString());
		if(oldCourseService.update(oldCourse)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("pla_old_course_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(oldCourse.getId()+"");
			fieldDict.setFieldMean(oldCourse.getName());
			fieldDict.setSeq(0);
			if(fieldDictService.updateCache(fieldDict)>0) {
				return R.ok();
			}else{
				return R.error("修改数据成功，修改缓存失败！");
			}
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:oldCourse:remove")
	public R remove( String id){
		if(oldCourseService.remove(id)>0){
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setAppid(Constant.APPID);
			fieldDict.setTableName("pla_old_course_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setFieldValue(id+"");
			fieldDict.setSeq(0);
			if(fieldDictService.removeCache(fieldDict)>0) {
				return R.ok();
			}else{
				return R.error("删除数据成功，删除缓存失败！");
			}
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:oldCourse:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		if(oldCourseService.batchRemove(ids)>0)
		{
			FieldDictDO fieldDict = new FieldDictDO();
			fieldDict.setTableName("pla_old_course_nzxy");
			fieldDict.setFieldName("id");
			fieldDict.setSeq(0);
			if(fieldDictService.batchremoveCache(fieldDict,ids)>0) {
				return R.ok();
			}else{
				return R.error("删除数据成功，删除缓存失败！");
			}
		}
		return R.error();
	}
	@GetMapping("/showSearchList")
	String showSearch(Model model,@RequestParam Map<String, Object> params){
		String type=(String)params.get("type");
		model.addAttribute("type",type);
		return "center/plan/oldCourse/searchList";
	}

	@GetMapping("/searchList")
	List searchList(@RequestParam Map<String, Object> params){
		List list =oldCourseService. searchlist(params);
		return list;
	}

	//弹出导入页面
	@GetMapping("/importData")
	String importData( ){
		return "center/plan/oldCourse/importData";
	}
	/**
	 * 批量导入
	 */
	@PostMapping("/savBathData")
	@RequiresPermissions("plan:oldCourse:add")
	public String savBathData(@RequestParam(value="filename") MultipartFile file,
							  HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/plan/oldCourse/importData";
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
			return "center/plan/oldCourse/importData";
		}

		//批量导入
		String message = oldCourseService.batchImport(fileName,file);
		request.setAttribute("msg",message);
		return "center/plan/oldCourse/importData";
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
		List<OldCourseDO> oldCourseList = oldCourseService.list(params);
		request.getSession().setAttribute("totalCount", oldCourseList.size());
		for (OldCourseDO item : oldCourseList) {
			item.setType(FieldDictUtil.get(getAppName(), "pla_old_course", "type", item.getType()));
			item.setClassify(FieldDictUtil.get(getAppName(), "pla_old_course", "classify", item.getClassify()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		if (oldCourseList != null && oldCourseList.size() > 0) {
			String[][] result = new String[oldCourseList.size() + 1][9];
			result[0] = new String[] { "序号","代码", "老课程名称", "拼音", "分类", "专业类别", "操作员", "操作日期"};
			if (oldCourseList != null && oldCourseList.size() > 0) {
				for (int i = 0; i < oldCourseList.size(); i++) {
					OldCourseDO oldCourseDO = oldCourseList.get(i);
					result[i + 1][0] = String.valueOf(i + 1);
					result[i + 1][1] = String.valueOf(oldCourseDO.getId());
					result[i + 1][2] = String.valueOf(oldCourseDO.getName());
					result[i + 1][3] = String.valueOf(oldCourseDO.getPinyin());
					result[i + 1][4] = String.valueOf(oldCourseDO.getClassify());
					result[i + 1][5] = String.valueOf(oldCourseDO.getType());
					result[i + 1][6] = String.valueOf(oldCourseDO.getOperator());
					result[i + 1][7] = String.valueOf(oldCourseDO.getUpdateDate());

                    //进度条写入进度
                    double dPercent=(double)(i)/oldCourseList.size();   //将计算出来的数转换成double
                    int  percent=(int)(dPercent*100);               //再乘上100取整
                    request.getSession().setAttribute("curCount", i);
                    request.getSession().setAttribute("percent", percent);    //比如这里是50
                    request.getSession().setAttribute("percentText",percent+"%");//这里是50%
				}
			}
			String tempFileName="老课程管理导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="老课程管理导出信息" +date+".zip";
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
			return "center/plan/oldCourse/oldCourse";
		}
        //进度条，走到100%
        request.getSession().setAttribute("percent", 100);    //比如这里是50
        request.getSession().setAttribute("percentText",100+"%");//这里是50%
        request.getSession().setAttribute("curCount", oldCourseList.size());
		return null;
	}
}
