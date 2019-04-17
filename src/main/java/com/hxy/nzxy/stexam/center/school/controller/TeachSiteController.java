package com.hxy.nzxy.stexam.center.school.controller;

import com.hxy.nzxy.stexam.center.school.service.TeachSiteService;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.utils.*;
import com.hxy.nzxy.stexam.domain.TeachSiteDO;
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
 * 中心端教学点审核
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 17:52:27
 */
 
@Controller
@RequestMapping("/school/teachSite")
public class TeachSiteController extends SystemBaseController{
	@Autowired
	private TeachSiteService teachSiteService;
    @Autowired
    private CommonService commonService;
	@GetMapping()
	@RequiresPermissions("school:teachSite:teachSite")
	String TeachSite(Model model){
		model.addAttribute("sch_teach_site_status", commonService.listFieldDict(getAppName(), "sch_teach_site", "status"));
		return "center/school/teachSite/teachSite";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:teachSite:teachSite")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TeachSiteDO> teachSiteList = teachSiteService.list(query);
        for (TeachSiteDO item : teachSiteList) {
            item.setOperator(UserUtil.getName(item.getOperator()));
            item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_teach_site", "status", item.getStatus()));
			item.setSchoolSiteName(FieldDictUtil.get(getAppName(), "reg_region_nzxy_b", "id", String.valueOf(item.getRegionid()))+"->"+FieldDictUtil.get(getAppName(), "reg_region_nzxy_a", "id", String.valueOf(item.getRegionid())));
        }
		int total = teachSiteService.count(query);
		PageUtils pageUtils = new PageUtils(teachSiteList, total);
		return pageUtils;
	}
	


	/**
	 * 单个审核/取消审核
	 */
	@PostMapping( "/updateAudit")
	@ResponseBody
	@RequiresPermissions("school:teachSite:audit")
	public R updateAudit( @RequestParam Map<String, Object> params){
		String auditStatus=(String)params.get("status");
		String messages="审核成功！";
		if("a".equals(auditStatus))
		{
			messages="取消审核成功！";
		}
		if(teachSiteService.updateAudit(params)>0){
			return R.ok(messages);
		}
		return R.error();
	}

	/**
	 * 批量审核/取消审核
	 */
	@PostMapping( "/batchUpdateAudit")
	@ResponseBody
	@RequiresPermissions("school:teachSite:batchAudit")
	public R batchUpdateAudit(@RequestParam("ids[]") Long[] ids,@RequestParam("status") String status){

		String messages="批量审核成功！";
		if("a".equals(status))
		{
			messages="批量取消审核成功！";
		}
		teachSiteService.batchUpdateAudit(ids,status);
		return R.ok();
	}

	@ResponseBody
	@GetMapping("/listTeachSite")
	public List<TeachSiteDO> listTeachSite(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<TeachSiteDO> teachSiteList = teachSiteService.list(params);
		for (TeachSiteDO item : teachSiteList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		return teachSiteList;
	}
	@ResponseBody
	@GetMapping("/listTeachSite2")
	public PageUtils listTeachSite2(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<TeachSiteDO> teachSiteList = teachSiteService.list(query);
		for (TeachSiteDO item : teachSiteList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
		}
		int total = teachSiteService.count(query);
		PageUtils pageUtils = new PageUtils(teachSiteList, total);
		return pageUtils;
	}


	//导出
	@RequestMapping("/searchOutExcel")
	public String searchOutEXcel(@RequestParam Map<String, Object> params,HttpServletRequest request, HttpServletResponse response,Model model){
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
		List<TeachSiteDO> teachSiteList = teachSiteService.list(params);
		request.getSession().setAttribute("totalCount", teachSiteList.size());
		for (TeachSiteDO item : teachSiteList) {
			item.setOperator(UserUtil.getName(item.getOperator()));
			item.setUpdateDate(DateUtils.format(item.getUpdateDate(), DateUtils.DATE_PATTERN));
			item.setStatus(FieldDictUtil.get(getAppName(), "sch_teach_site", "status", item.getStatus()));
			item.setSchoolSiteName(FieldDictUtil.get(getAppName(), "reg_region_nzxy_b", "id",
					String.valueOf(item.getRegionid()))+"->"+FieldDictUtil.get(getAppName(), "reg_region_nzxy_a", "id",
					String.valueOf(item.getRegionid())));
		}
		if (teachSiteList != null && teachSiteList.size() > 0) {
			String[][] result = new String[teachSiteList.size() + 1][14];
			result[0] = new String[] { "序号","单位名称", "办学地区","拼音", "联系人", "固定电话","移动电话","邮箱","邮编"
					,"联系地址","状态","操作员","申请日期"};
			if (teachSiteList != null && teachSiteList.size() > 0) {
				for (int i = 0; i < teachSiteList.size(); i++) {
					TeachSiteDO teachSiteDO = teachSiteList.get(i);
					result[i + 1][0] = String.valueOf(i + 1);
					result[i + 1][1] = String.valueOf(teachSiteDO.getName());
					result[i + 1][2] = String.valueOf(teachSiteDO.getSchoolSiteName());
					result[i + 1][3] = String.valueOf(teachSiteDO.getPinyin());
					result[i + 1][4] = String.valueOf(teachSiteDO.getLinkman());
					result[i + 1][5] = String.valueOf(teachSiteDO.getPhone());
					result[i + 1][6] = String.valueOf(teachSiteDO.getMphone());
					result[i + 1][7] = String.valueOf(teachSiteDO.getEmail());
					result[i + 1][8] = String.valueOf(teachSiteDO.getPostCode());
					result[i + 1][9] = String.valueOf(teachSiteDO.getAddress());
					result[i + 1][10] = String.valueOf(teachSiteDO.getStatus());
					result[i + 1][11] = String.valueOf(teachSiteDO.getOperator());
					result[i + 1][12] = String.valueOf(teachSiteDO.getUpdateDate());
					//进度条写入进度
					double dPercent=(double)(i)/teachSiteList.size();   //将计算出来的数转换成double
					int  percent=(int)(dPercent*100);               //再乘上100取整
					request.getSession().setAttribute("curCount", i);
					request.getSession().setAttribute("percent", percent);    //比如这里是50
					request.getSession().setAttribute("percentText",percent+"%");//这里是50%

				}
			}
			String tempFileName="教学点管理导出信息"+".xlsx";
			ExcelUtil.writeExcelOs(result,Filepath+tempFileName);
			BufferedInputStream buffer1=null;
			String date = "";
			try {
				date = DateUtil.formatDate(new Date());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			OutputStream out1=null;
			String zipName="教学点管理导出信息" +date+".zip";
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
			model.addAttribute("sch_teach_site_status", commonService.listFieldDict(getAppName(), "sch_teach_site", "status"));
			return "center/school/teachSite/teachSite";
		}
		//进度条，走到100%
		request.getSession().setAttribute("percent", 100);    //比如这里是50
		request.getSession().setAttribute("percentText",100+"%");//这里是50%
		request.getSession().setAttribute("curCount", teachSiteList.size());
		return null;
	}
	//弹出导入页面
	@GetMapping("/importData")
	String importData(String collegeId, Model model){
		model.addAttribute("collegeId",collegeId);
		return "center/school/teachSite/importData";
	}
	/**
	 * 批量导入
	 */
	@PostMapping("/savBathData")
	@RequiresPermissions("school:teachSite:add")
	public String savBathData(@RequestParam(value="filename") MultipartFile file,
							  HttpServletRequest request, HttpServletResponse response, HttpSession session, String collegeId) throws IOException {
		//判断文件是否为空
		if(file==null){
			request.setAttribute("msg","文件不能为空！");
			return "center/school/teachSite/importData";
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
			return "center/school/teachSite/importData";
		}
		//批量导入
		String message = teachSiteService.batchImport(fileName,file, collegeId);
		request.setAttribute("collegeId",collegeId);
		request.setAttribute("msg",message);
		return "center/school/teachSite/importData";
	}
}