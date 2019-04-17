package com.hxy.nzxy.stexam.school.school.controller;

import com.hxy.nzxy.stexam.center.region.service.RegionService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.SchoolSiteDO;
import com.hxy.nzxy.stexam.school.school.service.SchoolSiteSchoolService;
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
 * 办学地区管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-08 20:36:41
 */
 
@Controller
@RequestMapping("/school/schoolSiteSchool")
public class SchoolSiteSchoolController extends SystemBaseController{
	@Autowired
	private SchoolSiteSchoolService schoolSiteSchoolService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private RegionService regionService;
	@GetMapping()
	@RequiresPermissions("school:schoolSiteSchool:schoolSiteSchool")
	String SchoolSiteSchool(){
	    return "school/school/schoolSiteSchool/schoolSiteSchool";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:schoolSiteSchool:schoolSiteSchool")
	public PageUtils list(@RequestParam Map<String, Object> params, HttpServletRequest request){
		//查询列表数据
		params.put("schoolid",ShiroUtils.getUser().getWorkerid());
      Query query = new Query(params);
	//	Map map=new HashMap();
	//	map.put("keyName","id");
	//	map.put("table","sch_school");
//		map.put("value","name");


		List<SchoolSiteDO> schoolSiteSchoolList = schoolSiteSchoolService.list(query);
        for (SchoolSiteDO item : schoolSiteSchoolList) {
			//map.put("key",item.getSchoolid());
			//item.setSchoolName(commonService.getValueBykeyTable(map).getValue());
			item.setRegionName(item.getRegionShengName()+"->"+item.getRegionXianName());
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_school_site", "status", item.getStatus()));
			item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = schoolSiteSchoolService.count(query);
		PageUtils pageUtils = new PageUtils(schoolSiteSchoolList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("school:schoolSiteSchool:add")
	String add(Model model){
	    return "school/school/schoolSiteSchool/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:schoolSiteSchool:edit")
	String edit(@PathVariable("id") Long id,Model model){

		SchoolSiteDO schoolSiteSchool = schoolSiteSchoolService.get(id);
		model.addAttribute("schoolSiteSchool", schoolSiteSchool);
	    return "school/school/schoolSiteSchool/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:schoolSiteSchool:add")
	public R save( SchoolSiteDO schoolSiteSchool){
        schoolSiteSchool.setOperator(ShiroUtils.getUserId().toString());
		if(schoolSiteSchoolService.save(schoolSiteSchool)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:schoolSiteSchool:edit")
	public R update( SchoolSiteDO schoolSiteSchool){
	    schoolSiteSchool.setOperator(ShiroUtils.getUserId().toString());
		schoolSiteSchoolService.update(schoolSiteSchool);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:schoolSiteSchool:remove")
	public R remove( Long id){
		if(schoolSiteSchoolService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:schoolSiteSchool:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		schoolSiteSchoolService.batchRemove(ids);
		return R.ok();
	}
	/**
	 * 办学地区下拉框
	 */
	@ResponseBody
	@RequestMapping("/listSchoolSite")
	public List<SchoolSiteDO>  listSchoolSite(@RequestParam Map<String, Object> params, HttpServletRequest request){
		//查询列表数据
		params.put("schoolid",ShiroUtils.getUser().getWorkerid());
		params.put("status","b");


		List<SchoolSiteDO> schoolSiteSchoolList = schoolSiteSchoolService.list(params);
		for (SchoolSiteDO item : schoolSiteSchoolList) {
			item.setRegionName(item.getRegionShengName()+"->"+item.getRegionXianName());
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_school_site", "status", item.getStatus()));

		}

		return schoolSiteSchoolList;
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
		params.put("schoolid",ShiroUtils.getUser().getWorkerid());
		List<SchoolSiteDO> schoolSiteSchoolList = schoolSiteSchoolService.list(params);
		request.getSession().setAttribute("totalCount", schoolSiteSchoolList.size());
		for (SchoolSiteDO item : schoolSiteSchoolList) {
			//map.put("key",item.getSchoolid());
			//item.setSchoolName(commonService.getValueBykeyTable(map).getValue());
			item.setRegionName(item.getRegionShengName()+"->"+item.getRegionXianName());
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_school_site", "status", item.getStatus()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		if (schoolSiteSchoolList != null && schoolSiteSchoolList.size() > 0) {
			String[][] result = new String[schoolSiteSchoolList.size() + 1][8];
			result[0] = new String[] { "序号","代码","助学组织名称", "考区名称","状态","操作员","申请日期"};
			if (schoolSiteSchoolList != null && schoolSiteSchoolList.size() > 0) {
				for (int i = 0; i < schoolSiteSchoolList.size(); i++) {
					SchoolSiteDO schoolSiteSchoolDO = schoolSiteSchoolList.get(i);
					result[i + 1][0] = String.valueOf(i + 1);
					result[i + 1][1] = String.valueOf(schoolSiteSchoolDO.getCode());
					result[i + 1][2] = String.valueOf(schoolSiteSchoolDO.getSchoolName());
					result[i + 1][3] = String.valueOf(schoolSiteSchoolDO.getRegionName());
					result[i + 1][4] = String.valueOf(schoolSiteSchoolDO.getStatus());
					result[i + 1][5] = String.valueOf(schoolSiteSchoolDO.getOperator());
					result[i + 1][6] = String.valueOf(schoolSiteSchoolDO.getUpdateDate());

					//进度条写入进度
					double dPercent=(double)(i)/schoolSiteSchoolList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%
				}
			}
			String tempFileName="办学地区查看导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="办学地区查看导出信息" +date+".zip";
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
			return "school/school/schoolSiteSchool/schoolSiteSchool";
		}

		//进度条，走到100%
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", schoolSiteSchoolList.size());
		return null;
	}


}
