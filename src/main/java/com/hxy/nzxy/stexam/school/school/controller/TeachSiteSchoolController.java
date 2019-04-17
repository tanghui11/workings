package com.hxy.nzxy.stexam.school.school.controller;

import com.hxy.nzxy.stexam.center.region.service.RegionService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.TeachSiteDO;
import com.hxy.nzxy.stexam.school.school.service.TeachSiteSchoolService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import static com.hxy.nzxy.stexam.common.utils.GenUtils.getConfig;

/**
 * 教学点管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:41
 */
 
@Controller
@RequestMapping("/school/teachSiteSchool")
public class TeachSiteSchoolController extends SystemBaseController{
	@Autowired
	private TeachSiteSchoolService teachSiteSchoolService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private RegionService regionService;
	@GetMapping()
	@RequiresPermissions("school:teachSiteSchool:teachSiteSchool")
	String TeachSiteSchool(){
	    return "school/school/teachSiteSchool/teachSiteSchool";
	}
	
	@ResponseBody
	@GetMapping("/list/{collegeid}")
	@RequiresPermissions("school:teachSiteSchool:teachSiteSchool")
	public PageUtils list(@RequestParam Map<String, Object> params, @PathVariable("collegeid") String collegeid, HttpServletRequest request){
		//查询列表数据
		request.getSession().setAttribute("collegeid",collegeid);
		params.put("collegeid", collegeid);
        Query query = new Query(params);
		List<TeachSiteDO> teachSiteSchoolList = teachSiteSchoolService.list(query);
        for (TeachSiteDO item : teachSiteSchoolList) {
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_teach_site", "status", item.getStatus()));
			item.setSchoolSiteName(item.getParentName()+"->"+item.getRegionName());
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = teachSiteSchoolService.count(query);
		PageUtils pageUtils = new PageUtils(teachSiteSchoolList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("school:teachSiteSchool:add")
	String add(Model model){
	    return "school/school/teachSiteSchool/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:teachSiteSchool:edit")
	String edit(@PathVariable("id") Long id,Model model){
		TeachSiteDO teachSiteSchool = teachSiteSchoolService.get(id);
		model.addAttribute("teachSiteSchool", teachSiteSchool);
	    return "school/school/teachSiteSchool/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:teachSiteSchool:add")
	public R save( TeachSiteDO teachSiteSchool,HttpServletRequest request){
	String  collegeid= (String) request.getSession().getAttribute("collegeid");
		teachSiteSchool.setCollegeid(Long.valueOf(collegeid));
		teachSiteSchool.setStatus("b");
        teachSiteSchool.setOperator(ShiroUtils.getUserId().toString());
		if(teachSiteSchoolService.save(teachSiteSchool)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:teachSiteSchool:edit")
	public R update( TeachSiteDO teachSiteSchool){
	    teachSiteSchool.setOperator(ShiroUtils.getUserId().toString());
		teachSiteSchoolService.update(teachSiteSchool);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:teachSiteSchool:remove")
	public R remove( Long id){
		if(teachSiteSchoolService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:teachSiteSchool:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		teachSiteSchoolService.batchRemove(ids);
		return R.ok();
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
		params.put("collegeid", params.get("orgid"));
		List<TeachSiteDO> teachSiteSchoolList = teachSiteSchoolService.list(params);
		request.getSession().setAttribute("totalCount", teachSiteSchoolList.size());
		for (TeachSiteDO item : teachSiteSchoolList) {
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_teach_site", "status", item.getStatus()));
			item.setSchoolSiteName(item.getParentName()+"->"+item.getRegionName());
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		if (teachSiteSchoolList != null && teachSiteSchoolList.size() > 0) {
			String[][] result = new String[teachSiteSchoolList.size() + 1][9];
			result[0] = new String[] { "序号","办学地点", "教学点名称","联系人","移动电话","状态","操作员","操作日期"};
			if (teachSiteSchoolList != null && teachSiteSchoolList.size() > 0) {
				for (int i = 0; i < teachSiteSchoolList.size(); i++) {
					TeachSiteDO teachSiteDO = teachSiteSchoolList.get(i);
					result[i + 1][0] = String.valueOf(i + 1);
					result[i + 1][1] = String.valueOf(teachSiteDO.getSchoolSiteName());
					result[i + 1][2] = String.valueOf(teachSiteDO.getName());
					result[i + 1][3] = String.valueOf(teachSiteDO.getLinkman());
					result[i + 1][4] = String.valueOf(teachSiteDO.getMphone());
					result[i + 1][5] = String.valueOf(teachSiteDO.getStatus());
					result[i + 1][6] = String.valueOf(teachSiteDO.getOperator());
					result[i + 1][7] = String.valueOf(teachSiteDO.getUpdateDate());

					//进度条写入进度
					double dPercent=(double)(i)/teachSiteSchoolList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%
				}
			}
			String tempFileName="学院端教学点管理导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="学院端教学点管理导出信息" +date+".zip";
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
			return "school/school/teachSiteSchool/teachSiteSchool";
		}
		//进度条，走到100%
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", teachSiteSchoolList.size());
		return null;
	}

}