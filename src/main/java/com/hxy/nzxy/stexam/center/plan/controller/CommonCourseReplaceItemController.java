package com.hxy.nzxy.stexam.center.plan.controller;

import com.hxy.nzxy.stexam.center.plan.service.CommonCourseReplaceItemService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.CommonCourseReplaceDO;
import com.hxy.nzxy.stexam.domain.CommonCourseReplaceItemDO;
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
 * 通用顶替课程
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 14:02:49
 */
 
@Controller
@RequestMapping("/plan/commonCourseReplaceItem")
public class CommonCourseReplaceItemController extends SystemBaseController{
	@Autowired
	private CommonCourseReplaceItemService commonCourseReplaceItemService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("plan:commonCourseReplaceItem:commonCourseReplaceItem")
	String CommonCourseReplaceItem(){
	    return "center/plan/commonCourseReplaceItem/commonCourseReplaceItem";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("plan:commonCourseReplaceItem:commonCourseReplaceItem")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CommonCourseReplaceItemDO> commonCourseReplaceItemList = commonCourseReplaceItemService.list(query);
        for (CommonCourseReplaceItemDO item : commonCourseReplaceItemList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = commonCourseReplaceItemService.count(query);
		PageUtils pageUtils = new PageUtils(commonCourseReplaceItemList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("plan:commonCourseReplaceItem:add")
	String add(Model model){
	    return "center/plan/commonCourseReplaceItem/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("plan:commonCourseReplaceItem:edit")
	String edit(@PathVariable("id") Long id,Model model){
		CommonCourseReplaceItemDO commonCourseReplaceItem = commonCourseReplaceItemService.get(id);
		model.addAttribute("commonCourseReplaceItem", commonCourseReplaceItem);
	    return "center/plan/commonCourseReplaceItem/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("plan:commonCourseReplaceItem:add")
	public R save( CommonCourseReplaceItemDO commonCourseReplaceItem){
        commonCourseReplaceItem.setOperator(ShiroUtils.getUserId().toString());
		if(commonCourseReplaceItemService.save(commonCourseReplaceItem)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("plan:commonCourseReplaceItem:edit")
	public R update( CommonCourseReplaceItemDO commonCourseReplaceItem){
	    commonCourseReplaceItem.setOperator(ShiroUtils.getUserId().toString());
		commonCourseReplaceItemService.update(commonCourseReplaceItem);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("plan:commonCourseReplaceItem:remove")
	public R remove( Long id){
		if(commonCourseReplaceItemService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("plan:commonCourseReplaceItem:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		commonCourseReplaceItemService.batchRemove(ids);
		return R.ok();
	}

	//定义课程导入页面
	@GetMapping("/TYIimportData")

	String TYIimportData( ){

		return "center/plan/commonCourseReplaceItem/TYIimportData";
	}

	/**
	 * 批量导入
	 */
	@PostMapping("/TYIsavBathData")
	@RequiresPermissions("plan:commonCourseReplaceItem:add")
	public String MKsavBathData(@RequestParam(value="fatherCourseId") Long fatherCourseId,@RequestParam(value="filename") MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/plan/commonCourseReplaceItem/TYIimportData";
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
			return "center/plan/commonCourseReplaceItem/TYIimportData";
		}

		//批量导入
		String message = commonCourseReplaceItemService.TYIbatchImport(fatherCourseId,fileName,file);
		request.setAttribute("msg",message);
		return "center/plan/commonCourseReplaceItem/TYIimportData";
	}

	//通用课程导出
	@RequestMapping("/searchOutExcel")
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
		List<CommonCourseReplaceItemDO> commonCourseReplaceItemList = commonCourseReplaceItemService.list(params);
		for (CommonCourseReplaceItemDO item : commonCourseReplaceItemList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setCourseName(FieldDictUtil.get(getAppName(), "pla_course_nzxy", "id", item.getCourseid()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		if (commonCourseReplaceItemList != null && commonCourseReplaceItemList.size() > 0) {
			String[][] result = new String[commonCourseReplaceItemList.size() + 1][6];

			result[0] = new String[] { "序号","课程代码","课程名称","备注","操作人","操作时间"};
			if (commonCourseReplaceItemList != null && commonCourseReplaceItemList.size() > 0) {
				for (int i = 0; i < commonCourseReplaceItemList.size(); i++) {
					CommonCourseReplaceItemDO courseFree = commonCourseReplaceItemList.get(i);
					result[i + 1][0] =  String.valueOf(i + 1);
					result[i + 1][1] = String.valueOf(courseFree.getCourseid());
					result[i + 1][2] = String.valueOf(courseFree.getCourseName());
					result[i + 1][3] = String.valueOf(courseFree.getRemark());
					result[i + 1][4] = String.valueOf(courseFree.getOperator());
					result[i + 1][5] = String.valueOf(DateUtils.format(courseFree.getUpdateDate(), DateUtils.DATE_PATTERN));
					double dPercent=(double)(i)/commonCourseReplaceItemList.size();   //将计算出来的数转换成double
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
			return "center/plan/commonCourseReplaceItem/commonCourseReplaceItem?courseid="+params.get("courseid").toString()+"&courseReplaceid="+params.get("courseReplaceid").toString();
		}
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", commonCourseReplaceItemList.size());
		return null;
	}

}
