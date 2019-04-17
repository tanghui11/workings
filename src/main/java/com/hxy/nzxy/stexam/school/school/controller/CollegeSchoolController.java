package com.hxy.nzxy.stexam.school.school.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import com.hxy.nzxy.stexam.domain.SchoolDO;
import com.hxy.nzxy.stexam.domain.TaskDO;
import com.hxy.nzxy.stexam.school.school.service.SchoolSchoolService;
import com.hxy.nzxy.stexam.system.domain.DeptWorkerDO;
import org.apache.commons.configuration.Configuration;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hxy.nzxy.stexam.domain.CollegeDO;
import com.hxy.nzxy.stexam.school.school.service.CollegeSchoolService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.system.controller.SystemBaseController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 学院管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:40
 */
 
@Controller
@RequestMapping("/school/collegeSchool")
public class CollegeSchoolController extends SystemBaseController{
	@Autowired
	private CollegeSchoolService collegeSchoolService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private SchoolSchoolService schoolSchoolService;
	@GetMapping()
	@RequiresPermissions("school:collegeSchool:collegeSchool")
	String CollegeSchool(){
	    return "school/school/collegeSchool/collegeSchool";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:collegeSchool:collegeSchool")
	public PageUtils list(@RequestParam Map<String, Object> params, HttpServletRequest request){
		//查询列表数据

		params.put("schoolid",ShiroUtils.getUser().getWorkerid());
        Query query = new Query(params);
		List<CollegeDO> collegeSchoolList = collegeSchoolService.list(query);
        for (CollegeDO item : collegeSchoolList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = collegeSchoolService.count(query);
		PageUtils pageUtils = new PageUtils(collegeSchoolList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("school:collegeSchool:add")
	String add(Model model){
	    return "school/school/collegeSchool/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:collegeSchool:edit")
	String edit(@PathVariable("id") String id,Model model){
		CollegeDO collegeSchool = collegeSchoolService.get(id);
		model.addAttribute("collegeSchool", collegeSchool);
	    return "school/school/collegeSchool/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:collegeSchool:add")
	public R save( CollegeDO collegeSchool, HttpServletRequest request){
        collegeSchool.setSchoolid(ShiroUtils.getUser().getWorkerid());
        collegeSchool.setOperator(ShiroUtils.getUserId().toString());
		if(collegeSchoolService.save(collegeSchool)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:collegeSchool:edit")
	public R update( CollegeDO collegeSchool){
	    collegeSchool.setOperator(ShiroUtils.getUserId().toString());
		collegeSchoolService.update(collegeSchool);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:collegeSchool:remove")
	public R remove( String id){
		if(collegeSchoolService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:collegeSchool:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		collegeSchoolService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 院校端查询当前用户学院接口
	 */
	@ResponseBody
	@GetMapping("/listCollege")
	public List<CollegeDO> listCollege(@RequestParam Map<String, Object> params, HttpServletRequest request){
		//查询列表数据
		String type = ShiroUtils.getUser().getType();
		if(type.equals("3")){
			params.put("schoolid",ShiroUtils.getUser().getWorkerid());
		}else if(type.equals("4")){
			params.put("id",ShiroUtils.getUser().getWorkerid());
		}
		List<CollegeDO> collegeSchoolList = collegeSchoolService.listCollege(params);
		for (CollegeDO item : collegeSchoolList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		return collegeSchoolList;
	}


	/**
	 * 院校端查询当前用户学院接口
	 */
	@ResponseBody
	@GetMapping("/listAllCollege")
	public List<CollegeDO> listAllCollege(@RequestParam Map<String, Object> params, HttpServletRequest request){
		//查询列表数据

		List<CollegeDO> collegeSchoolList = collegeSchoolService.listCollege(params);
		for (CollegeDO item : collegeSchoolList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		return collegeSchoolList;
	}

	/*
	***学院管理导出
	 */
	@RequestMapping("searchOutXYGLExcel")
	public String searchOutXYGLExcel(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request){
		request.getSession().removeAttribute("totalCount");
		request.getSession().removeAttribute("curCount");
		request.getSession().removeAttribute("percent");
		request.getSession().removeAttribute("percentText");
		//查询列表数据
		String schoolid = ShiroUtils.getUser().getWorkerid();
		params.put("schoolid",schoolid);
		Configuration config = getConfig("config.properties");
		String configsrc =config.getString("url");
		String Filepath = configsrc+ "collegeSchool/";
		String strZipPath=configsrc+"collegeSchoolZip/";
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
		List<CollegeDO> collegeSchoolList = collegeSchoolService.list(params);
		request.getSession().setAttribute("totalCount", collegeSchoolList.size());
		for (CollegeDO item : collegeSchoolList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//System.out.println(sf2.format(date));

		if (collegeSchoolList != null && collegeSchoolList.size() > 0) {
			String[][] result = new String[collegeSchoolList.size() + 1][13];
			//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result[0] = new String[] { "编号","助学组织编号","名称","拼音","联系人","联系地址","邮编","固定电话","移动电话","传真","邮箱","操作员","操作日期"};
			if (collegeSchoolList != null && collegeSchoolList.size() > 0) {
				for (int i = 0; i < collegeSchoolList.size(); i++) {
					CollegeDO college = collegeSchoolList.get(i);
					result[i + 1][0] = String.valueOf(college.getId());
					result[i + 1][1] = String.valueOf(college.getSchoolid());
					result[i + 1][2] = String.valueOf(college.getName());
					result[i + 1][3] = String.valueOf(college.getPinyin());
					result[i + 1][4] = String.valueOf(college.getLinkman());
					result[i + 1][5] = String.valueOf(college.getAddress());
					result[i + 1][6] = String.valueOf(college.getPostCode());
					result[i + 1][7] = String.valueOf(college.getPhone());
					result[i + 1][8] = String.valueOf(college.getMphone());
					result[i + 1][9] = String.valueOf(college.getFax());
					result[i + 1][10] = String.valueOf(college.getEmail());
					result[i + 1][11] = String.valueOf(college.getOperator());
					result[i + 1][12] =  String.valueOf(DateUtils.format(college.getUpdateDate(), DateUtils.DATE_PATTERN));
					double dPercent=(double)(i)/collegeSchoolList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%;
				}
			}
			String tempFileName="学院管理导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="学院管理导出信息" +date+".zip";
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
			return "center/school/school/collegeSchool";
		}
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", collegeSchoolList.size());

		return null;
	}
}
