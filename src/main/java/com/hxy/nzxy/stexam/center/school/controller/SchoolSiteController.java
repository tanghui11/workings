package com.hxy.nzxy.stexam.center.school.controller;

import com.hxy.nzxy.stexam.center.region.service.RegionService;
import com.hxy.nzxy.stexam.center.school.service.SchoolSiteService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.RegionDO;
import com.hxy.nzxy.stexam.domain.SchoolSiteDO;
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
 * 办学地区管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
 
@Controller
@RequestMapping("/school/schoolSite")
public class SchoolSiteController extends SystemBaseController{
	@Autowired
	private SchoolSiteService schoolSiteService;
    @Autowired
    private CommonService commonService;
	@Autowired
	private RegionService regionService;
	@GetMapping()
	@RequiresPermissions("school:schoolSite:schoolSite")
	String SchoolSite(){
	    return "center/school/schoolSite/schoolSite";
	}
	
	@ResponseBody//查询列表数据
	@GetMapping("/list/{schoolid}")
	@RequiresPermissions("school:schoolSite:schoolSite")
	public PageUtils list(@RequestParam Map<String, Object> params,@PathVariable("schoolid") String schoolid, HttpServletRequest request){
		request.getSession().setAttribute("schoolid",schoolid);
		//查询列表数据
		params.put("schoolid", schoolid);
        Query query = new Query(params);
		List<SchoolSiteDO> schoolSiteList = schoolSiteService.list(query);
        for (SchoolSiteDO item : schoolSiteList) {

			RegionDO region = regionService.get(item.getRegionid());
        	//市考办

			item.setRegionShengName(region.getParentName());
			//县考办
			item.setRegionXianName(region.getName());
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_school_site", "status", item.getStatus()));
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
        }
		int total = schoolSiteService.count(query);
		PageUtils pageUtils = new PageUtils(schoolSiteList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("school:schoolSite:add")
	String add(Model model){
		model.addAttribute("sch_school_site_status", commonService.listFieldDict(getAppName(), "sch_school_site", "status"));

		return "center/school/schoolSite/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:schoolSite:edit")
	String edit(@PathVariable("id") Long id,Model model){
		SchoolSiteDO schoolSite = schoolSiteService.get(id);
		RegionDO region = regionService.get(schoolSite.getRegionid());
		RegionDO region1=regionService.get(region.getParentid());
		model.addAttribute("regionXian",region.getName());
		model.addAttribute("regionSheng",region1.getName());
		model.addAttribute("schoolSite", schoolSite);
		model.addAttribute("sch_school_site_status", commonService.listFieldDict(getAppName(), "sch_school_site", "status"));

		return "center/school/schoolSite/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:schoolSite:add")
	public R save( SchoolSiteDO schoolSite,HttpServletRequest request){
		String  schoolid= (String) request.getSession().getAttribute("schoolid");
		schoolSite.setSchoolid(Long.valueOf(schoolid));
        schoolSite.setOperator(ShiroUtils.getUserId().toString());
		if(schoolSiteService.save(schoolSite)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:schoolSite:edit")
	public R update( SchoolSiteDO schoolSite){
	    schoolSite.setOperator(ShiroUtils.getUserId().toString());

		schoolSiteService.update(schoolSite);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:schoolSite:remove")
	public R remove( Long id){
		if(schoolSiteService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:schoolSite:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		schoolSiteService.batchRemove(ids);
		return R.ok();
	}
	/**
	 * 批量审核接口
	 */
	@PostMapping( "/batchAuditStatus")
	@ResponseBody
	@RequiresPermissions("school:schoolSite:batchRemove")
	public R batchAuditStatus(@RequestParam("ids[]") Long[] ids,@RequestParam("status") String status){
		schoolSiteService.batchAuditStatus(ids,status);
		return R.ok();
	}
	/**
	 * 查询教学点接口
	 */

	@ResponseBody//查询列表数据
	@GetMapping("/listSchoolSite")
	public List<SchoolSiteDO> list(@RequestParam Map<String, Object> params){
		//查询列表数据


		List<SchoolSiteDO> schoolSiteList = schoolSiteService.listSchoolSite(params);

		return schoolSiteList;
	}

	//导出

	@RequestMapping("/searchOutExcel")
	public String searchOutExcel(@RequestParam Map<String, Object> params, Model model ,HttpServletRequest request, HttpServletResponse response){
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
		params.put("schoolid", params.get("orgid"));
		List<SchoolSiteDO> schoolSiteList = schoolSiteService.list(params);
		request.getSession().setAttribute("totalCount", schoolSiteList.size());
		for (SchoolSiteDO item : schoolSiteList) {
			RegionDO region = regionService.get(item.getRegionid());
			//市考办
			item.setRegionName(region.getParentName());
			//县考办
			//item.setRegionXianName(region.getName());
			item.setRegionXianName(region.getName());
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_school_site", "status", item.getStatus()));
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		if (schoolSiteList != null && schoolSiteList.size() > 0) {
			String[][] result = new String[schoolSiteList.size() + 1][8];
			result[0] = new String[] { "序号","代码", "地州市考办", "县区考办", "状态","操作员","操作日期"};
			if (schoolSiteList != null && schoolSiteList.size() > 0) {
				for (int i = 0; i < schoolSiteList.size(); i++) {
					SchoolSiteDO schoolSiteDO = schoolSiteList.get(i);
					result[i + 1][0] = String.valueOf(i + 1);
					result[i + 1][1] = String.valueOf(schoolSiteDO.getCode());
					result[i + 1][2] = String.valueOf(schoolSiteDO.getRegionName());
					result[i + 1][3] = String.valueOf(schoolSiteDO.getRegionXianName());
					result[i + 1][4] = String.valueOf(schoolSiteDO.getStatus());
					result[i + 1][5] = String.valueOf(schoolSiteDO.getOperator());
					result[i + 1][6] = String.valueOf(schoolSiteDO.getUpdateDate());

					//进度条写入进度
					double dPercent=(double)(i)/schoolSiteList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%

				}
			}
			String tempFileName="办学地区管理导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="办学地区管理导出信息" +date+".zip";
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
			model.addAttribute("sch_school_site_status", commonService.listFieldDict(getAppName(), "sch_school_site", "status"));
			return "center/school/schoolSite/schoolSite";
		}
		//进度条，走到100%
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", schoolSiteList.size());
		return null;
	}


	//弹出导入页面
	@GetMapping("/importData")
	String importData(String schoolsid ,Model model){
		 model.addAttribute("schoolsid",schoolsid);

		return "center/school/schoolSite/importData";
	}
	/**
	 * 批量导入
	 */
	@RequiresPermissions("school:schoolSite:add")
	@PostMapping("/savBathData")
	public String savBathData(@RequestParam(value="filename") MultipartFile file,String schoolsid,
							  HttpServletRequest request, HttpServletResponse response, HttpSession session
	) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/school/schoolSite/importData";
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
			return "center/school/schoolSite/importData";
		}

		//返回错误信息
		String message = schoolSiteService.batchImport(fileName,file ,schoolsid );
		request.setAttribute("msg",message);
		request.setAttribute("schoolid",schoolsid);
		return "center/school/schoolSite/importData";
	}

}
